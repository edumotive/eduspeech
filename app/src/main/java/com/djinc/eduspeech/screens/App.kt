package com.djinc.eduspeech.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.djinc.eduspeech.MainEdumotive
import com.djinc.eduspeech.components.menus.BottomBar
import com.djinc.eduspeech.components.menus.SideBar
import com.djinc.eduspeech.components.modals.LanguageModal
import com.djinc.eduspeech.constants.WindowSize
import com.djinc.eduspeech.navigation.NavGraph

@Composable
fun App(windowSize: WindowSize) {
    val navController = rememberNavController()
    if (windowSize == WindowSize.Compact) {
        Scaffold(
            bottomBar = {
                BottomBar(navController = navController)
            },
        ) {
            NavGraph(
                navController = navController,
                windowSize = windowSize
            )
        }
        if (MainEdumotive.isLanguageModalOpen) {
            LanguageModal(windowSize = windowSize)
        }
    } else {
        Row {
            SideBar(navController = navController)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                NavGraph(
                    navController = navController,
                    windowSize = windowSize
                )
            }
        }
        if (MainEdumotive.isLanguageModalOpen) {
            LanguageModal(windowSize = windowSize)
        }
    }
}
