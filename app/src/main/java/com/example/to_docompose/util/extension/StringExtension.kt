package com.example.to_docompose.util.extension

private const val MIN_LENGTH = 2

fun String?.isValid(): Boolean {
    return formatContent(this).length >= MIN_LENGTH
}

private fun formatContent(string: String?): String {
    return string.orEmpty().trim()
}