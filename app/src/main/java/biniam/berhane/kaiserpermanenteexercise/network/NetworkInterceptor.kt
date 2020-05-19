package biniam.berhane.kaiserpermanenteexercise.network

import android.util.Log
import biniam.berhane.kaiserpermanenteexercise.utils.Constants
import biniam.berhane.kaiserpermanenteexercise.utils.NetworkMonitor
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Designed and developed by Biniam Berhane on 18/05/2020.
 */
private const val TAG = "NetworkInterceptor"

class NetworkInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            400 -> {
                Log.d(TAG, "Bad Request ${response.code}")
            }
            401 -> {
                Log.d(TAG, " UnAuthorized Request ${response.code}")
            }

            403 -> {
                Log.d(TAG, " Forbidden Request ${response.code}")
            }

            404 -> {
                Log.d(TAG, " NotFound  ${response.code}")
            }
        }
        return response
    }

}