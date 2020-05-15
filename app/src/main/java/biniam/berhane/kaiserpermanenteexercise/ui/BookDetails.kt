package biniam.berhane.kaiserpermanenteexercise.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import biniam.berhane.kaiserpermanenteexercise.R
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.utils.Constants

/**
 * Designed and developed by Biniam Berhane on 15/05/2020.
 */
class BookDetails : Fragment() {

    private var bundle = Bundle()

    private val TAG = "BookDetails"

    lateinit var title: TextView
    lateinit var author: TextView
    lateinit var description: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutView = inflater.inflate(R.layout.book_details, container, false)
        initView(layoutView)
        Log.d(TAG, "Detailed Book Opened.......")
        parseBundle()
        return layoutView
    }

    private fun parseBundle() {
        bundle = this.arguments!!
        val book = bundle.getParcelable<Books>(Constants.DATA_KEY)
        title.text = book?.title
        author.text = book?.author
        description.text = book?.description
    }


    private fun initView(view: View) {
        title = view.findViewById(R.id.detailed_book_title) as TextView
        author = view.findViewById(R.id.detailed_book_author) as TextView
        description = view.findViewById(R.id.detailed_book_description) as TextView
    }
}