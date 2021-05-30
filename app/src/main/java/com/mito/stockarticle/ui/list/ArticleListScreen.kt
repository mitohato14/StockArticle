package com.mito.stockarticle.ui.list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mito.stockarticle.R
import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.ui.widget.LinkableText
import kotlinx.coroutines.flow.collect
import java.net.URL

@Composable
fun ArticleListScreen(
  articleListViewModel: ArticleListViewModel,
  addArticleAction: () -> Unit
) {
  val state: ArticleListState = articleListViewModel.state
  val event: ArticleListEvent = articleListViewModel

  val scaffoldState = rememberScaffoldState()

  LaunchedEffect(key1 = articleListViewModel.navigateToAdd) {
    articleListViewModel.navigateToAdd.collect {
      addArticleAction()
    }
  }

  LaunchedEffect(key1 = articleListViewModel.archivedArticle) {
    articleListViewModel.archivedArticle.collect { articleId ->
      val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
        message = "Archive",
        actionLabel = "Undo"
      )

      if (snackbarResult == SnackbarResult.ActionPerformed) {
        articleListViewModel.onUndoArchiveClick(articleId = articleId)
      }
    }
  }

  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      scaffoldState = scaffoldState,
      topBar = {
        TopBar()
      },
      floatingActionButton = {
        AddFloatingActionButton(event::onAddClick)
      },
      content = {
        ArticleListContent(
          state = state,
          event = event
        )
      }
    )
  }
}

@Composable
private fun TopBar() {
  TopAppBar(
    title = {
      Text(
        text = stringResource(R.string.article_list_title)
      )
    }
  )
}

@Composable
private fun AddFloatingActionButton(
  onClick: () -> Unit
) {
  FloatingActionButton(onClick = onClick) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = stringResource(R.string.add)
    )
  }
}

@Composable
private fun ArticleListContent(
  state: ArticleListState,
  event: ArticleListEvent
) {
  Surface(color = MaterialTheme.colors.background) {
    LazyColumn(
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      items(
        state.articleList,
        key = { it.id.value }
      ) { item ->
        ArticleRow(
          article = item,
          event::onArchiveClick
        )
      }
    }
  }
}

@Composable
private fun ArticleRow(
  article: Article,
  onArchiveClick: (ArticleId) -> Unit
) {
  var expand by remember { mutableStateOf(false) }
  Card(
    backgroundColor = MaterialTheme.colors.surface,
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize()
  ) {
    ArticleRowContent(
      expand = expand,
      onCardClick = { expand = !expand },
      article = article,
      onArchiveClick = onArchiveClick
    )
  }
}

@Composable
private fun ArticleRowContent(
  expand: Boolean,
  onCardClick: () -> Unit,
  article: Article,
  onArchiveClick: (ArticleId) -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onCardClick() }
      .padding(8.dp)
  ) {
    Row(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = article.title,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
          .padding(start = 8.dp)
          .weight(1f)
      )

      IconButton(
        onClick = { onArchiveClick(article.id) },
        modifier = Modifier
          .padding(end = 8.dp)
      ) {
        Icon(
          imageVector = Icons.Outlined.Archive,
          contentDescription = stringResource(R.string.read),
        )
      }
    }

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

@Composable
private fun ArticleDetail(
  memo: String,
  url: String,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    LinkableText(url = url)
    if (memo.isNotBlank()) {
      Spacer(modifier = Modifier.size(4.dp))
      Text(
        text = memo,
        style = MaterialTheme.typography.caption
      )
    }
  }
}

@Preview
@Composable
private fun ArticleListPreview() {
  val articleList = arrayListOf<Article>()
  for (i in 0..10) {
    articleList.add(
      Article(
        id = ArticleId(value = "$i"),
        title = "hoge:$i",
        url = URL("http://google.com"),
        memo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        isArchived = false
      )
    )
  }
  ArticleListContent(
    state = ArticleListState(articleList = articleList),
    event = object : ArticleListEvent {
      override fun onAddClick() {}
      override fun onArchiveClick(articleId: ArticleId) {}
      override fun onUndoArchiveClick(articleId: ArticleId) {}
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun ArticleRowPreview() {
  ArticleRow(
    Article(
      id = ArticleId(value = ""),
      title = "hoge",
      url = URL("http://google.com"),
      memo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
      isArchived = false
    )
  ) {}
}

@Preview
@Composable
private fun ArticleDetailPreview() {
  ArticleDetail(
    "hoge",
    "https://google.com"
  )
}
