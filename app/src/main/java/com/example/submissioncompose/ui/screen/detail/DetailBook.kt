package com.example.submissioncompose.ui.screen.detail

import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.submissioncompose.R
import com.example.submissioncompose.ViewModelFactory
import com.example.submissioncompose.di.Injection
import com.example.submissioncompose.ui.common.UiResult
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
@Composable
fun DetailScreen(
    bookId: Long,
    viewModel: DetailBookViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.providerRepository()
        )
    ),
    navigateBack: ()-> Unit
){
    viewModel.uiState.collectAsState(initial = UiResult.Loading).value.let { uiResult ->
        when (uiResult){
            is UiResult.Loading ->{
                viewModel.getBookById(bookId)
            }
            is UiResult.Success -> {
                val data = uiResult.data
                DetailBook(img = data.book.image,
                    title = data.book.title,
                    price = data.book.price.toString(),
                    desc = data.book.desc,
                    onBackClick = navigateBack)
            }
            is UiResult.Error -> {}
        }
    }

}

@Composable
fun DetailBook (
    @DrawableRes img: Int,
    title: String,
    price: String,
    desc: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Column (
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ){
            Box{
                Image(painter = painterResource(img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                )
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ){
                Text(text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    )
                Text(text = price,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}




@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailListBooks(){
    SubmissionComposeTheme {
        DetailBook(
            img = R.drawable.sagaras,
            title = "Novel Sagaras",
            price = "99.000",
            desc = stringResource(id = R.string.desc_sagaras),
            onBackClick = {})

    }
}