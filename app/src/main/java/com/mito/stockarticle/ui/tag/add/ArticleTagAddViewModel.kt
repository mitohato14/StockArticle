package com.mito.stockarticle.ui.tag.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.repo.ArticleTagRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticleTagAddViewModel(
  private val articleTagRepository: ArticleTagRepository
) : ViewModel(), ArticleTagAddEvent {
  private val _navigateToList = Channel<Unit>(Channel.BUFFERED)
  val navigateToList: Flow<Unit> = _navigateToList.receiveAsFlow()

  var state: ArticleTagAddState by mutableStateOf(ArticleTagAddState())
    private set

  override fun onNameChange(name: String) {
    state = state.copy(name = name)
  }

  override fun onRedChange(red: Float) {
    state = state.copy(red = red)
  }

  override fun onGreenChange(green: Float) {
    state = state.copy(green = green)
  }

  override fun onBlueChange(blue: Float) {
    state = state.copy(blue = blue)
  }

  override fun onClickAdd() {
    viewModelScope.launch {
      articleTagRepository.add(
        state.name,
        state.red,
        state.green,
        state.blue
      )
      _navigateToList.send(Unit)
    }
  }

  override fun onClickBack() {
    viewModelScope.launch {
      _navigateToList.send(Unit)
    }
  }
}