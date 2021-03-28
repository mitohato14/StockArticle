package com.mito.stockarticle.infra.db

import android.content.Context
import com.mito.stockarticle.infra.db.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
internal object DBModule {
  @Singleton
  @Provides
  internal fun provideDataBase(context: Context): AppDatabase = AppDatabase.getDataBase(context)

  @Singleton
  @Provides
  internal fun provideDao(dataBase: AppDatabase): ArticleDao = dataBase.articleDao()
}
