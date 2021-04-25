package com.mito.stockarticle.ui.utils

import androidx.compose.ui.graphics.Color

fun String.convertToColor(): Color {
  return Color(
    red = substring(
      0,
      1
    ).toInt(16),
    green = substring(
      2,
      3
    ).toInt(16),
    blue = substring(
      4,
      5
    ).toInt(16),
  )
}

fun Color.toHexString(): String {
  return "#" +
      (red * 255).toInt().toString(16) +
      (green * 255).toInt().toString(16) +
      (blue * 255).toInt().toString(16)
}
