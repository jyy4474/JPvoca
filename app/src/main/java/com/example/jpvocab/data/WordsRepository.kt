package com.example.jpvocab.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WordsRepository(private val context: Context) {
  private val gson = Gson()
  private var cache: List<Word>? = null

  fun getAll(): List<Word> {
    if (cache != null) return cache!!
    val json = context.assets.open("words.json").bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<Word>>(){}.type
    cache = gson.fromJson(json, type)
    return cache!!
  }
}
