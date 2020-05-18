package biniam.berhane.kaiserpermanenteexercise.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import biniam.berhane.kaiserpermanenteexercise.R
import biniam.berhane.kaiserpermanenteexercise.utils.Constants


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openBooksFragment()
    }

    private fun openBooksFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, BooksFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        backStackHandler()
    }

    private fun backStackHandler() {
        val fragmentManager = supportFragmentManager
        if (supportFragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            return
        } else {
            super.onBackPressed()
        }
    }
}

