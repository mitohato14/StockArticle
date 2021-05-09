package com.mito.stockarticle.ui.list

import com.mito.stockarticle.domain.ArticleId

interface ArticleListEvent {
  fun onAddClick()
  fun onArchiveClick(articleId: ArticleId)
}