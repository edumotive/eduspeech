package com.djinc.eduspeech.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.djinc.eduspeech.MainEdumotive
import com.djinc.eduspeech.R
import com.djinc.eduspeech.components.LazySlider
import com.djinc.eduspeech.components.ScreenTitle
import com.djinc.eduspeech.constants.SliderComponent
import com.djinc.eduspeech.constants.SliderDirection
import com.djinc.eduspeech.constants.WindowSize

@ExperimentalFoundationApi
@Composable
fun Exercises(nav: NavController, windowSize: WindowSize) {
    Column(modifier = Modifier.padding(horizontal = if (windowSize == WindowSize.Compact) 20.dp else 40.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        ScreenTitle(
            title = stringResource(R.string.exercises),
            windowSize = windowSize
        )
        LazySlider(
            direction = SliderDirection.Vertical,
            list = MainEdumotive.exerciseAssemble,
            list2 = MainEdumotive.exercisesManual,
            list3 = MainEdumotive.exerciseRecognition,
            component = SliderComponent.ExerciseCard,
            nav = nav,
            windowSize = windowSize
        )
    }
}
