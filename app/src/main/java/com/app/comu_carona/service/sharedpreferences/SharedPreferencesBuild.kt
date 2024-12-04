package com.app.comu_carona.service.sharedpreferences

interface SharedPreferencesBuilder {
    /**
     * putString this method is used to save string data in shared preferences
     * @param key this is key of data
     * @param data this is data that you want to save
     */
    fun putString(key: String, data: String)

    /**
     * putBoolean this method is used to save boolean data in shared preferences
     * @param key this is key of data
     * @param data this is data that you want to save
     */
    fun putBoolean(key: String, data: Boolean)

    /**
     * getString this method is used to get string data from shared preferences
     * @param key this is key of data
     * @param defaultValue this is default value if data is not found
     * @return String this is return string data
     */
    fun getString(key: String, defaultValue: String?): String?

    /**
     * getBoolean this method is used to get boolean data from shared preferences
     * @param key this is key of data
     * @param defaultValue this is default value if data is not found
     * @return Boolean this is return boolean data
     */
    fun getBoolean(key: String, defaultValue: Boolean?): Boolean
}