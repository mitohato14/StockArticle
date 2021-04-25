package com.mito.stockarticle.infra.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mito.stockarticle.infra.db.entity.ArticleTagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleTagDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(articleTagEntity: ArticleTagEntity)

  @Query("SELECT * FROM article_tag")
  fun getAll(): Flow<List<ArticleTagEntity>>

  @Query("SELECT * FROM article_tag WHERE id = :id LIMIT 1")
  suspend fun findById(id: String): ArticleTagEntity

  @Query("DELETE FROM article_tag WHERE id = :id")
  suspend fun delete(id: String)

  @Delete
  suspend fun delete(articleTagEntity: ArticleTagEntity)

  @Update
  suspend fun update(articleTagEntity: ArticleTagEntity)
}