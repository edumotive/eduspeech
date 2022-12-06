package com.djinc.eduspeech.screens.ar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djinc.eduspeech.R
import com.djinc.eduspeech.ui.theme.Background
import com.djinc.eduspeech.ui.theme.AppPrimary
import com.djinc.eduspeech.ui.theme.fonts

@Composable
fun NextButton(callback: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Button(
            onClick = { callback.invoke() },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppPrimary),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                stringResource(R.string.next),
                fontFamily = fonts,
                color = Background,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}
