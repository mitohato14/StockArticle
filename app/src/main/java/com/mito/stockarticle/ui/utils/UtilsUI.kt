package com.mito.stockarticle.ui.utils

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.mito.stockarticle.ui.theme.Blue500

@Composable
fun LinkableText(
  modifier: Modifier = Modifier,
  url: String,
  text: String? = null
) {
  val context = LocalContext.current
  Text(
    text = text ?: url,
    modifier = modifier
      .clickable {
        context.startActivity(
          Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
          )
        )
      },
    style = TextStyle(textDecoration = TextDecoration.Underline),
    color = Blue500
  )
}

@Preview(name = "url text")
@Preview(name = "linkable text")
@Composable
fun LinkableTextPreview() {
  LinkableText(
    url = "https:google.com"
  )
}
