package com.mito.stockarticle.infra.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
  @PrimaryKey
  val id: String,
  val title: String,
  val url: String,
  val memo: String,
  val isArchived: Boolean
)
