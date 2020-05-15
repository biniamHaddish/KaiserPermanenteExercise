package biniam.berhane.kaiserpermanenteexercise.repository

import android.icu.text.CaseMap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import biniam.berhane.kaiserpermanenteexercise.database.BooksDAO
import biniam.berhane.kaiserpermanenteexercise.database.BooksDatabase
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.model.BooksListResponse
import biniam.berhane.kaiserpermanenteexercise.network.Retrofit
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */

class BooksRepo(private val booksDao: BooksDAO) {
    val TAG = "BooksRepo"
    var client = Retrofit.googleBookApi()

    suspend fun saveToDb() {
        val books = client.getBooksAsync()
        books.apply {
            items.forEach { items ->
                val title = items.volumeInfo?.title
                val id = items.id
                val author = items.volumeInfo?.getAuthorsString()
                val description = items.volumeInfo?.description
                val book = Books(id, title, author, description)
                booksDao.insertBooks(book)
                Log.d(TAG, "🧲........Data Saved properly...")

            }
        }

    }
}