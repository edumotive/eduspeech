package com.djinc.eduspeech.screens

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.djinc.eduspeech.R
import com.djinc.eduspeech.components.ScreenTitle
import com.djinc.eduspeech.components.cards.PartCard
import com.djinc.eduspeech.constants.ContentfulContentModel
import com.djinc.eduspeech.constants.WindowSize
import com.djinc.eduspeech.models.ContentfulExerciseAssemble
import com.djinc.eduspeech.models.ContentfulExerciseManual
import com.djinc.eduspeech.models.ContentfulExerciseRecognition
import com.djinc.eduspeech.models.ContentfulModelStep
import com.djinc.eduspeech.screens.ar.ARActivity
import com.djinc.eduspeech.ui.theme.*
import com.djinc.eduspeech.utils.contentful.Contentful
import com.djinc.eduspeech.utils.contentful.errorCatch

@ExperimentalFoundationApi
@Composable
fun ExerciseDetails(
    exerciseId: String = "",
    exerciseType: ContentfulContentModel,
    nav: NavController,
    windowSize: WindowSize
) {
    val isActiveExerciseLoaded = remember { mutableStateOf(false) }

    when (exerciseType) {
        ContentfulContentModel.EXERCISEASSEMBLE -> {
            val activeExercise = remember { mutableStateOf(ContentfulExerciseAssemble()) }
            LaunchedEffect(key1 = exerciseId) {
                isActiveExerciseLoaded.value = false
                Contentful().fetchExercisesAssembleById(
                    exerciseId,
                    errorCallBack = ::errorCatch
                ) {
                    activeExercise.value = it
                    isActiveExerciseLoaded.value = true
                }
            }
            if (isActiveExerciseLoaded.value) Details(
                exercise = activeExercise.value,
                exerciseType = exerciseType,
                exerciseId = exerciseId,
                nav = nav,
                windowSize = windowSize
            )
        }
        ContentfulContentModel.EXERCISEMANUAL -> {
            val activeExercise = remember { mutableStateOf(ContentfulExerciseManual()) }
            LaunchedEffect(key1 = exerciseId) {
                isActiveExerciseLoaded.value = false
                Contentful().fetchExercisesManualById(exerciseId, errorCallBack = ::errorCatch) {
                    activeExercise.value = it
                    isActiveExerciseLoaded.value = true
                }
            }
            if (isActiveExerciseLoaded.value) Details(
                exercise = activeExercise.value,
                exerciseType = exerciseType,
                exerciseId = exerciseId,
                nav = nav,
                windowSize = windowSize
            )
        }
        ContentfulContentModel.EXERCISERECOGNITION -> {
            val activeExercise = remember { mutableStateOf(ContentfulExerciseRecognition()) }
            LaunchedEffect(key1 = exerciseId) {
                isActiveExerciseLoaded.value = false
                Contentful().fetchExercisesRecognitionById(
                    exerciseId,
                    errorCallBack = ::errorCatch
                ) {
                    activeExercise.value = it
                    isActiveExerciseLoaded.value = true
                }
            }
            if (isActiveExerciseLoaded.value) Details(
                exercise = activeExercise.value,
                exerciseType = exerciseType,
                exerciseId = exerciseId,
                nav = nav,
                windowSize = windowSize
            )
        }
        else -> {}
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Details(
    exercise: Any,
    exerciseType: ContentfulContentModel,
    exerciseId: String,
    nav: NavController,
    windowSize: WindowSize
) {
    val context = LocalContext.current
    val title: String
    val minTime: Int
    val maxTime: Int
    val typeTitle: String
    val typeInfo: String
    val info: String
    val modelsteps: List<ContentfulModelStep>

    when (exerciseType) {
        ContentfulContentModel.EXERCISEASSEMBLE -> {
            exercise as ContentfulExerciseAssemble
            title = exercise.title
            minTime = exercise.minTime
            maxTime = exercise.maxTime
            typeTitle =
                "${stringResource(id = R.string.exercise_type_assemble)} ${stringResource(id = R.string.exercise).lowercase()}"
            typeInfo = stringResource(id = R.string.exercise_type_assemble_info)
            info = exercise.info
            modelsteps = exercise.steps
        }
        ContentfulContentModel.EXERCISEMANUAL -> {
            exercise as ContentfulExerciseManual
            title = exercise.title
            minTime = exercise.minTime
            maxTime = exercise.maxTime
            typeTitle =
                "${stringResource(id = R.string.exercise_type_manual)} ${stringResource(id = R.string.exercise).lowercase()}"
            typeInfo = stringResource(id = R.string.exercise_type_manual_info)
            info = exercise.info
            modelsteps = exercise.steps
        }
        ContentfulContentModel.EXERCISERECOGNITION -> {
            exercise as ContentfulExerciseRecognition
            title = exercise.title
            minTime = exercise.minTime
            maxTime = exercise.maxTime
            typeTitle =
                "${stringResource(id = R.string.exercise_type_recognition)} ${stringResource(id = R.string.exercise).lowercase()}"
            typeInfo = stringResource(id = R.string.exercise_type_recognition_info)
            info = exercise.info
            modelsteps = exercise.steps
        }
        else -> {
            title = ""
            minTime = 0
            maxTime = 0
            typeTitle = ""
            typeInfo = ""
            info = ""
            modelsteps = emptyList()
        }
    }

    Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth(1f)) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = if (windowSize == WindowSize.Compact) 20.dp else 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(if (windowSize == WindowSize.Expanded) 0.5f else 1f)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                ScreenTitle(
                    title = title,
                    spacerHeight = 0,
                    windowSize = windowSize
                )
                Time(minTime = minTime, maxTime = maxTime)
            }
            item {
                Text(
                    text = typeTitle,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = typeInfo,
                    style = MaterialTheme.typography.body2,
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.exercise_info),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = info,
                    style = MaterialTheme.typography.body2,
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.exercise_start_title),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = stringResource(id = R.string.exercise_start_both),
                    style = MaterialTheme.typography.body2,
                )
                if (exerciseType == ContentfulContentModel.EXERCISEASSEMBLE) {
                    Text(
                        text = stringResource(id = R.string.exercise_assemble_name),
                        fontSize = 16.sp,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, ARActivity::class.java)
                            val params = Bundle()
                            params.putString("type", exerciseType.stringValue)
                            params.putString("id", exerciseId)
                            intent.putExtras(params)
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppPrimary
                        ),
                    ) {
                        Text(
                            text = stringResource(R.string.exercise_start_btn_with_ar),
                            color = Background,
                            fontSize = 16.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppSecondary
                        ),
                    ) {
                        Text(
                            text = stringResource(R.string.exercise_start_btn_without_ar),
                            color = AppPrimary,
                            fontSize = 16.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }
                }
                if (exerciseType == ContentfulContentModel.EXERCISEASSEMBLE) {
                    Text(
                        text = stringResource(id = R.string.exercise_disassemble_name),
                        fontSize = 16.sp,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = AppPrimary
                            ),
                        ) {
                            Text(
                                text = stringResource(R.string.exercise_start_btn_with_ar),
                                color = Background,
                                fontSize = 16.sp,
                                fontFamily = fonts,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = AppSecondary
                            ),
                        ) {
                            Text(
                                text = stringResource(R.string.exercise_start_btn_without_ar),
                                color = AppPrimary,
                                fontSize = 16.sp,
                                fontFamily = fonts,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }
                    }
                }
            }
        }
    }
    if (windowSize == WindowSize.Expanded) {
        Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxWidth(1f)) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(if (windowSize == WindowSize.Compact) 80.dp else 120.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = if (windowSize == WindowSize.Compact) PaddingValues(
                    bottom = 100.dp
                ) else PaddingValues(
                    bottom = 24.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(top = 60.dp, end = 40.dp)
            ) {
                modelsteps.forEach { modelstep ->
                    modelstep.models.forEach { model ->
                        item {
                            PartCard(
                                partId = model.id,
                                partType = model.type,
                                partName = model.title,
                                imageUrl = model.image,
                                nav = nav,
                                windowSize = windowSize
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Time(minTime: Int, maxTime: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "Clock icon",
                tint = TextSecondary,
                modifier = Modifier.padding(bottom = 3.dp)
            )
            Text(
                text = "$minTime - $maxTime min",
                color = TextSecondary,
                fontSize = 16.sp
            )
        }
    }
}
