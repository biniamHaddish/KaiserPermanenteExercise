package biniam.berhane.kaiserpermanenteexercise.network

import biniam.berhane.kaiserpermanenteexercise.model.BooksListResponse
import biniam.berhane.kaiserpermanenteexercise.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
interface GoogleBooksService {
    @GET(Constants.BOOKS_URL)
    fun getBooksAsync(): Call<BooksListResponse>
}