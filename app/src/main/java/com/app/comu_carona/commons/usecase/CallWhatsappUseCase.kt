package com.app.comu_carona.commons.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.annotation.Factory

@Factory
class CallWhatsappUseCase(
    private val context: Context
) {
    operator fun invoke(phoneNumber: String, message: String) {
        openWhatsApp(context, phoneNumber, message)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openWhatsApp(
        context: Context,
        phoneNumber: String,
        message: String
    ) {
        val url = getUrl(
            message = message,
            phoneNumber = phoneNumber,
        )

        val intent = handleWhatsappIntent(url = url)

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            openWhatsAppWithBrowser(
                context = context,
                url = url
            )
        }
    }

    private fun openWhatsAppWithBrowser(context: Context, url: String): Unit {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(browserIntent)
    }

    private fun handleWhatsappIntent(
        url: String
    ): Intent {
        return Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    private fun getUrl(phoneNumber: String, message: String): String {
        return "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
    }

    companion object {
        const val DEFAULT_MESSAGE_CAR_RIDE =
            "Olá, gostaria de saber mais sobre a carona. Ainda tem vaga?"
    }
}