package com.mito.stockarticle.domain

import java.net.URL

data class Article(
  val id: ArticleId,
  val title: String,
  val url: URL,
  val memo: String,
  val isArchived: Boolean
)
