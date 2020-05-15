package biniam.berhane.kaiserpermanenteexercise.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import biniam.berhane.kaiserpermanenteexercise.model.Books

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
@Dao
interface BooksDAO {
    /**
     * Here I am inserting the data from the API To Local Db Table Books
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: Books)

    /**
     * get a list of Livedata of the books list
     */
    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Books>>

    /**
     * Will give us Single Book by Id
     */
    @Query("SELECT * from books WHERE id = :id")
    fun getBook(id: String): Books?


}