package com.delsi.bestsellerlist.ui.books.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delsi.bestsellerlist.data.vo.Book
import com.delsi.bestsellerlist.databinding.BookAdapterBinding

class BookAdapter :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var _items: List<Book>? = null

    inner class ViewHolder(val binding: BookAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book) {
            binding.book = item
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        BookAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        _items?.let {
            viewHolder.bind(it[position])
        }

    }

    override fun getItemCount() = _items?.size ?: 0

    fun updateData(newDataSet: List<Book>?) = newDataSet.also { _items = it }

}