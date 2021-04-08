package com.mito.stockarticle.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticleAddViewModel : ViewModel() {
  private val _title: MutableLiveData<String> = MutableLiveData()
  val title: LiveData<String> = _title

  private val _url: MutableLiveData<String> = MutableLiveData()
  val url: LiveData<String> = _url

  private val _memo: MutableLiveData<String> = MutableLiveData()
  val memo: LiveData<String> = _memo

  private val _navigateToList = Channel<Unit>(Channel.BUFFERED)
  val navigateToList = _navigateToList.receiveAsFlow()

  fun onTitleChange(title: String) {
    _title.value = title
  }

  fun onUrlChange(url: String) {
    _url.value = url
  }

  fun onMemoChange(memo: String) {
    _memo.value = memo
  }

  fun onClickAdd() {
    viewModelScope.launch {
      _navigateToList.send(Unit)
    }
  }

  fun onClickCancel() {
    viewModelScope.launch {
      _navigateToList.send(Unit)
    }
  }
}