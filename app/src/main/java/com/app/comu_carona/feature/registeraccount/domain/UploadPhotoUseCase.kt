package com.app.comu_carona.feature.registeraccount.domain

import android.content.Context
import android.net.Uri
import com.app.comu_carona.feature.registeraccount.data.RegisterAccountRepository
import com.app.comu_carona.feature.registeraccount.data.models.PhotoRequest
import com.app.comu_carona.service.retrofit.AuthPreferences
import com.app.comu_carona.utils.MultipartUtils
import org.koin.core.annotation.Factory

@Factory
class UploadPhotoUseCase(
    private val context: Context,
    private val repository: RegisterAccountRepository,
    private val authPreferences: AuthPreferences
) {
    suspend operator fun invoke(
        photoUri: Uri
    ): Result<PhotoRequest> {
        return try {
            val photoUploadResponse = uploadPhoto(
                context = context,
                photoUri = photoUri
            )
            photoUploadResponse.fold(
                onSuccess = { data ->
                    authPreferences.photoUrl = data.uri
                    Result.success(data)
                },
                onFailure = { throwable ->
                    Result.failure(throwable)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun uploadPhoto(context: Context, photoUri: Uri): Result<PhotoRequest> {
        val photoPart = MultipartUtils.getFilePartFromUri(
            context = context,
            uri = photoUri,
            partName = "file"
        )
        return repository.updatePhoto(photoPart)
    }
}