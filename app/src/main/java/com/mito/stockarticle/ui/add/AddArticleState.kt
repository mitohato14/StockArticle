package com.mito.stockarticle.ui.add

data class AddArticleState(
  val title: String = "",
  val url: String = "",
  val memo: String = "",
  val addable: Boolean = false
)
