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
        bundle = this.requireArguments()
        val book = bundle.getParcelable<Books>(Constants.DATA_KEY)
        title.text = book?.title
        author.text =  book?.author.toString()
        description.text = book?.description
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        bundle = this.arguments!!
//        val book = bundle.getParcelable<Books>(Constants.DATA_KEY)
//        outState.putString(Constants.TITLE, book?.title)
//        outState.putString(Constants.AUTHOR, book?.author)
//        outState.putString(Constants.DESCIPTION, book?.description)
//        super.onSaveInstanceState(outState)
//    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        val bundle = savedInstanceState ?: return
//        title.text = bundle.getString(Constants.TITLE)
//        author.text = bundle.getString(Constants.AUTHOR)
//        description.text = bundle.getString(Constants.DESCIPTION)
//    }

    private fun initView(view: View) {
        title = view.findViewById(R.id.d_book_title) as TextView
        author = view.findViewById(R.id.d_book_author) as TextView
        description = view.findViewById(R.id.d_book_description) as TextView
    }
}