package biniam.berhane.kaiserpermanenteexercise.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import biniam.berhane.kaiserpermanenteexercise.R
import biniam.berhane.kaiserpermanenteexercise.utils.Constants
import biniam.berhane.kaiserpermanenteexercise.utils.getTopFragment


class MainActivity : AppCompatActivity() {
    private fun isTopFragmentConsumedBackPress() =
        getTopFragment<BackPressHandler>()?.onBackPressed() == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openBooksFragment()
    }
    private fun openBooksFragment() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(Constants.BOOKS_LIST)
            .replace(R.id.container, BooksFragment())
            .commit()
    }
    override fun onBackPressed() {
        if (!isTopFragmentConsumedBackPress()) {
            super.onBackPressed()
            openBooksFragment()
        }
    }
}
interface BackPressHandler {
    fun onBackPressed(): Boolean
}