package com.mito.stockarticle

import android.app.Application
import com.mito.stockarticle.domain.repo.ArticleReadOnlyRepository
import com.mito.stockarticle.domain.repo.ArticleRepository
import com.mito.stockarticle.domain.repo.ArticleTagReadOnlyRepository
import com.mito.stockarticle.domain.repo.ArticleTagRepository
import com.mito.stockarticle.infra.db.AppDatabase
import com.mito.stockarticle.infra.domain.repo.ArticleRepositoryImpl
import com.mito.stockarticle.infra.domain.repo.ArticleTagRepositoryImpl
import com.mito.stockarticle.ui.add.AddArticleViewModel
import com.mito.stockarticle.ui.list.ArticleListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class StockArticleApp : Application() {
  private val dbModule = module {
    single { AppDatabase.getDataBase(get()) }
    single { get<AppDatabase>().articleDao() }
    single { get<AppDatabase>().tagDao() }
  }

  private val domainImplModule = module {
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    single<ArticleReadOnlyRepository> { ArticleRepositoryImpl(get()) }
    single<ArticleTagRepository> { ArticleTagRepositoryImpl(get()) }
    single<ArticleTagReadOnlyRepository> { ArticleTagRepositoryImpl(get()) }
  }

  private val viewModelModule = module {
    viewModel { ArticleListViewModel(get()) }
    viewModel { AddArticleViewModel(get()) }
  }

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@StockArticleApp)
      modules(
        listOf(
          dbModule,
          domainImplModule,
          viewModelModule
        )
      )
    }
  }
}
