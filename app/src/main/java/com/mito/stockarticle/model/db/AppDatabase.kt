package com.mito.stockarticle.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mito.stockarticle.model.ArticleEntity
import com.mito.stockarticle.model.db.dao.ArticleDao

@Database(
  entities = arrayOf(ArticleEntity::class),
  version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun articleDao(): ArticleDao

  companion object {
    @Volatile
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