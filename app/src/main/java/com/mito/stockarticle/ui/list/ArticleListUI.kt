package com.mito.stockarticle.ui.list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.ui.utils.LinkableText
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mito.stockarticle.R
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel
import java.net.URL

@Composable
fun ArticleList(
  addArticleAction: () -> Unit
) {
  val articleListViewModel: ArticleListViewModel = getViewModel()
  val articleList: List<Article> by articleListViewModel.articleList.observeAsState(listOf())

  LaunchedEffect(key1 = articleListViewModel.navigateToAdd) {
    articleListViewModel.navigateToAdd.collect {
      addArticleAction()
    }
  }
  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = {
            Text(text = stringResource(R.string.arcticle_list_title))
          }
        )
      },
      floatingActionButton = {
        FloatingActionButton(onClick = articleListViewModel::onAddClick) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add)
          )
        }
      },
      content = {
        LazyColumn(
          contentPadding = PaddingValues(10.dp),
          modifier = Modifier.fillMaxSize()
        ) {
          items(
            articleList,
            key = { it.id.value }
          ) { item ->
            ArticleRow(article = item)
          }
        }
      }
    )
  }
}

@Composable
fun ArticleRow(
  article: Article,
) {
  var expand by remember { mutableStateOf(false) }
  Card(
    backgroundColor = MaterialTheme.colors.surface,
    modifier = Modifier
      .fillMaxWidth()
      .padding(10.dp)
      .animateContentSize()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { expand = !expand }
        .padding(8.dp)
    ) {
      Text(
        text = article.title,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
      )

      if (expand) {
        ArticleDetail(
          memo = article.memo,
          url = article.url.toString(),
          modifier = Modifier
            .padding(
              horizontal = 8.dp,
              vertical = 16.dp
            )
        )
      }
    }
  }
}

@Composable
fun ArticleDetail(
  memo: String,
  url: String,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    LinkableText(url = url)
    Spacer(modifier = Modifier.size(4.dp))
    Text(
      text = memo,
      style = MaterialTheme.typography.caption
    )
  }
}

@Preview
@Composable
fun ArticleListPreview() {
  val articleList = arrayListOf<Article>()
  for (i in 0..10) {
    articleList.add(
      Article(
        id = ArticleId(value = "$i"),
        title = "hoge:$i",
        url = URL("http://google.com"),
        memo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
      )
    )
  }
//  ArticleList(articleList = articleList.toList())
}

@Preview(showBackground = true)
@Composable
fun ArticleRowPreview() {
  ArticleRow(
    Article(
      id = ArticleId(value = ""),
      title = "hoge",
      url = URL("http://google.com"),
      memo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    )
  )
}

@Preview
@Composable
fun ArticleDetailPreview() {
  ArticleDetail(
    "hoge",
    "https://google.com"
  )
}
