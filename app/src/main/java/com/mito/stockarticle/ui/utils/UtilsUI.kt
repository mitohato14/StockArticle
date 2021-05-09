package com.mito.stockarticle.ui.utils

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
