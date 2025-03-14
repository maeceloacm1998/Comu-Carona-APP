package com.app.comu_carona.commons.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import org.koin.core.annotation.Factory

@Factory
class ShareLinkUseCase(
    private val context: Context
) {
    @SuppressLint("QueryPermissionsNeeded")
    operator fun invoke(link: String, onErrorAction: (message: String) -> Unit = {}) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, link)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            onErrorAction(ERROR_MESSAGE)
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Erro ao tentar abrir o share."
    }
}