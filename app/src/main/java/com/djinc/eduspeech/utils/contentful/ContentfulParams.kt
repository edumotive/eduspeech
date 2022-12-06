package com.djinc.eduspeech.utils.contentful

import com.djinc.eduspeech.BuildConfig

data class ContentfulParams(
    var spaceId: String = "",
    var previewToken: String = "",
    var deliveryToken: String = "",
    var host: String = "",
    var studyTag: String = "",
)

fun parameterFromBuildConfig(): ContentfulParams =
    ContentfulParams(
        spaceId = BuildConfig.CONTENTFUL_SPACE_ID,
        deliveryToken = BuildConfig.CONTENTFUL_DELIVERY_TOKEN,
        previewToken = BuildConfig.CONTENTFUL_PREVIEW_TOKEN,
        host = BuildConfig.CONTENTFUL_HOST,
        studyTag = BuildConfig.CONTENTFUL_STUDY_TAG,
    )
