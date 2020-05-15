package biniam.berhane.kaiserpermanenteexercise

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import biniam.berhane.kaiserpermanenteexercise.database.BooksDAO
import biniam.berhane.kaiserpermanenteexercise.database.BooksDatabase
import biniam.berhane.kaiserpermanenteexercise.model.Books
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("biniam.berhane.kaiserpermanenteexercise", appContext.packageName)
    }


}
