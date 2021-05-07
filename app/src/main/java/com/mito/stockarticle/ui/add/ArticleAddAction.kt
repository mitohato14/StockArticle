package com.mito.stockarticle.ui.add

interface ArticleAddAction {
  fun onTitleChange(title: String)
  fun onUrlChange(url: String)
  fun onMemoChange(memo: String)
  fun onClickAdd()
  fun onClickBack()
}