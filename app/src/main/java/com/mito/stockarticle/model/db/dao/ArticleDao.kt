package com.mito.stockarticle.model.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mito.stockarticle.model.ArticleEntity

@Dao
interface ArticleDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(articleEntity: ArticleEntity)

  @Query("SELECT * FROM articles")
  suspend fun getAll(): List<ArticleEntity>

  @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
  suspend fun findById(id: String): ArticleEntity

  @Query("DELETE FROM articles WHERE id = :id")
  suspend fun delete(id: String): ArticleEntity

  @Delete
  suspend fun delete(articleEntity: ArticleEntity)
}