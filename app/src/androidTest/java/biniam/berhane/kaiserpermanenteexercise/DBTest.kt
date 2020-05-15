package biniam.berhane.kaiserpermanenteexercise

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import biniam.berhane.kaiserpermanenteexercise.database.BooksDAO
import biniam.berhane.kaiserpermanenteexercise.database.BooksDatabase
import biniam.berhane.kaiserpermanenteexercise.model.Books
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var booksDao: BooksDAO
    private lateinit var db: BooksDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, BooksDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        booksDao = db.booksDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertToDb() {
        val books = Books()
        booksDao.insertBooks(books)
        val book = booksDao.getBook("")
        Assert.assertEquals(book?.title, "")
    }

}