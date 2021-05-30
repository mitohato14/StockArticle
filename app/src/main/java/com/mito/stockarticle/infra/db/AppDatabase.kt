package com.mito.stockarticle.infra.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mito.stockarticle.infra.db.dao.ArticleDao
import com.mito.stockarticle.infra.db.dao.ArticleTagDao
import com.mito.stockarticle.infra.db.entity.ArticleEntity
import com.mito.stockarticle.infra.db.entity.ArticleTagEntity

@Database(
  entities = [
    ArticleEntity::class,
    ArticleTagEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun articleDao(): ArticleDao
  abstract fun tagDao(): ArticleTagDao

  companion object {
    private var INSTANCE: AppDatabase? = null

    fun getDataBase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "article_db"
        )
          .allowMainThreadQueries()
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}