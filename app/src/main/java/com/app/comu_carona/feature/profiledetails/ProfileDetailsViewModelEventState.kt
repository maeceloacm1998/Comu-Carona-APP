package com.app.comu_carona.feature.profiledetails

import android.net.Uri

sealed class ProfileDetailsViewModelEventState {

    /**
     * Represents the event when the back button is clicked.
     */
    data object OnBack : ProfileDetailsViewModelEventState()

    /**
     * Represents the event when the full name is updated.
     *
     * @param fullName The new full name.
     */
    data class OnUpdateFullName(val fullName: String) : ProfileDetailsViewModelEventState()

    /**
     * Represents the event when the birth date is updated.
     *
     * @param birthDate The new birth date.
     */
    data class OnUpdateBirthDate(val birthDate: String) : ProfileDetailsViewModelEventState()

    /**
     * Represents the event when the phone number is updated.
     *
     * @param phoneNumber The new phone number.
     */
    data class OnUpdatePhoneNumber(val phoneNumber: String) : ProfileDetailsViewModelEventState()
    
    /**
     * Represents the event when the photo is updated.
     */
    data class OnOpenPhoto(val uri: Uri) : ProfileDetailsViewModelEventState()

    /**
     * Represents the event when the photo is updated.
     */
    data object OnUpdateProfile : ProfileDetailsViewModelEventState()
}