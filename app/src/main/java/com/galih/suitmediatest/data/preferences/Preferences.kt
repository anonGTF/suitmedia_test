package com.galih.suitmediatest.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.galih.suitmediatest.SuitmediaApplication

class Preferences private constructor() {
    private val mPrefs: SharedPreferences
    private val mEdit: SharedPreferences.Editor

    val page: Int
        get() = instance.mPrefs.getInt(PrefConstants.PREF_PAGE, 1)

    val totalPage: Int
        get() = instance.mPrefs.getInt(PrefConstants.PREF_TOTAL_PAGE, 0)

    val name: String
        get() = instance.mPrefs.getString(PrefConstants.PREF_NAME, "").orEmpty()

    fun savePage(value: Int) {
        mEdit.putInt(PrefConstants.PREF_PAGE, value)
        mEdit.apply()
    }

    fun saveTotalPage(value: Int) {
        mEdit.putInt(PrefConstants.PREF_TOTAL_PAGE, value)
        mEdit.apply()
    }

    fun saveName(value: String) {
        mEdit.putString(PrefConstants.PREF_NAME, value)
        mEdit.apply()
    }

    companion object {
        var INSTANCE: Preferences? = null
        val instance: Preferences
            get() {
                if (INSTANCE == null) INSTANCE = Preferences()
                return INSTANCE as Preferences
            }
    }

    init {
        val app: Application = SuitmediaApplication.instance
        mPrefs = app.getSharedPreferences(PrefConstants.PREF_KEY, Context.MODE_PRIVATE)
        mEdit = mPrefs.edit()
    }
}