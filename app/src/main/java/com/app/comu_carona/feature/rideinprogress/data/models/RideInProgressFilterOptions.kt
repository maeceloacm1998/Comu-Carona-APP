package com.app.comu_carona.feature.rideinprogress.data.models

enum class RideInProgressFilterOptions(
    val value: String
) {
    MY_RIDE("Minha carona"),
    IN_PROGRESS("Em andamento"),
    CANCELED("Cancelado"),
    FINISHED("Concluído");

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