package com.djinc.eduspeech.utils.contentful

import com.djinc.eduspeech.models.ContentfulModel
import com.djinc.eduspeech.models.ContentfulModelGroup
import kotlinx.coroutines.*

private var debounceJobModel: Job? = null
private var debounceJobModelGroup: Job? = null

private const val charMinimum = 3
private const val delay = 300L

fun filterModelList(
    listModel: List<ContentfulModel>,
    filter: String,
    debounceDelay: Long = delay,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    callback: (List<ContentfulModel>) -> Unit
) {
    debounceJobModel?.cancel()
    debounceJobModel = coroutineScope.launch {
        delay(debounceDelay)
        val value = if (filter.chars()
                .count() >= charMinimum
        ) listModel.filter { model -> model.title.lowercase().contains(filter.lowercase()) } else listModel
        callback(value)
    }
}

fun filterModelGroupList(
    listModelGroup: List<ContentfulModelGroup>,
    filter: String,
    debounceDelay: Long = delay,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    callback: (List<ContentfulModelGroup>) -> Unit
) {
    debounceJobModelGroup?.cancel()
    debounceJobModelGroup = coroutineScope.launch {
        delay(debounceDelay)
        val value = if (filter.chars()
                .count() >= charMinimum
        ) listModelGroup.filter { model -> model.title.lowercase().contains(filter.lowercase()) } else listModelGroup
        callback(value)
    }
}
