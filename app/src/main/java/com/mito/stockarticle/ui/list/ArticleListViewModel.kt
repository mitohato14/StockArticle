package com.mito.stockarticle.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.Article
import com.mito.stockarticle.domain.repo.ArticleReadOnlyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
  private val articleReadOnlyRepository: ArticleReadOnlyRepository
) : ViewModel() {
  private val _articleList: MutableLiveData<List<Article>> = MutableLiveData()
  val articleList: LiveData<List<Article>> = _articleList

  private val _navigateToAdd = Channel<Unit>(Channel.BUFFERED)
  val navigateToAdd = _navigateToAdd.receiveAsFlow()

  init {
    viewModelScope.launch {
      _articleList.value = articleReadOnlyRepository.findAll()
    }
  }

  fun onAddClick() {
    viewModelScope.launch {
      _navigateToAdd.send(Unit)
    }
  }
}