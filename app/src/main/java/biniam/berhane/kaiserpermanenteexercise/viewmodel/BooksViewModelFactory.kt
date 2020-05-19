package biniam.berhane.kaiserpermanenteexercise.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import biniam.berhane.kaiserpermanenteexercise.database.DataBaseHelper

/**
 * Designed and developed by Biniam Berhane on 15/05/2020.
 */
class BooksViewModelFactory(
    private val dataSource: DataBaseHelper,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}