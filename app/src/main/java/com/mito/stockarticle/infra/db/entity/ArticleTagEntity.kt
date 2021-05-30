package com.mito.stockarticle.infra.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_tag")
data class ArticleTagEntity(
  val name: String,
  val red: Float,
  val green: Float,
  val blue: Float,
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}
