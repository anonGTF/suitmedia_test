package com.galih.suitmediatest.utills

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun String.toDate(pattern: String = "yyyy-MM-dd"): Date? =
    try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }

fun Boolean.toInt() = if (this) 1 else 0

fun Int.toBoolean() = this == 1

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Boolean?.orFalse() = this ?: false

fun String.withImageUrl() = BASE_IMAGE_URL + this

fun String.removeWhitespaces() = filter{ !it.isWhitespace()}

fun String.trim(maxLength: Int) =
    if (this.length > maxLength)
        "${this.take(maxLength - 3)}..."
    else
        this

fun View.visible() = run { this.visibility = View.VISIBLE }
fun View.gone() = run { this.visibility = View.GONE }