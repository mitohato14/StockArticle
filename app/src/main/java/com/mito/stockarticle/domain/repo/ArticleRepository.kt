package com.mito.stockarticle.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId

interface ArticleRepository : ArticleReadOnlyRepository {
  suspend fun add(
    title: String,
    url: String,
    memo: String
  )

  suspend fun update(article: Article)

  suspend fun delete(article: Article)
  suspend fun delete(id: ArticleId)
}