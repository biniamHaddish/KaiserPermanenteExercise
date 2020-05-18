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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val book = getItem(position) as Books
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = inflater.inflate(layout.books_item, parent, false)
            holder = ViewHolder()
            holder.titleTextView = view.findViewById(R.id.book_title) as TextView
            holder.subtitleTextView = view.findViewById(R.id.book_author) as TextView
            holder.detailTextView = view.findViewById(R.id.book_description) as TextView
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        val bookTitle = holder.titleTextView
        val bookAuthor = holder.subtitleTextView
        val bookDescription = holder.detailTextView

        val des = book.description
        val author = book.author
        bookTitle.text = book.title
        bookAuthor.text ="By:-" + authorDoesNotExisit(author)
        bookDescription.text = textIsEmpty(des)
        return view
    }

    private fun textIsEmpty(text: String?): String {
        return text ?: " Description not provided"
    }

    private fun authorDoesNotExisit(text: String?): String {
        return  text ?: " No Author."
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

private class ViewHolder {
    lateinit var titleTextView: TextView
    lateinit var subtitleTextView: TextView
    lateinit var detailTextView: TextView
}
