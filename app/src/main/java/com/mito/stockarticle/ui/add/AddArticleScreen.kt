package com.mito.stockarticle.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mito.stockarticle.R
import com.mito.stockarticle.ui.widget.BackButton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@Composable
fun AddArticleScreen(
  addArticleEvent: AddArticleEvent,
  addArticleState: AddArticleState,
  navigateToList: Flow<Unit>,
  backAction: () -> Unit
) {

  LaunchedEffect(key1 = navigateToList) {
    navigateToList.collect {
      backAction()
    }
  }

  Scaffold(
    topBar = {
      TopBar(
        addArticleEvent::onBackClick,
        addArticleState.addable,
        addArticleEvent::onAddClick
      )
    },
    content = {
      AddArticleContent(
        articleState = addArticleState,
        articleEvent = addArticleEvent
      )
    }
  )
}

@Composable
private fun TopBar(
  onBackClick: () -> Unit,
  addable: Boolean,
  onAddClick: () -> Unit
) {
  TopAppBar(
    navigationIcon = {
      BackButton(onBackClick)
    },
    title = {
      Text(text = stringResource(R.string.article_add_title))
    },
    actions = {
      if (addable) {
        AddArticleAction(onAddClick)
      }
    }
  )
}

@Composable
private fun AddArticleAction(
  onAddClick: () -> Unit
) {
  IconButton(onClick = onAddClick) {
    Icon(
      imageVector = Icons.Default.Check,
      contentDescription = stringResource(id = R.string.article_add__action_description)
    )
  }
}

@Composable
private fun AddArticleContent(
  articleState: AddArticleState,
  articleEvent: AddArticleEvent
) {
  Surface(color = MaterialTheme.colors.background) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      ArticleInfoInput(
        title = articleState.title,
        onTitleChange = articleEvent::onTitleChange,
        url = articleState.url,
        onUrlChange = articleEvent::onUrlChange,
        memo = articleState.memo,
        onMemoChange = articleEvent::onMemoChange
      )
    }
  }
}

@Composable
private fun ArticleInfoInput(
  title: String,
  onTitleChange: (String) -> Unit,
  url: String,
  onUrlChange: (String) -> Unit,
  memo: String,
  onMemoChange: (String) -> Unit
) {
  Column {
    BackGroundTransparentTextField(
      value = title,
      onValueChange = onTitleChange,
      label = { Text(text = "title") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(32.dp))
    BackGroundTransparentTextField(
      value = url,
      onValueChange = onUrlChange,
      label = { Text(text = "url") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(32.dp))
    BackGroundTransparentTextField(
      value = memo,
      onValueChange = onMemoChange,
      label = { Text(text = "memo") },
      modifier = Modifier.fillMaxWidth()
    )
  }
}

@Composable
fun BackGroundTransparentTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: @Composable () -> Unit,
  modifier: Modifier
) {
  TextField(
    value = value,
    onValueChange = onValueChange,
    label = label,
    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
    modifier = modifier
  )
}

@Preview
@Composable
fun AddArticleInputPreview() {
  ArticleInfoInput(
    "",
    {},
    "",
    {},
    "",
    {}
  )
}
