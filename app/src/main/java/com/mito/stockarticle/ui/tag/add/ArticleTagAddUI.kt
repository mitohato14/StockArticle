package com.mito.stockarticle.ui.tag.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mito.stockarticle.R
import com.mito.stockarticle.ui.add.BackGroundTransparentTextField
import com.mito.stockarticle.ui.utils.toHexString
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun TagAdd(
  backAction: () -> Unit
) {
  val articleTagAddViewModel: ArticleTagAddViewModel = getViewModel()
  val state: ArticleTagAddState = articleTagAddViewModel.state
  val event: ArticleTagAddEvent = articleTagAddViewModel

  LaunchedEffect(key1 = articleTagAddViewModel.navigateToList) {
    articleTagAddViewModel.navigateToList.collect {
      backAction()
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        navigationIcon = {
          IconButton(
            onClick = event::onClickBack
          ) {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = stringResource(id = R.string.back)
            )
          }
        },
        title = {
          Text(text = stringResource(id = R.string.tag_add_title))
        }
      )
    },
    content = {
      ArticleTagAddContentCompose(
        state,
        event
      )
    }
  )
}

@Composable
private fun ArticleTagAddContentCompose(
  state: ArticleTagAddState,
  event: ArticleTagAddEvent
) {
  ConstraintLayout(modifier = Modifier.fillMaxSize()) {
    val (inputLayer, addButton) = createRefs()
    TagAddInputLayer(
      name = state.name,
      onNameChange = event::onNameChange,
      red = state.red,
      onRedChange = event::onRedChange,
      green = state.green,
      onGreenChange = event::onGreenChange,
      blue = state.blue,
      onBlueChange = event::onBlueChange,
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

    Button(
      onClick = event::onClickAdd,
      modifier = Modifier.constrainAs(addButton) {
        end.linkTo(
          parent.end,
          16.dp
        )
        bottom.linkTo(
          parent.bottom,
          16.dp
        )
      }
    ) {
      Text(text = stringResource(id = R.string.add))
    }
  }
}

@Composable
fun TagAddInputLayer(
  name: String,
  onNameChange: (String) -> Unit,
  red: Float,
  onRedChange: (Float) -> Unit,
  green: Float,
  onGreenChange: (Float) -> Unit,
  blue: Float,
  onBlueChange: (Float) -> Unit,
  modifier: Modifier = Modifier
) {
  val selectedColor = Color(
    red = red,
    green = green,
    blue = blue
  )
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    BackGroundTransparentTextField(
      value = name,
      onValueChange = onNameChange,
      label = { Text(text = "name") },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.size(32.dp))

    Text(
      text = selectedColor.toHexString(),
      modifier = Modifier
        .background(color = selectedColor)
    )
    Spacer(modifier = Modifier.size(16.dp))

    Slider(
      value = selectedColor.red,
      onValueChange = onRedChange,
      valueRange = 0f..1f,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
    )
    Slider(
      value = selectedColor.green,
      onValueChange = onGreenChange,
      valueRange = 0f..1f,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
    )
    Slider(
      value = selectedColor.blue,
      onValueChange = onBlueChange,
      valueRange = 0f..1f,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
    )
  }
}

@Preview
@Composable
fun InputPreview() {
  TagAddInputLayer(
    name = "",
    onNameChange = { /*TODO*/ },
    red = 0f,
    onRedChange = { /*TODO*/ },
    green = 0f,
    onGreenChange = { /*TODO*/ },
    blue = 0f,
    onBlueChange = { /*TODO*/ },
  )
}
