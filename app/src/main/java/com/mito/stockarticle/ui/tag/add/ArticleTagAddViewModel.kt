package com.mito.stockarticle.ui.tag.add

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
) : ViewModel() {
  private val _name: MutableLiveData<String> = MutableLiveData("")
  val name: LiveData<String> = _name

  private val _red: MutableLiveData<Float> = MutableLiveData(0.5f)
  val red: LiveData<Float> = _red

  private val _green: MutableLiveData<Float> = MutableLiveData(0.5f)
  val green: LiveData<Float> = _green

  private val _blue: MutableLiveData<Float> = MutableLiveData(0.5f)
  val blue: LiveData<Float> = _blue

  private val _navigateToList = Channel<Unit>(Channel.BUFFERED)
  val navigateToList: Flow<Unit> = _navigateToList.receiveAsFlow()


  fun onNameChange(tagName: String) {
    _name.value = tagName
  }

  fun onRedChange(red: Float) {
    _red.value = red
  }

  fun onGreenChange(green: Float) {
    _green.value = green
  }

  fun onBlueChange(blue: Float) {
    _blue.value = blue
  }

  fun onClickAdd(
    name: String,
    red: Float,
    green: Float,
    blue: Float
  ) {
    viewModelScope.launch {
      articleTagRepository.add(
        name,
        red,
        green,
        blue
      )
      _navigateToList.send(Unit)
    }
  }

  fun onClickBack() {
    viewModelScope.launch {
      _navigateToList.send(Unit)
    }
  }
}