package biniam.berhane.kaiserpermanenteexercise.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


private const val TAG = "DataBaseHelper"

class DataBaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :

    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME " +
                    "($COLUMN_ID TEXT PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_AUTHOR TEXT, $COLUMN_DESCRIPTION TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertBook(id: String, title: String, author: String, description: String) {
        val values = ContentValues()
        values.put(COLUMN_ID, id)
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_AUTHOR, author)
        values.put(COLUMN_DESCRIPTION, description)

        val db = this.writableDatabase

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        Log.e(TAG, "DATA Inserted Good 🔥🧨🎯")
        db.close()
    }

    fun updateBook(row_id: String, title: String, author: String, description: String) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_AUTHOR, author)
        values.put(COLUMN_DESCRIPTION, description)

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(row_id))
        db.close()
    }

    fun deleteOneBook(row_id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(row_id))
        db.close()
    }

    fun getAllBooks(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "books_db.db"
        const val TABLE_NAME = "books"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_DESCRIPTION = "description"
    }


}