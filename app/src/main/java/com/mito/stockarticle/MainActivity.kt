package com.mito.stockarticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mito.stockarticle.ui.add.AddArticleScreen
import com.mito.stockarticle.ui.add.AddArticleViewModel
import com.mito.stockarticle.ui.list.ArticleListScreen
import com.mito.stockarticle.ui.list.ArticleListViewModel
import com.mito.stockarticle.ui.theme.StockArticleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      StockArticleApp()
    }
  }

  @Composable
  private fun StockArticleApp() {
    StockArticleTheme {
      // A surface container using the 'background' color from the theme
      val navController = rememberNavController()

      val navActions = remember(navController) { MainNavActions(navController) }

      NavHost(
        navController = navController,
        startDestination = MainDestinations.LIST_ROUTE
      ) {
        composable(MainDestinations.LIST_ROUTE) {
          val articleListViewModel: ArticleListViewModel = hiltViewModel()
          ArticleListScreen(
            articleListViewModel = articleListViewModel,
            addArticleAction = navActions.addArticle
          )
        }
        composable(MainDestinations.ADD_ARTICLE_ROUTE) {
          val addArticleViewModel: AddArticleViewModel = hiltViewModel()
          AddArticleScreen(
            addArticleViewModel = addArticleViewModel,
            backAction = navActions.popBack
          )
        }
      }
    }
  }
}

object MainDestinations {
  const val LIST_ROUTE = "List"
  const val ADD_ARTICLE_ROUTE = "AddArticle"
}

class MainNavActions(navController: NavController) {
  val addArticle: () -> Unit = {
    navController.navigate(MainDestinations.ADD_ARTICLE_ROUTE)
  }
  val toArticleList: () -> Unit = {
    navController.navigate(MainDestinations.LIST_ROUTE)
  }
  val popBack: () -> Unit = {
    navController.popBackStack()
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