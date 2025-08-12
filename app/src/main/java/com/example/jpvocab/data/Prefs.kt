package com.example.jpvocab.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "prefs")

object PrefsKeys {
  val DAY = intPreferencesKey("day")
  val TODAY_SET = stringSetPreferencesKey("today_set") // word ids as strings
}

suspend fun ensureToday(context: Context, repo: WordsRepository): List<Word> {
  val ds = context.dataStore
  val words = repo.getAll()
  val today = (System.currentTimeMillis() / (1000*60*60*24)).toInt()
  val prefs = ds.data.first()
  val savedDay = prefs[PrefsKeys.DAY] ?: -1
  val list: List<Word>
  if (savedDay == today) {
    val ids = prefs[PrefsKeys.TODAY_SET] ?: emptySet()
    list = words.filter { ids.contains(it.id.toString()) }
  } else {
    list = words.shuffled().take(10)
    ds.edit {
      it[PrefsKeys.DAY] = today
      it[PrefsKeys.TODAY_SET] = list.map { w -> w.id.toString() }.toSet()
    }
  }
  return list
}
