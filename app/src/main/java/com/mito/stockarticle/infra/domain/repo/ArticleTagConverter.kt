package com.mito.stockarticle.infra.domain.repo

import com.mito.stockarticle.domain.ArticleTag
import com.mito.stockarticle.domain.ArticleTagId
import com.mito.stockarticle.infra.db.entity.ArticleTagEntity

fun ArticleTagEntity.toDomainModel() = ArticleTag(
  id = ArticleTagId(id.toString()),
  name = name,
  red = red,
  green = green,
  blue = blue
)

fun ArticleTag.toEntity() = ArticleTagEntity(
  name = name,
  red = red,
  green = green,
  blue = blue
)
