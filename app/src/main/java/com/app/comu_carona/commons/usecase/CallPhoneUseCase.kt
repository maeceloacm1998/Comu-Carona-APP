package com.app.comu_carona.commons.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.annotation.Factory

@Factory
class CallPhoneUseCase(
    private val context: Context
) {
    operator fun invoke(
        phoneNumber: String, onErrorAction: (message: String) -> Unit = {}
    ) {
        openDialer(
            context = context,
            phoneNumber = phoneNumber,
            onErrorAction = onErrorAction
        )
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openDialer(
        context: Context,
        phoneNumber: String,
        onErrorAction: (message: String) -> Unit = {}
    ) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:31992521566")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            onErrorAction(ERROR_MESSAGE)
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Erro ao tentar abrir o discador."
    }
}