package com.example.submissioncompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissioncompose.ViewModelFactory
import com.example.submissioncompose.di.Injection
import com.example.submissioncompose.model.OrderBook
import com.example.submissioncompose.ui.common.UiResult
import com.example.submissioncompose.ui.components.BookItem


@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.providerRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    ){
        viewModel.uiState.collectAsState(initial = UiResult.Loading).value.let { uiresult ->
            when(uiresult){
                is UiResult.Loading -> {
                    viewModel.getAllListBooks()
                }
                is UiResult.Success -> {
                    HomeContent(
                        bookOrder = uiresult.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiResult.Error -> {}
            }
        }
}

@Composable
fun HomeContent(
    bookOrder: List<OrderBook>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
){
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
      //  horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.testTag("BookList")
    ){
        items(bookOrder){ data ->
            BookItem(
                image = data.book.image,
                title = data.book.title,
                price = data.book.price,
                prevdesc = data.book.descprev,
                modifier = Modifier.clickable {
                    navigateToDetail(data.book.bookID)
                }
            )
        }
    }
}

