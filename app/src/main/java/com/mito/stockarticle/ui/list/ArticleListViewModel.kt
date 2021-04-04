package com.mito.stockarticle.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.Article
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel() {
  private val _articleList: MutableLiveData<List<Article>> = MutableLiveData()
  val articleList: LiveData<List<Article>> = _articleList

  private val _navigateToAdd = Channel<Unit>(Channel.BUFFERED)
  val navigateToAdd = _navigateToAdd.receiveAsFlow()

  fun onAddClick() {
    viewModelScope.launch {
      _navigateToAdd.send(Unit)
    }
  }
}