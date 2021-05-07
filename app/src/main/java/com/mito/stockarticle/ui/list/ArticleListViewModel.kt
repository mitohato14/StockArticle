package com.mito.stockarticle.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.domain.repo.ArticleRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticleListViewModel(
  private val articleRepository: ArticleRepository
) : ViewModel(), ArticleListEvent {
  var state: ArticleListState by mutableStateOf(ArticleListState())
    private set

  private val _navigateToAdd = Channel<Unit>(Channel.BUFFERED)
  val navigateToAdd = _navigateToAdd.receiveAsFlow()

  private val _navigateToAddTag = Channel<Unit>(Channel.BUFFERED)
  val navigateToAddTag = _navigateToAddTag.receiveAsFlow()

  init {
    viewModelScope.launch {
      articleRepository.findAll().collect {
        state = state.copy(articleList = it)
      }
    }
  }

  override fun onAddClick() {
    viewModelScope.launch {
      _navigateToAdd.send(Unit)
    }
  }

  override fun onNewTagClick() {
    viewModelScope.launch {
      _navigateToAddTag.send(Unit)
    }
  }

  override fun onArchiveClick(articleId: ArticleId) {
    viewModelScope.launch {
      val article = articleRepository.findById(articleId)
      articleRepository.update(article.copy(isArchived = true))
    }
  }
}