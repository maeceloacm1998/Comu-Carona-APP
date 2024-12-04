package com.app.comu_carona.service.retrofit

import com.app.comu_carona.service.sharedpreferences.SharedPreferencesBuilder
import org.koin.core.annotation.Single

@Single
class AuthPreferences(
    private val sharedPreferences: SharedPreferencesBuilder
) {

    /**
     * accessToken this is access token of user
     * @return String this is return access token
     * @param value this is value of access token
     */
    var accessToken: String?
        get() = sharedPreferences.getString("access_token", null)
        set(value) {
            if (value != null) {
                sharedPreferences.putString("access_token", value)
            }
        }

    /**
     * refreshToken this is refresh token of user
     * @return String this is return refresh token
     * @param value this is value of refresh token
     */
    var refreshToken: String?
        get() = sharedPreferences.getString("refresh_token", null)
        set(value) {
            if (value != null) {
                sharedPreferences.putString("refresh_token", value)
            }
        }

    /**
     * saveTokens this method is used to save access token and refresh token in shared preferences
     * @param accessToken this is access token of user
     * @param refreshToken this is refresh token of user
     * @return Unit this is return nothing
     */
    fun saveTokens(accessToken: String, refreshToken: String) {
        sharedPreferences.apply {
            putString("access_token", accessToken)
            putString("refresh_token", refreshToken)
        }
    }
}