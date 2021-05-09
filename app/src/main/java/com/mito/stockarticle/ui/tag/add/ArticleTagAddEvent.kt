package com.mito.stockarticle.ui.tag.add

interface ArticleTagAddEvent {
  fun onNameChange(name: String)
  fun onRedChange(red: Float)
  fun onGreenChange(green: Float)
  fun onBlueChange(blue: Float)
  fun onClickAdd()
  fun onClickBack()
}
