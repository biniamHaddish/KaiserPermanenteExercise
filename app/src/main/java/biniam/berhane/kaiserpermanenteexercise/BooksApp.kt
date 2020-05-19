package biniam.berhane.kaiserpermanenteexercise

import android.app.Application
import biniam.berhane.kaiserpermanenteexercise.database.DataBaseHelper

class BooksApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = DataBaseHelper(applicationContext, null)

    }
}