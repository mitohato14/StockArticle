package com.mito.stockarticle.infra.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.infra.db.entity.ArticleEntity
import java.net.URL

fun ArticleEntity.toDomainModel() = Article(
  id = ArticleId(value = id),
  title = title,
  url = URL(url),
  memo = memo,
  isArchived = isArchived
)

fun Article.toEntity() = ArticleEntity(
  id = id.value,
  title = title,
  url = url.toString(),
  memo = memo,
  isArchived = isArchived
)
