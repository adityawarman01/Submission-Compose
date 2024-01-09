package com.example.submissioncompose.ui.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.submissioncompose.R
import com.example.submissioncompose.ui.theme.Shapes


@Composable
fun CartItem(
    bookId: Long,
    image: Int,
    title: String,
    price: Double,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
){
    Row (modifier = modifier.fillMaxSize()
    ){
        Image(painter = painterResource(image),
            contentDescription =null ,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ){
           Text(text = title,
               maxLines = 5,
               overflow = TextOverflow.Ellipsis,
               style = MaterialTheme.typography.titleMedium.copy(
                   fontWeight = FontWeight.Bold
               )
           )
            Text(text = stringResource(R.string.need_prices, price)
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview(){
    CartItem(bookId = 1, image = R.drawable.bumi,
        title = "Novel Bumi", price = 120.000, count = 1,
        onProductCountChanged = {book, count -> },
        )
}