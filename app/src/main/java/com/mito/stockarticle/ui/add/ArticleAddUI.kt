package com.mito.stockarticle.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ArticleAdd(articleAddViewModel: ArticleAddViewModel = viewModel()) {
  val title: String by articleAddViewModel.title.observeAsState("")
  val url: String by articleAddViewModel.url.observeAsState("")
  val memo: String by articleAddViewModel.memo.observeAsState("")
  Surface(color = MaterialTheme.colors.background) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
      val (inputLayer, buttonLayer) = createRefs()

      ArticleAddInputLayer(
        title = title,
        onTitleChange = articleAddViewModel::onTitleChange,
        url = url,
        onUrlChange = articleAddViewModel::onUrlChange,
        memo = memo,
        onMemoChange = articleAddViewModel::onMemoChange,
        modifier = Modifier.constrainAs(inputLayer) {
          top.linkTo(
            parent.top,
            16.dp
          )
          start.linkTo(
            parent.start,
            16.dp
          )
          end.linkTo(
            parent.end,
            16.dp
          )
        }
      )

      ArticleAddButtonLayer(
        onClickAdd = articleAddViewModel::onClickAdd,
        onClickCancel = articleAddViewModel::onClickCancel,
        modifier = Modifier.constrainAs(buttonLayer) {
          end.linkTo(
            parent.end,
            16.dp
          )
          bottom.linkTo(
            parent.bottom,
            16.dp
          )
        }
      )
    }
  }
}

@Composable
fun ArticleAddInputLayer(
  title: String,
  onTitleChange: (String) -> Unit,
  url: String,
  onUrlChange: (String) -> Unit,
  memo: String,
  onMemoChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    TextField(
      value = title,
      onValueChange = onTitleChange,
      label = { Text(text = "title") }
    )
    Spacer(modifier = Modifier.size(32.dp))
    TextField(
      value = url,
      onValueChange = onUrlChange,
      label = { Text(text = "url") }
    )
    Spacer(modifier = Modifier.size(32.dp))
    TextField(
      value = memo,
      onValueChange = onMemoChange,
      label = { Text(text = "memo") }
    )
  }
}

@Composable
fun ArticleAddButtonLayer(
  onClickAdd: () -> Unit,
  onClickCancel: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(modifier = modifier) {
    Button(onClick = onClickCancel) {
      Text(text = "cancel")
    }
    Spacer(modifier = Modifier.size(16.dp))
    Button(onClick = onClickAdd) {
      Text(text = "add")
    }
  }
}

@Preview
@Composable
fun ArticleAddPreview() {
  ArticleAdd()
}

@Preview
@Composable
fun ArticleAddInputPreview() {
  ArticleAddInputLayer(
    "",
    {},
    "",
    {},
    "",
    {}
  )
}

@Preview
@Composable
fun ArticleAddButtonPreview() {
  ArticleAddButtonLayer(
    {},
    {}
  )
}
