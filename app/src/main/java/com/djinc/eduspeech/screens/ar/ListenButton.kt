package com.djinc.eduspeech.screens.ar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djinc.eduspeech.R
import com.djinc.eduspeech.constants.WindowSize
import com.djinc.eduspeech.ui.theme.AppPrimary
import com.djinc.eduspeech.ui.theme.Background
import com.djinc.eduspeech.ui.theme.fonts

@Composable
fun ListenButton(callback: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp + 1
    val windowSize =
        if (screenWidth < 600) WindowSize.Compact else if (screenWidth < 840) WindowSize.Medium else WindowSize.Expanded

    Box(
        contentAlignment = if (windowSize == WindowSize.Expanded) Alignment.BottomStart else Alignment.TopEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Button(
                onClick = { callback.invoke() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Background),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text(
                    text = stringResource(R.string.listen),
                    color = AppPrimary,
                    fontSize = 16.sp,
                    fontFamily = fonts,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
        }
    }
}