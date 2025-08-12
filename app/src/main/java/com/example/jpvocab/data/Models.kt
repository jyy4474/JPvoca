package com.example.jpvocab.data

data class Word(
  val id: Int,
  val kanji: String, // 표기(한자/가나)
  val reading: String, // 읽는법(가나)
  val meaningKo: String // 뜻(한국어)
)
