package com.mito.stockarticle.domain

data class ArticleTag(
  val id: ArticleTagId,
  val name: String,
  val red: Float,
  val green: Float,
  val blue: Float
)
