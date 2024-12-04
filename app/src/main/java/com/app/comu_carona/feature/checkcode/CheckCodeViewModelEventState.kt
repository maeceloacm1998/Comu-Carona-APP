package com.app.comu_carona.feature.checkcode

/**
 * Represents the events that can be triggered in the check code screen.
 */
sealed class CheckCodeViewModelEventState {
    /**
     * Represents the event when the code is changed.
     *
     * @param code The new code.
     */
    data class OnChangedCode(val code: String) : CheckCodeViewModelEventState()

    /**
     * Represents the event when the check code button is clicked.
     */
    data object OnClickCheckCode : CheckCodeViewModelEventState()
}