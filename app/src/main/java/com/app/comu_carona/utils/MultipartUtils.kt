package com.app.comu_carona.utils

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object MultipartUtils {

    /**
     * Create a MultipartBody.Part from a file path and a part name to be used in a Multipart request
     * @param uri the Uri of the file to be uploaded
     * @param partName the name of the part in the Multipart request
     * @return a MultipartBody.Part object
     */
    fun getFilePartFromUri(context: Context, uri: Uri, partName: String): MultipartBody.Part {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("upload", ".tmp", context.cacheDir)
        val outputStream = FileOutputStream(tempFile)

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        val requestFile = RequestBody.create(
            context.contentResolver.getType(uri)?.toMediaTypeOrNull(),
            tempFile
        )

        return MultipartBody.Part.createFormData(partName, tempFile.name, requestFile)
    }
}