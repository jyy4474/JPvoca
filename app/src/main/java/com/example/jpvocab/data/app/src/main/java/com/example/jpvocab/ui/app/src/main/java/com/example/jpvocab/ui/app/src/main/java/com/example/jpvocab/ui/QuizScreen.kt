package com.example.jpvocab.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jpvocab.data.Word

@Composable
fun QuizScreen(vm: VocabViewModel) {
  val list by vm.today.collectAsState()
  if (list.isEmpty()) { 
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { 
      CircularProgressIndicator() 
    }
    return
  }

  var idx by remember { mutableStateOf(0) }
  var revealed by remember { mutableStateOf(false) }
  val w = list[idx]

  Column(
    Modifier.fillMaxSize().padding(16.dp), 
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Text("퀴즈: 뜻 맞히기", style = MaterialTheme.typography.titleMedium)
    Card(Modifier.fillMaxWidth()) {
      Column(
        Modifier.padding(20.dp), 
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Text(w.kanji, style = MaterialTheme.typography.headlineLarge)
        if (revealed) {
          Text("읽는법: ${w.reading} (${vm.romaji(w.reading)})")
          Text("정답: ${w.meaningKo}")
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Button(onClick = { revealed = true }) { Text("정답 보기") }
          OutlinedButton(
            onClick = {
              idx = (idx + 1) % list.size
              revealed = false
            }
          ) { Text("다음") }
        }
      }
    }
  }
}
