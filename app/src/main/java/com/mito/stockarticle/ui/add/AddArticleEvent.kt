package com.mito.stockarticle.ui.add

interface AddArticleEvent {
  fun onTitleChange(title: String)
  fun onUrlChange(url: String)
  fun onMemoChange(memo: String)
  fun onAddClick()
  fun onBackClick()
}