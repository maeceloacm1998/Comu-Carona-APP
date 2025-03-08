package com.app.comu_carona.feature.home.steps.rideinprogress.data.models

import androidx.compose.ui.graphics.Color
import com.app.comu_carona.theme.Error
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.Secondary
import com.app.comu_carona.theme.Success

enum class RideInProgressFilterOptions(
    val title: String,
    val color: Color
) {
    MY_RIDE("Minha carona", Secondary),
    IN_PROGRESS("Em andamento", Primary),
    CANCELED("Cancelado", Error),
    FINISHED("Concluído", Success);

    companion object {
        fun fromValue(value: String):  RideInProgressFilterOptions {
            return when(value) {
                MY_RIDE.toString() -> MY_RIDE
                IN_PROGRESS.toString() -> IN_PROGRESS
                CANCELED.toString() -> CANCELED
                FINISHED.toString() -> FINISHED
                else -> {
                    throw IllegalArgumentException("Invalid value")
                }
            }
        }
    }
}