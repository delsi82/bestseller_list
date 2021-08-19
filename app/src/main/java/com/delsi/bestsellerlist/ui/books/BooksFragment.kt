package com.delsi.bestsellerlist.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.delsi.bestsellerlist.R
import com.delsi.bestsellerlist.data.Status
import com.delsi.bestsellerlist.databinding.BooksFragmentBinding
import com.delsi.bestsellerlist.ui.books.adapters.BestsellerTypeAdapter
import com.delsi.bestsellerlist.ui.books.adapters.BookAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksFragment : Fragment(),AdapterView.OnItemSelectedListener {


    private val viewmodel: BooksViewModel by viewModels()
    private lateinit var binding: BooksFragmentBinding
    private lateinit var adapterBestSellerType: BestsellerTypeAdapter
    private var bookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BooksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.booksList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }
        binding.menu.onItemSelectedListener = this

        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewmodel.typeOfBooks.collect { it ->
                showLoader(false)
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { listOfType ->
                            adapterBestSellerType = BestsellerTypeAdapter(
                                requireContext(),
                                R.layout.type_label_item,
                                listOfType
                            )
                            binding.menu.adapter = adapterBestSellerType
                            viewmodel.getBooks(listOfType.first())
                        }
                    }
                    Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    else -> {
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewmodel.books.collect {

                when (it.status) {
                    Status.SUCCESS -> {
                        showLoader(false)
                        bookAdapter.updateData(it.data)
                        bookAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showLoader(false)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                            .show()
                    }
                    else -> showLoader(true)
                }
            }
        }
    }

    private fun showLoader(isLoading: Boolean) = activity?.runOnUiThread {
        binding.loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        adapterBestSellerType.getItem(position)?.let {
            viewmodel.getBooks(it)
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}