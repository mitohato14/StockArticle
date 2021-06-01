package com.mito.stockarticle.ui.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mito.stockarticle.R


@Composable
fun BackButton(
  onBackClick: () -> Unit
) {
  IconButton(
    onClick = onBackClick
  ) {
    Icon(
      imageVector = Icons.Default.ArrowBack,
      contentDescription = stringResource(R.string.back)
    )
  }
}

