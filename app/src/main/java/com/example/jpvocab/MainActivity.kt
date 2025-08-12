package com.example.jpvocab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jpvocab.ui.*

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        val vm: VocabViewModel = viewModel(factory = VocabViewModel.factory(applicationContext))
        AppScaffold(vm)
      }
    }
  }
}

@Composable
fun AppScaffold(vm: VocabViewModel) {
  var tab by remember { mutableStateOf(0) }
  Scaffold(
    topBar = { SmallTopAppBar(title = { Text("JP Vocab") }) },
    bottomBar = {
      NavigationBar {
        NavigationBarItem(selected = tab == 0, onClick = { tab = 0 }, label = { Text("학습") }, icon = { })
        NavigationBarItem(selected = tab == 1, onClick = { tab = 1 }, label = { Text("퀴즈") }, icon = { })
        NavigationBarItem(selected = tab == 2, onClick = { tab = 2 }, label = { Text("단어장") }, icon = { })
      }
    }
  ) { inner ->
    Box(Modifier.padding(inner)) {
      when (tab) {
        0 -> StudyScreen(vm)
        1 -> QuizScreen(vm)
        2 -> DictionaryScreen(vm)
      }
    }
  }
}
