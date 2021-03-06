package com.mito.stockarticle.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.ArticleId
import com.mito.stockarticle.domain.repo.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
  private val articleRepository: ArticleRepository
) : ViewModel(), ArticleListEvent {
  var state: ArticleListState by mutableStateOf(ArticleListState())
    private set

  private val _navigateToAdd = Channel<Unit>(Channel.BUFFERED)
  val navigateToAdd = _navigateToAdd.receiveAsFlow()

  private val _archivedArticle = Channel<ArticleId>(Channel.BUFFERED)
  val archivedArticle = _archivedArticle.receiveAsFlow()

  init {
    viewModelScope.launch {
      articleRepository.findUnArchivedAll().collect {
        state = state.copy(articleList = it)
      }
    }
  }

  override fun onAddClick() {
    viewModelScope.launch {
      _navigateToAdd.send(Unit)
    }
  }

  override fun onArchiveClick(articleId: ArticleId) {
    viewModelScope.launch {
      val article = articleRepository.findById(articleId)
      articleRepository.update(article.copy(isArchived = true))

      _archivedArticle.send(articleId)
    }
  }

  override fun onUndoArchiveClick(articleId: ArticleId) {
    viewModelScope.launch {
      val article = articleRepository.findById(articleId)
      articleRepository.update(article.copy(isArchived = false))
    }
  }
}