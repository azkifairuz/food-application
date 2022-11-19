package com.javfairuz.foodapplication

import android.content.Context

class SharedPreference (val context: Context) {
    private val sharedName = "sahred_name"
    private val sharedPref = context.getSharedPreferences(sharedName,Context.MODE_PRIVATE)
    private val editor     = sharedPref.edit()

    fun setSessionString(key: String, value: String){
        editor.putString(key,value)
        editor.commit()
    }

    fun setSessionBool(key: String, value: Boolean){
        editor.putBoolean(key,value)
        editor.commit()
    }

    fun getSessionString(key : String ): String? {
        return sharedPref.getString(key,null)

    }

    fun getSessionBool(key : String ): Boolean {
        return sharedPref.getBoolean(key,false)

    }

    fun clearSession(){
        editor.clear()
            .apply()
    }

    companion object{
        const val key_level = "key_level"
        const val PREF_IS_LOGIN = "PREF_IS_lOGIN"
        const val PREF_USERNAME = "PREF_USERNAME"
        const val PREF_PASSWORD = "PREF_PASWORD"
    }

}