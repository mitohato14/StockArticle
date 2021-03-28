package com.mito.stockarticle.infra.domain.repo

import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.infra.db.entity.ArticleEntity
import java.net.URL

fun ArticleEntity.toDomainModel() = Article(
  id = ArticleId(value = id.toString()),
  title = title,
  url = URL(url),
  memo = memo
)

fun Article.toEntity() = ArticleEntity(
  title = title,
  url = url.path,
  memo = memo
)
