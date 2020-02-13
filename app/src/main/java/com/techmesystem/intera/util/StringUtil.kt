package com.techmesystem.intera.util

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import kotlin.math.round

/**
 * Prem's creation, on 2020-02-05
 */
infix fun String?.ifEmptyUse(otherString: String): String = if (this.isNullOrEmpty()) otherString else this

fun Int?.orZero() = this ?: 0

fun Float.round(decimals: Int = 2): Float {
  var multiplier = 1.0
  repeat(decimals) { multiplier *= 10 }
  return (round(this * multiplier) / multiplier).toFloat()
}

@SuppressLint("DefaultLocale") fun String?.makeSectionOfTextBoldPlusColor(textToBold: String?, colorCode: Int): SpannableStringBuilder {
  if ((this?.contains(textToBold.orEmpty(), true) == false)) return SpannableStringBuilder.valueOf(this)
  val builder = SpannableStringBuilder()

  if (this != null && textToBold != null && textToBold.trim { it <= ' ' }.isNotEmpty()) {
    // for counting start/end indexes
    val testText = toLowerCase()
    val testTextToBold = textToBold.toLowerCase()
    val startingIndex = testText.indexOf(testTextToBold)
    val endingIndex = startingIndex + testTextToBold.length
    if (startingIndex < 0 || endingIndex < 0) {
      builder.append(this)
    } else if (startingIndex > -1) {
      builder.append(this)
      builder.setSpan(StyleSpan(Typeface.BOLD), startingIndex, endingIndex, 0)
      builder.setSpan(ForegroundColorSpan(colorCode), startingIndex, endingIndex, 0)
    }
  } else {
    builder.append(this)
  }
  return builder
}
