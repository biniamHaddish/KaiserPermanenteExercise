package biniam.berhane.kaiserpermanenteexercise.repository

import android.util.Log
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.model.BooksListResponse
import biniam.berhane.kaiserpermanenteexercise.network.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */


private const val TAG = "BooksRepo"

class BooksRepo {
    val TAG = "BooksRepo"
    var client = Retrofit.googleBookApi()

    fun saveBookToBooksTable(onSuccess: (books: Books) -> Unit, onError: (error: String) -> Unit) {
        client.getBooksAsync().enqueue(object : Callback<BooksListResponse> {
            override fun onFailure(call: Call<BooksListResponse>, t: Throwable) {
                t.printStackTrace()
                onError("${t.message}")
            }
            override fun onResponse(
                call: Call<BooksListResponse>,
                response: Response<BooksListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items?.forEach { items ->
                        val volumeData = items.volumeInfo
                        val id = items.id
                        val title = volumeData?.title
                        val author = volumeData?.getAuthorsString()
                        val description = volumeData?.description
                        val booksObject = Books(id, title, author, description)
                        onSuccess(booksObject)
                    }
                    Log.d(TAG, "🧲.......Response isSuccessful..")
                }
            }
        })
    }


}