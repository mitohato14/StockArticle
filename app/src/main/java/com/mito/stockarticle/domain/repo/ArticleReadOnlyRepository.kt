package com.mito.stockarticle.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import kotlinx.coroutines.flow.Flow

interface ArticleReadOnlyRepository {
  fun findAll(): Flow<List<Article>>
  fun findUnArchivedAll(): Flow<List<Article>>

  suspend fun findById(id: ArticleId): Article
}