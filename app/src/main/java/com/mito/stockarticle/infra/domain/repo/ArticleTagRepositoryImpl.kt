package com.mito.stockarticle.infra.domain.repo

import com.mito.stockarticle.domain.ArticleTag
import com.mito.stockarticle.domain.ArticleTagId
import com.mito.stockarticle.domain.repo.ArticleTagRepository
import com.mito.stockarticle.infra.db.dao.ArticleTagDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleTagRepositoryImpl(
  private val dao: ArticleTagDao
) : ArticleTagRepository {
  override suspend fun add(
    name: String,
    red: Float,
    green: Float,
    blue: Float
  ) {
    val articleTag = ArticleTag(
      id = ArticleTagId(value = ""),
      name = name,
      red = red,
      green = green,
      blue = blue
    )
    dao.insert(articleTag.toEntity())
  }

  override suspend fun update(articleTag: ArticleTag) {
    dao.update(articleTag.toEntity())
  }

  override suspend fun delete(articleTag: ArticleTag) {
    dao.delete(articleTag.toEntity())
  }

  override suspend fun delete(id: ArticleTagId) {
    dao.delete(id.value)
  }

  override fun findAll(): Flow<List<ArticleTag>> = dao.getAll().map {
    it.map { entity ->
      entity.toDomainModel()
    }
  }

  override suspend fun findById(id: ArticleTagId): ArticleTag =
    dao.findById(id.value).toDomainModel()
}