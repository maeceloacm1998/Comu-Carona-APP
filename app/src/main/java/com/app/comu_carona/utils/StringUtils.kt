package com.app.comu_carona.utils

object StringUtils {
    fun String.formatPhoneNumber(): String {
        val cleaned = this.filter { it.isDigit() }

        val formatted = StringBuilder()
        for (i in cleaned.indices) {
            when (i) {
                0 -> formatted.append('(')
                2 -> formatted.append(") ")
                6 -> formatted.append('-')
            }
            formatted.append(cleaned[i])
        }
        return formatted.toString()
    }

    fun String.formatBirthDate(): String {
        val cleaned = this.filter { it.isDigit() }

        val formatted = StringBuilder()
        for (i in cleaned.indices) {
            if (i == 2 || i == 4) {
                formatted.append('/')
            }
            formatted.append(cleaned[i])
        }
        return formatted.toString()
    }
}