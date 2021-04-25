package com.mito.stockarticle.domain.repo

import com.mito.stockarticle.domain.ArticleTag
import com.mito.stockarticle.domain.ArticleTagId
import kotlinx.coroutines.flow.Flow

interface ArticleTagReadOnlyRepository {
  fun findAll(): Flow<List<ArticleTag>>

  suspend fun findById(id: ArticleTagId): ArticleTag
}