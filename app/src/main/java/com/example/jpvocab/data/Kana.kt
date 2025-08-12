package com.example.jpvocab.data

object Kana {
  private val table = mapOf(
    "あ" to "a","い" to "i","う" to "u","え" to "e","お" to "o",
    "か" to "ka","き" to "ki","く" to "ku","け" to "ke","こ" to "ko",
    "さ" to "sa","し" to "shi","す" to "su","せ" to "se","そ" to "so",
    "た" to "ta","ち" to "chi","つ" to "tsu","て" to "te","と" to "to",
    "な" to "na","に" to "ni","ぬ" to "nu","ね" to "ne","の" to "no",
    "は" to "ha","ひ" to "hi","ふ" to "fu","へ" to "he","ほ" to "ho",
    "ま" to "ma","み" to "mi","む" to "mu","め" to "me","も" to "mo",
    "や" to "ya","ゆ" to "yu","よ" to "yo",
    "ら" to "ra","り" to "ri","る" to "ru","れ" to "re","ろ" to "ro",
    "わ" to "wa","を" to "o","ん" to "n",
    "が" to "ga","ぎ" to "gi","ぐ" to "gu","げ" to "ge","ご" to "go",
    "ざ" to "za","じ" to "ji","ず" to "zu","ぜ" to "ze","ぞ" to "zo",
    "だ" to "da","ぢ" to "ji","づ" to "zu","で" to "de","ど" to "do",
    "ば" to "ba","び" to "bi","ぶ" to "bu","べ" to "be","ぼ" to "bo",
    "ぱ" to "pa","ぴ" to "pi","ぷ" to "pu","ぺ" to "pe","ぽ" to "po",
    "きゃ" to "kya","きゅ" to "kyu","きょ" to "kyo",
    "しゃ" to "sha","しゅ" to "shu","しょ" to "sho",
    "ちゃ" to "cha","ちゅ" to "chu","ちょ" to "cho",
    "にゃ" to "nya","にゅ" to "nyu","にょ" to "nyo",
    "ひゃ" to "hya","ひゅ" to "hyu","ひょ" to "hyo",
    "みゃ" to "mya","みゅ" to "myu","みょ" to "myo",
    "りゃ" to "rya","りゅ" to "ryu","りょ" to "ryo",
    "ぎゃ" to "gya","ぎゅ" to "gyu","ぎょ" to "gyo",
    "じゃ" to "ja","じゅ" to "ju","じょ" to "jo",
    "びゃ" to "bya","びゅ" to "byu","びょ" to "byo",
    "ぴゃ" to "pya","ぴゅ" to "pyu","ぴょ" to "pyo"
  )

  fun toRomaji(kana: String): String {
    var s = kana
    val smallTsu = "っ"
    val longBar = "ー"

    table.keys.filter { it.length == 2 }.forEach { digraph ->
      s = s.replace(digraph, " ${table[digraph]} ")
    }
    table.keys.filter { it.length == 1 }.forEach { mono ->
      s = s.replace(mono, " ${table[mono]} ")
    }
    s = s.replace(smallTsu, " ")
    s = s.trim().replace(Regex("\\s+"), " ")
    s = s.replace(longBar, "")
    return s
  }
}
