package com.mito.stockarticle.infra.domain

import com.mito.stockarticle.domain.repo.ArticleReadOnlyRepository
import com.mito.stockarticle.domain.repo.ArticleRepository
import com.mito.stockarticle.infra.domain.repo.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class DomainImplModule {
  @Singleton
  @Binds
  abstract fun provideRepository(
    impl: ArticleRepositoryImpl
  ): ArticleRepository

  @Singleton
  @Binds
  abstract fun provideReadOnlyRepository(
    impl: ArticleRepositoryImpl
  ): ArticleReadOnlyRepository
}
