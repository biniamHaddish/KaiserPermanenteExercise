package biniam.berhane.kaiserpermanenteexercise.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import biniam.berhane.kaiserpermanenteexercise.R
import biniam.berhane.kaiserpermanenteexercise.database.DataBaseHelper
import biniam.berhane.kaiserpermanenteexercise.model.Books
import biniam.berhane.kaiserpermanenteexercise.model.BooksAdapter
import biniam.berhane.kaiserpermanenteexercise.utils.Constants
import biniam.berhane.kaiserpermanenteexercise.viewmodel.BooksViewModel
import biniam.berhane.kaiserpermanenteexercise.viewmodel.BooksViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment() {
    private lateinit var booksViewModel: BooksViewModel
    private lateinit var listView: ListView
    private var bookCollections = ArrayList<Books>()
    private val list = ArrayList<Books>()
    private val TAG = "BooksFragment"
    private var mContext: Context? = this.context

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_books, container, false)
        listView = view.findViewById(R.id.books_listView)

        val application = requireNotNull(this.activity).application
        val dataSource = DataBaseHelper(application, null)
        Log.d(TAG, "Bookfragment Loaded..........🙈🙉🙊")
        val viewModelFactory = BooksViewModelFactory(dataSource, application)
        booksViewModel = ViewModelProvider(this, viewModelFactory).get(BooksViewModel::class.java)
        booksViewModel.bookslivedata.observe(viewLifecycleOwner, Observer { books ->
            list.addAll(books)
            mContext?.let { initAdapter(it, books) }
            bookCollections.addAll(list)
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val data = bookCollections[position]
            Log.e(TAG, "🙉😄🙈😄🙊 Click Event.... ${data} Clicked")
            val sendBooks = Books(data.id, data.title, data.author, data.description)
            goToDetails(sendBooks)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity).supportActionBar?.title = getString(R.string.your_title)
        navController = Navigation.findNavController(view)
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
        val bundle = bundleOf(Constants.DATA_KEY to books)
        findNavController().navigate(R.id.bookDetails,bundle)
    }
}
