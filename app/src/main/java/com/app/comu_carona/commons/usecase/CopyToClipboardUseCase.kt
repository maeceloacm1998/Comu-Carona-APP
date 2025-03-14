package com.app.comu_carona.commons.usecase

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import org.koin.core.annotation.Factory

@Factory
class CopyToClipboardUseCase(
    private val context: Context
) {
    operator fun invoke(text: String, label: String = "Copied Text") {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        showToast("Texto copiado")
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}