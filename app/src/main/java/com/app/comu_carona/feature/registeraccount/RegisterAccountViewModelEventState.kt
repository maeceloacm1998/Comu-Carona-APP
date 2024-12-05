package com.app.comu_carona.feature.registeraccount

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps

/**
 * Represents the events that can be triggered in the check code screen.
 */
sealed class RegisterAccountViewModelEventState {
    /**
     * Represents the event when the next step button is clicked.
     *
     * @param step The new step.
     */
    data class OnNextStep(val step: RegisterAccountSteps) : RegisterAccountViewModelEventState()

    /**
     * Represents the event when the previous step button is clicked.
     *
     * @param step The new step.
     */
    data class OnRemoveNewStep(val step: RegisterAccountSteps) :
        RegisterAccountViewModelEventState()

    /**
     * Represents the event when the full name is updated.
     *
     * @param fullName The new full name.
     */
    data class OnUpdateFullName(val fullName: String) : RegisterAccountViewModelEventState()

    /**
     * Represents the event when the birth date is updated.
     *
     * @param birthDate The new birth date.
     */
    data class OnUpdateBirthDate(val birthDate: String) : RegisterAccountViewModelEventState()

    /**
     * Represents the event when the phone number is updated.
     *
     * @param phoneNumber The new phone number.
     */
    data class OnUpdatePhoneNumber(val phoneNumber: String) : RegisterAccountViewModelEventState()

    /**
     * Represents the event when the photo is updated.
     *
     * @param photo The new photo.
     */
    data class OnUpdatePhoto(val photo: String) : RegisterAccountViewModelEventState()

    /**
     * Represents the event when the permission is granted.
     */
    data class OnGrantedPermission(val isGranted: Boolean) : RegisterAccountViewModelEventState()
}