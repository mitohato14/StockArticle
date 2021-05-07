package com.mito.stockarticle.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.repo.ArticleRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticleAddViewModel(
  private val articleRepository: ArticleRepository
) : ViewModel(), ArticleAddAction {
  private val _navigateToList = Channel<Unit>(Channel.BUFFERED)
  val navigateToList = _navigateToList.receiveAsFlow()

  var state: ArticleAddState by mutableStateOf(ArticleAddState())
  private set

  override fun onTitleChange(title: String) {
    state = state.copy(title = title)
  }

  override fun onUrlChange(url: String) {
    state = state.copy(url = url)
  }

  override fun onMemoChange(memo: String) {
    state = state.copy(memo = memo)
  }

  override fun onClickAdd() {
    viewModelScope.launch {
      articleRepository.add(
        title = state.title,
        url = state.url,
        memo = state.memo
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