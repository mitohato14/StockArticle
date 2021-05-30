package com.mito.stockarticle.infra.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.domain.repo.ArticleRepository
import com.mito.stockarticle.infra.db.dao.ArticleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.URL
import java.util.UUID

class ArticleRepositoryImpl(
  private val dao: ArticleDao
) : ArticleRepository {
  override suspend fun add(
    title: String,
    url: String,
    memo: String
  ) {
    val article = Article(
      id = ArticleId(value = UUID.randomUUID().toString()),
      title = title,
      url = URL(url),
      memo = memo,
      isArchived = false
    )
    dao.insert(article.toEntity())
  }

  override suspend fun update(article: Article) {
    dao.update(articleEntity = article.toEntity())
  }

  override suspend fun delete(article: Article) {
    dao.delete(article.toEntity())
  }

  override suspend fun delete(id: ArticleId) {
    dao.delete(id.value)
  }

  override fun findAll(): Flow<List<Article>> {
    return dao.getAll().map {
      it.map { entity ->
        entity.toDomainModel()
      }
    }
  }

  override fun findUnArchivedAll(): Flow<List<Article>> {
    return dao.getUnarchivedAll().map {
      it.map { entity ->
        entity.toDomainModel()
      }
    }
  }

  override suspend fun findById(id: ArticleId): Article {
    return dao.findById(id.value).toDomainModel()
  }
}