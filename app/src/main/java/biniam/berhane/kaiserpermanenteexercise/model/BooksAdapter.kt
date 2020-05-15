package biniam.berhane.kaiserpermanenteexercise.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import biniam.berhane.kaiserpermanenteexercise.R
import biniam.berhane.kaiserpermanenteexercise.R.layout

class BooksAdapter(
    context: Context,
    private val dataSource: List<Books>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = inflater.inflate(layout.books_item, parent, false)
        val bookTitle = rowView.findViewById(R.id.book_title) as TextView
        val bookAuthor = rowView.findViewById(R.id.book_author) as TextView
        val bookDescription = rowView.findViewById(R.id.book_description) as TextView

        val book = getItem(position) as Books
        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookDescription.text = book.description


        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}
