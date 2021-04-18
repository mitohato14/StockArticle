package com.mito.stockarticle.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.repo.ArticleReadOnlyRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticleListViewModel(
  articleReadOnlyRepository: ArticleReadOnlyRepository
) : ViewModel() {
  val articleList: LiveData<List<Article>> = articleReadOnlyRepository.findAll().asLiveData()

  private val _navigateToAdd = Channel<Unit>(Channel.BUFFERED)
  val navigateToAdd = _navigateToAdd.receiveAsFlow()

  fun onAddClick() {
    viewModelScope.launch {
      _navigateToAdd.send(Unit)
    }
  }
}