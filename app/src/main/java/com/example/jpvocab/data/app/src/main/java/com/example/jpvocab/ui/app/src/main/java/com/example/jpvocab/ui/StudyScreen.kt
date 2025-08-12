package com.example.jpvocab.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jpvocab.data.Word

@Composable
fun StudyScreen(vm: VocabViewModel) {
  val list by vm.today.collectAsState()
  if (list.isEmpty()) { 
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { 
      CircularProgressIndicator() 
    }
    return
  }

  var idx by remember { mutableStateOf(0) }
  val w = list[idx]
  var showReading by remember { mutableStateOf(false) }

  Column(
    Modifier.fillMaxSize().padding(16.dp), 
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Text("오늘의 10단어", style = MaterialTheme.typography.titleMedium)
    Card(Modifier.fillMaxWidth()) {
      Column(
        Modifier.padding(20.dp), 
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Text(w.kanji, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
        if (showReading) {
          Text("읽는법: ${w.reading} (${vm.romaji(w.reading)})", style = MaterialTheme.typography.bodyLarge)
          Text("뜻: ${w.meaningKo}", style = MaterialTheme.typography.bodyLarge)
        } else {
          Text("읽는법/뜻 숨김", style = MaterialTheme.typography.bodyMedium)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Button(onClick = { showReading = !showReading }) { Text(if (showReading) "숨기기" else "보기") }
          OutlinedButton(
            onClick = {
              idx = (idx + 1).coerceAtMost(list.lastIndex)
              showReading = false
            }, 
            enabled = idx < list.lastIndex
          ) { Text("다음") }
        }
      }
    }
    LinearProgressIndicator(progress = (idx+1) / list.size.toFloat())
  }
}
