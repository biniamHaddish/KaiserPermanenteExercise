package biniam.berhane.kaiserpermanenteexercise.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import biniam.berhane.kaiserpermanenteexercise.R
import biniam.berhane.kaiserpermanenteexercise.database.BooksDatabase
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.model.BooksAdapter
import biniam.berhane.kaiserpermanenteexercise.utils.Constants
import biniam.berhane.kaiserpermanenteexercise.viewmodel.BooksViewModel
import biniam.berhane.kaiserpermanenteexercise.viewmodel.BooksViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment() {
    companion object {
        fun newInstance(): BooksFragment {
            return BooksFragment()
        }
    }

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var listView: ListView
    private var bookCollections = ArrayList<Books>()
    private val list = ArrayList<Books>()
    private val TAG = "BooksFragment"
    private var mContext: Context? = this.context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_books, container, false)
        listView = view.findViewById(R.id.books_listView)
        Log.e(TAG, "Fragment Loaded......... ")

        val application = requireNotNull(this.activity).application
        val dataSource = BooksDatabase
            .getInstance(application)
            .booksDAO

        val viewModelFactory = BooksViewModelFactory(dataSource, application)
        booksViewModel = ViewModelProvider(this, viewModelFactory).get(BooksViewModel::class.java)
        booksViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            list.addAll(books)
            mContext?.let { initAdapter(it, books) }
            bookCollections.addAll(list)

//            books.forEach { books ->
//                Log.d(TAG, "🙉😄🙈😄🙊 ID.... ${books.id}")
//                Log.d(TAG, "🙉😄🙈😄🙊 Title.... ${books.title}")
//                Log.d(TAG, "🙉😄🙈😄🙊 Author.... ${books.author}")
//                Log.e(TAG, "🙉😄🙈😄🙊 Description.... ${books.description}")
//            }
        })


        listView.setOnItemClickListener { _, _, position, _ ->
            val data = bookCollections[position]
            Log.e(TAG, "🙉😄🙈😄🙊 Click Event.... ${data} Clicked")
            val sendBooks = Books(data.id, data.title, data.author, data.description)
            goToDetails(sendBooks)
            //Toast.makeText(context, "$data. 🙈", Toast.LENGTH_SHORT).show()
        }

        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    private fun initAdapter(context: Context, booksList: List<Books>) {
        val adapter = BooksAdapter(context, booksList)
        listView.adapter = adapter

    }


    private fun goToDetails(books: Books) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.DATA_KEY, books)
        val booksDetails = BookDetails()
        booksDetails.arguments = bundle
        //Replacing the fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, booksDetails)
            .commit()
    }
}
