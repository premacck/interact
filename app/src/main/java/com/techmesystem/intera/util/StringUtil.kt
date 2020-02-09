package com.techmesystem.intera.util

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import java.util.regex.Pattern
import kotlin.math.round

/**
 * Prem's creation, on 2020-02-05
 */
infix fun String?.ifEmptyUse(otherString: String): String = if (this.isNullOrEmpty()) otherString else this

fun Int?.orZero() = this ?: 0

fun String.makeStarredPortionBold(): SpannableStringBuilder {
  val sb = StringBuffer()
  val result = SpannableStringBuilder()
  val matcher = Pattern.compile("""[*][^ ]([^*]+)[^ ][*]""").matcher(this)
  while (matcher.find()) {
    sb.setLength(0) // clear
    matcher.group(1)?.let { group ->
      val spanText = group.substring(0, group.length)
      matcher.appendReplacement(sb, spanText)
      result.append(sb.toString())
      val start = result.length - spanText.length
      result.setSpan(StyleSpan(Typeface.BOLD), start, result.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    }
  }
  sb.setLength(0)
  matcher.appendTail(sb)
  result.append(sb.toString())
  return result
}

fun Float.round(decimals: Int = 2): Float {
  var multiplier = 1.0
  repeat(decimals) { multiplier *= 10 }
  return (round(this * multiplier) / multiplier).toFloat()
}