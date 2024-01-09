package com.example.submissioncompose.di

import com.example.submissioncompose.data.BookRepository

object Injection {
    fun providerRepository(): BookRepository{
        return BookRepository.getInstance()
    }
}