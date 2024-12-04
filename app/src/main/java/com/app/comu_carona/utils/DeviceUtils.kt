package com.app.comu_carona.utils

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DeviceUtils {

    /**
     * Get the advertising ID of the device
     * @param context Context
     * @return String?
     */
    suspend fun getAdvertisingId(context: Context): String? {
        return withContext(Dispatchers.IO) {
            try {
                val info = AdvertisingIdClient.getAdvertisingIdInfo(context)
                Log.d("DeviceUtils", "Advertising ID: ${info.id}")
                info.id
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}