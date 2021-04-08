package com.mito.stockarticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.mito.stockarticle.ui.add.ArticleAdd
import com.mito.stockarticle.ui.list.ArticleList
import com.mito.stockarticle.ui.theme.StockArticleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      StockArticleTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()

        val navActions = remember(navController) { MainNavActions(navController) }

        NavHost(
          navController = navController,
          startDestination = MainDestinations.LIST_ROUTE
        ) {
          composable(MainDestinations.LIST_ROUTE) {
            ArticleList(addArticleAction = navActions.addArticle)
          }
          composable(MainDestinations.ADD_ROUTE) {
            ArticleAdd()
          }
        }
      }
    }
  }
}

object MainDestinations {
  const val LIST_ROUTE = "list"
  const val ADD_ROUTE = "add"
}

class MainNavActions(navController: NavController) {
  val addArticle: () -> Unit = {
    navController.navigate(MainDestinations.ADD_ROUTE)
  }
  val toArticleList: () -> Unit = {
    navController.navigate(MainDestinations.LIST_ROUTE)
  }
}


@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  StockArticleTheme {
    Greeting("Android")
  }
}