package com.mito.stockarticle.infra.db

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context) =
    AppDatabase.getDataBase(context = context)

  @Provides
  @Singleton
  fun provideArticleDao(appDatabase: AppDatabase) = appDatabase.articleDao()
}
