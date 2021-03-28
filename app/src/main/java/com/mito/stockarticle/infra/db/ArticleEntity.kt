package com.mito.stockarticle.infra.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
  val title: String,
  val url: String,
  val memo: String
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}
