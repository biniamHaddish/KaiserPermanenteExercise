package biniam.berhane.kaiserpermanenteexercise.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import biniam.berhane.kaiserpermanenteexercise.database.DataBaseHelper
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.repository.BooksRepo
import kotlinx.coroutines.*

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
private const val TAG = "BooksViewModel"

class BooksViewModel(
    private val dataBaseHelper: DataBaseHelper,
    application: Application
) : AndroidViewModel(application) {

    private var _booksLivedata = MutableLiveData<List<Books>>()
    var bookslivedata: LiveData<List<Books>> = _booksLivedata


    private var viewModelJob = Job()

    //scope for UI with A Job
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        // all the initialization goes here
        uiScope.launch {
            insertBooksToDb()
            // get all the contents from the Database here
            getAllBooksFromCursor()
        }
    }

    private suspend fun insertBooksToDb() {
        withContext(Dispatchers.IO) {
            try {

                BooksRepo().saveBookToBooksTable(
                    onSuccess = { book ->
                        val id = book.id
                        val title = book.title
                        val author = book.author
                        val description = book.description
                        if (title != null && description != null && author != null) {
                            dataBaseHelper.insertBook(
                                id, title,
                                author,
                                description
                            )
                        } else {
                            Log.d(TAG, "Data is Empty")
                        }
                    },
                    onError = { error ->
                        Log.e(TAG, error)
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, "${e.message}")
            }
        }
    }


    private suspend fun getAllBooksFromCursor() {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Books>()
            try {
                val cursor = dataBaseHelper.getAllBooks()
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val titleIndex = cursor.getColumnIndex(DataBaseHelper.COLUMN_TITLE)
                        val authorIndex = cursor.getColumnIndex(DataBaseHelper.COLUMN_AUTHOR)
                        val descriptionIndex =
                            cursor.getColumnIndex(DataBaseHelper.COLUMN_DESCRIPTION)
                        //data
                        val title = cursor.getString(titleIndex)
                        val author = cursor.getString(authorIndex)
                        val description = cursor.getString(descriptionIndex)
                        val books = Books(title = title, author = author, description = description)
                        list.add(books)
                        Log.d(
                            TAG,
                            "Cursor data ...........😄🙉 ....${cursor.getString(descriptionIndex)}"
                        )
                    }
                }
                _booksLivedata.postValue(list)
                cursor?.close()
            } catch (e: Exception) {
                Log.d(TAG, "${e.message}")
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        dataBaseHelper.close()
    }
}