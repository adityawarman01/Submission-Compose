package com.example.submissioncompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissioncompose.data.BookRepository
import com.example.submissioncompose.ui.screen.detail.DetailBookViewModel
import com.example.submissioncompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: BookRepository):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailBookViewModel::class.java)) {
            return DetailBookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class "+ modelClass.name)
    }
}