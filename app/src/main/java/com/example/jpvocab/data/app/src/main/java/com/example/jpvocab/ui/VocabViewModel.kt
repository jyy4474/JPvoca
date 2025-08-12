package com.example.jpvocab.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jpvocab.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VocabViewModel(private val context: Context): ViewModel() {
  private val repo = WordsRepository(context)
  private val _today = MutableStateFlow<List<Word>>(emptyList())
  val today: StateFlow<List<Word>> = _today

  private val _all = MutableStateFlow<List<Word>>(emptyList())
  val all: StateFlow<List<Word>> = _all

  init {
    viewModelScope.launch {
      _all.value = repo.getAll()
      _today.value = ensureToday(context, repo)
    }
  }

  fun romaji(reading: String) = Kana.toRomaji(reading)

  companion object {
    val factory: (Context) -> ViewModelProvider.Factory = { ctx ->
      viewModelFactory { initializer { VocabViewModel(ctx) } }
    }
  }
}
