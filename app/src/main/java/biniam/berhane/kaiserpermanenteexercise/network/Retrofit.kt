package biniam.berhane.kaiserpermanenteexercise.network

import biniam.berhane.kaiserpermanenteexercise.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
object Retrofit {

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .generateNonExecutableJson()
        .create()

    private fun okHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            // to add Network request output
            .addInterceptor(NetworkInterceptor())
            .retryOnConnectionFailure(true)
            .addInterceptor(CacheInterceptor())
        return builder.build()
    }

    fun googleBookApi(): GoogleBooksService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(GoogleBooksService::class.java)
    }
}