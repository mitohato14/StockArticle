package com.mito.stockarticle.infra.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.domain.repo.ArticleRepository
import com.mito.stockarticle.infra.db.dao.ArticleDao
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
  private val dao: ArticleDao
) : ArticleRepository {
  override suspend fun add(article: Article) {
    dao.insert(article.toEntity())
  }

  override suspend fun delete(article: Article) {
    dao.delete(article.toEntity())
  }

  override suspend fun delete(id: ArticleId) {
    dao.delete(id.value)
  }

  override suspend fun findAll(): List<Article> {
    return dao.getAll().map {
      it.toDomainModel()
    }
  }

  override suspend fun findById(id: ArticleId): Article {
    return dao.findById(id.value).toDomainModel()
  }
}