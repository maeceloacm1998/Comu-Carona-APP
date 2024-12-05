package com.app.comu_carona.utils

object StringUtils {
    const val PHONE_NUMBER_LENGTH = 14
    const val BIRTH_DATE_LENGTH = 10
    const val FULL_NAME_LENGTH = 5

    fun String.formatPhoneNumber(): String {
        val cleaned = this.filter { it.isDigit() }

        val formatted = StringBuilder()
        for (i in cleaned.indices) {
            when (i) {
                0 -> formatted.append('(')
                2 -> formatted.append(")")
                7 -> formatted.append('-')
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