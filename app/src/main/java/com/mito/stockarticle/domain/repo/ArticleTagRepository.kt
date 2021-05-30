package com.mito.stockarticle.domain.repo

import com.mito.stockarticle.domain.ArticleTag
import com.mito.stockarticle.domain.ArticleTagId

interface ArticleTagRepository : ArticleTagReadOnlyRepository {
  suspend fun add(
    name: String,
    red: Float,
    green: Float,
    blue: Float,
  )

  suspend fun update(articleTag: ArticleTag)

  suspend fun delete(articleTag: ArticleTag)
  suspend fun delete(id: ArticleTagId)
}