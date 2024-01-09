package com.example.submissioncompose.data

import com.example.submissioncompose.model.BookDataStore
import com.example.submissioncompose.model.OrderBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


class BookRepository {

    private val orderBooks = mutableListOf<OrderBook>()

    init {
        if (orderBooks.isEmpty()){
            BookDataStore.listOfBooks.forEach{
                orderBooks.add(OrderBook(it, 0))
            }
        }
    }
    fun getAllBooks(): Flow<List<OrderBook>> {
        return flowOf(orderBooks)
    }
    fun getOrderBookById(BookId: Long): OrderBook{
        return orderBooks.first{
            it.book.bookID == BookId
        }
    }
    fun updateOrderBook(BookId: Long, newCountValue: Int): Flow<Boolean>{
        val index = orderBooks.indexOfFirst { it.book.bookID == BookId }
        val result = if (index >= 0){
            val orderBook = orderBooks[index]
            orderBooks[index] = orderBook.copy(book = orderBook.book, count = newCountValue)
            true
        }else{
            false
        }
        return flowOf(result)
    }
    fun getAddOrderRewards():Flow<List<OrderBook>> {
        return getAllBooks().map { BooksOrder -> orderBooks.filter { bookOrder ->
            bookOrder.count != 0
        } }
    }
    companion object{
        @Volatile
        private var instance: BookRepository? =null

        fun getInstance(): BookRepository = instance?: synchronized(this){
            BookRepository().apply { instance = this }
        }
    }
}