package com.example.submissioncompose.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.submissioncompose.R
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSreen (
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    Column (modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(title = {
            Text(text = stringResource(R.string.menu_profile),
                modifier = Modifier
                    .size(250.dp)
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                         contentDescription = null)

                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.DarkGray
            )
        )
        val image = painterResource(R.drawable.photoprofil)
        val name = "Muhammad Adithya Warman"
        val email = "a315bsy2346@bangkit.academy"

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ){
            Column (modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
            ) {
                Box(modifier = modifier
                    .size(190.dp)
                    .background(Color.White)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                ){
                    Image(painter = image, contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = name,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(text = email,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

