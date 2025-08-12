package com.example.jpvocab.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DictionaryScreen(vm: VocabViewModel) {
  val all by vm.all.collectAsState()
  LazyColumn(Modifier.fillMaxSize()) {
    items(all) { w ->
      ListItem(
        headlineContent = { Text(w.kanji) },
        supportingContent = { Text("${w.reading} (${vm.romaji(w.reading)}) · ${w.meaningKo}") }
      )
      Divider()
    }
    item { Spacer(Modifier.height(100.dp)) }
  }
}
