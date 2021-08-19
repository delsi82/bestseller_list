package com.delsi.bestsellerlist.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delsi.bestsellerlist.data.Resource
import com.delsi.bestsellerlist.data.repositories.BookRepository
import com.delsi.bestsellerlist.data.vo.BestsellerType
import com.delsi.bestsellerlist.data.vo.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    private val _typeOfBooks = MutableStateFlow<Resource<List<BestsellerType>?>>(Resource.loading())

    val typeOfBooks: StateFlow<Resource<List<BestsellerType>?>> = _typeOfBooks

    private val _books = MutableStateFlow<Resource<List<Book>?>>(Resource.loading())

    val books: StateFlow<Resource<List<Book>?>> = _books


    init {
        viewModelScope.launch {
            repository.getBestSellerTypes().catch { e ->
                _typeOfBooks.value = Resource.error(e.message ?: "Exception")
            }.collect {
                _typeOfBooks.value = it
            }
        }
    }

    fun getBooks(bestSellerType: BestsellerType) = viewModelScope.launch {
        _books.value = Resource.loading()
        repository.getBookByType(bestSellerType.listNameEncoded ?: "").catch { e ->
            _books.value = Resource.error(e.message ?: "Exception")
        }.collect {
            _books.value = it
        }
    }


}