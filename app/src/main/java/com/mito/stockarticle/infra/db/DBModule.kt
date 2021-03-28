package com.mito.stockarticle.infra.db

import android.content.Context
import com.mito.stockarticle.infra.db.dao.ArticleDao
import com.mito.stockarticle.infra.db.dao.ArticleDao_Impl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DataBaseModule(private val context: Context) {
  @Singleton
  @Provides
  internal fun provideDataBase(): AppDatabase = AppDatabase.getDataBase(context)

  @Singleton
  @Provides
  internal fun provideDao(dataBase: AppDatabase): ArticleDao = dataBase.articleDao()
}
