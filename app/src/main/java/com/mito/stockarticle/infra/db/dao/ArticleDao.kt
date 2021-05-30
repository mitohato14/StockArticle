package com.mito.stockarticle.infra.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mito.stockarticle.infra.db.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(articleEntity: ArticleEntity)

  @Query("SELECT * FROM articles")
  fun getAll(): Flow<List<ArticleEntity>>

  @Query("SELECT * FROM articles WHERE isArchived = 0")
  fun getUnarchivedAll(): Flow<List<ArticleEntity>>

  @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
  suspend fun findById(id: String): ArticleEntity

  @Query("DELETE FROM articles WHERE id = :id")
  suspend fun delete(id: String)

  @Delete
  suspend fun delete(articleEntity: ArticleEntity)

  @Update
  suspend fun update(articleEntity: ArticleEntity)
}