package com.mito.stockarticle.infra.domain

import com.mito.stockarticle.domain.repo.ArticleReadOnlyRepository
import com.mito.stockarticle.domain.repo.ArticleRepository
import com.mito.stockarticle.infra.db.dao.ArticleDao
import com.mito.stockarticle.infra.domain.repo.ArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainImplModule {
  @Provides
  @Singleton
  fun provideArticleRepository(articleDao: ArticleDao): ArticleRepository =
    ArticleRepositoryImpl(articleDao)

  @Provides
  @Singleton
  fun provideArticleReadOnlyRepository(articleDao: ArticleDao): ArticleReadOnlyRepository =
    ArticleRepositoryImpl(articleDao)
}
