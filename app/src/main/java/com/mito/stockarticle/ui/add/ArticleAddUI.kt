package com.mito.stockarticle.ui.add

import android.webkit.URLUtil
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mito.stockarticle.R
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticleAdd(
  backAction: () -> Unit
) {
  val articleAddViewModel: ArticleAddViewModel = getViewModel()
  val state: ArticleAddState = articleAddViewModel.state
  val action: ArticleAddAction = articleAddViewModel

  LaunchedEffect(key1 = articleAddViewModel.navigateToList) {
    articleAddViewModel.navigateToList.collect {
      backAction()
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        navigationIcon = {
          IconButton(
            onClick = action::onClickBack
          ) {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = stringResource(R.string.back)
            )
          }
        },
        title = {
          Text(text = stringResource(R.string.article_add_title))
        }
      )
    },
    content = {
      ArticleAddContentCompose(
        state = state,
        action = action
      )
    }
  )
}

@Composable
private fun ArticleAddContentCompose(
  state: ArticleAddState,
  action: ArticleAddAction
) {
  Surface(color = MaterialTheme.colors.background) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
      val (inputLayer, buttonLayer) = createRefs()

      ArticleAddInputLayer(
        title = state.title,
        onTitleChange = action::onTitleChange,
        url = state.url,
        onUrlChange = action::onUrlChange,
        memo = state.memo,
        onMemoChange = action::onMemoChange,
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
          width = Dimension.fillToConstraints
        }
      )

      ArticleAddButtonLayer(
        addable = URLUtil.isValidUrl(state.url),
        onClickAdd = action::onClickAdd,
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
private fun ArticleAddInputLayer(
  title: String,
  onTitleChange: (String) -> Unit,
  url: String,
  onUrlChange: (String) -> Unit,
  memo: String,
  onMemoChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
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

@Composable
fun ArticleAddButtonLayer(
  addable: Boolean,
  onClickAdd: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(modifier = modifier) {
    Button(
      onClick = onClickAdd,
      enabled = addable
    ) {
      Text(text = "add")
    }
  }
}

@Preview
@Composable
fun ArticleAddPreview() {
  ArticleAdd(backAction = {})
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
    true,
    {},
  )
}
