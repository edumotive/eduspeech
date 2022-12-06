package com.djinc.eduspeech

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.djinc.eduspeech.constants.Common
import com.djinc.eduspeech.constants.WindowSize
import com.djinc.eduspeech.screens.App
import com.djinc.eduspeech.ui.theme.EdumotiveTheme
import com.djinc.eduspeech.utils.SplitTag
import com.djinc.eduspeech.utils.changeLocale
import com.djinc.eduspeech.utils.rememberWindowSizeClass
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = rememberWindowSizeClass()
            if (windowSize != WindowSize.Compact) requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            EdumotiveTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val currentLocale = MainEdumotive.sharedPref!!.getString(
                        getString(R.string.locale),
                        Common.defaultLanguage
                    )

                    if (currentLocale != null) {
                        changeLocale(
                            LocalContext.current,
                            Locale(SplitTag(currentLocale).language, SplitTag(currentLocale).country)
                        )
                        MainEdumotive.currentLocale = currentLocale
                        MainEdumotive.contentfulCachedContent!!.locale = currentLocale
                    }
                    App(windowSize)
                }
            }
        }
    }
}
