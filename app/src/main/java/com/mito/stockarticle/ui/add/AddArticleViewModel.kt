package com.mito.stockarticle.ui.add

import android.net.Uri
import android.webkit.URLUtil
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mito.stockarticle.domain.repo.ArticleRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.net.URL

class AddArticleViewModel(
  private val articleRepository: ArticleRepository
) : ViewModel(), AddArticleEvent {
  private val _navigateToList = Channel<Unit>(Channel.BUFFERED)
  val navigateToList = _navigateToList.receiveAsFlow()

  var articleState: AddArticleState by mutableStateOf(AddArticleState())
    private set

  override fun onTitleChange(title: String) {
    articleState = articleState.copy(title = title)
  }

  override fun onUrlChange(url: String) {
    articleState = articleState.copy(
      url = url,
      addable = URLUtil.isValidUrl(url)
    )
  }

  override fun onMemoChange(memo: String) {
    articleState = articleState.copy(memo = memo)
  }

  override fun onAddClick() {
    viewModelScope.launch {
      articleRepository.add(
        title = articleState.title,
        url = articleState.url,
        memo = articleState.memo
      )
      _navigateToList.send(Unit)
    }
  }

  override fun onBackClick() {
    viewModelScope.launch {
      _navigateToList.send(Unit)
    }
  }
}