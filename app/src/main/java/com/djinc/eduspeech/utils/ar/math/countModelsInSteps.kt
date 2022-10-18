package com.djinc.eduspeech.utils.ar.math

import androidx.compose.runtime.mutableStateOf
import com.djinc.eduspeech.models.ContentfulModelStep

fun countModelsInSteps(steps: MutableList<ContentfulModelStep>): Int {
    val amount = mutableStateOf(0)
    steps.forEach { step ->
        amount.value = amount.value + step.getModelCount()
    }
    return amount.value
}
