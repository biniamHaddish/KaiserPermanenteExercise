package biniam.berhane.kaiserpermanenteexercise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import biniam.berhane.kaiserpermanenteexercise.database.BooksDAO
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.model.BooksListResponse
import biniam.berhane.kaiserpermanenteexercise.repository.BooksRepo
import kotlinx.coroutines.*

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
class BooksViewModel(
    private val booksDao: BooksDAO,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    //scope for UI with A Job
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val allBooks = booksDao.getAllBooks()

    init {
        // all the initialization goes here
        uiScope.launch { insertBooksToDb() }
    }

    private suspend fun insertBooksToDb() {
        withContext(Dispatchers.IO) {
            BooksRepo(booksDao).saveToDb()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}