package com.mito.stockarticle.ui.list

import com.mito.stockarticle.domain.Article

data class ArticleListState(
  val articleList: List<Article> = listOf()
)
