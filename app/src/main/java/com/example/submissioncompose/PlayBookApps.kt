package com.example.submissioncompose


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.submissioncompose.ui.navigation.NavigationItem
import com.example.submissioncompose.ui.navigation.Screen
import com.example.submissioncompose.ui.screen.detail.DetailScreen
import com.example.submissioncompose.ui.screen.home.HomeScreen
import com.example.submissioncompose.ui.screen.profile.ProfileSreen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayBookApps(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailBook.route){
               BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    navigateToDetail = { bookId ->
                    navController.navigate(Screen.DetailBook.createRoute(bookId))
                }
                )
            }
            composable(Screen.Profile.route){
                ProfileSreen(navController = navController)
            }
            composable(
                route = Screen.DetailBook.route,
                arguments = listOf(navArgument("bookId"){ type = NavType.LongType}),
            ){
                val id = it.arguments?.getLong("bookId") ?: -1L
                DetailScreen (
                    bookId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ){
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
//            NavigationItem(
//                title = stringResource(R.string.menu_cart),
//                icon = Icons.Default.ShoppingCart,
//                screen = Screen.Cart
//            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItem.map { item ->
            NavigationBarItem(
                icon = {
                  Icon(imageVector = item.icon,
                      contentDescription = item.title
                  )
                },
                label = { Text(item.title)},
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PlayBooksApps(){
    SubmissionComposeTheme {
        PlayBookApps()
    }
}