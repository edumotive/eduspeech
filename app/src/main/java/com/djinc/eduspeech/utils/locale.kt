package com.djinc.eduspeech.utils

import android.content.Context
import com.djinc.eduspeech.MainEdumotive
import com.djinc.eduspeech.R
import java.util.*

@SuppressWarnings("deprecation")
fun changeLocale(context: Context, locale: Locale) {
    with (MainEdumotive.sharedPref!!.edit()) {
        putString(context.getString(R.string.locale), locale.toLanguageTag())
        apply()
    }

    val resources = context.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
}

class SplitTag(tag: String) {
    val language = tag.split("-")[0]
    val country = tag.split("-")[1]
}
