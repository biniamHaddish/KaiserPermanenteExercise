package biniam.berhane.kaiserpermanenteexercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import biniam.berhane.kaiserpermanenteexercise.model.Books

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */

@Database(entities = [Books::class], version = 2, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {
    abstract val booksDAO: BooksDAO

    companion object {
        @Volatile
        private var INSTANCE: BooksDatabase? = null
        fun getInstance(context: Context): BooksDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooksDatabase::class.java,
                        "books_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}