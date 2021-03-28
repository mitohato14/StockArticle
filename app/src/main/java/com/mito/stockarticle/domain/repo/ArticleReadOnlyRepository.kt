package com.mito.stockarticle.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId

interface ArticleReadOnlyRepository {
  suspend fun findAll(): List<Article>

  suspend fun findById(id: ArticleId): Article
}