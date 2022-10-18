package com.djinc.eduspeech.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.djinc.eduspeech.R
import com.djinc.eduspeech.constants.ContentfulContentModel
import com.djinc.eduspeech.constants.WindowSize
import com.djinc.eduspeech.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PartCard(
    partId: String,
    partType: ContentfulContentModel,
    partName: String = "",
    imageUrl: String,
    nav: NavController? = null,
    windowSize: WindowSize? = null,
    activePart: String? = null,
    callback: ((String) -> Unit)? = null
) {
    Card(
        backgroundColor = if (activePart == partId) AppSecondary else Background,
        elevation = 3.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = {
            nav?.navigate("part/$partId/${partType.stringValue}")
            callback?.invoke(partId)
        },
        modifier = Modifier
            .width(if (windowSize == WindowSize.Compact) 200.dp else 170.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            CardImage(
                url = imageUrl,
                name = partName,
                aspectRatio = 1f,
                padding = true,
                rounded = true
            )
            PartName(name = partName)
            BottomBar(name = partName)
        }
    }
}

@Composable
fun PartName(name: String) {
    Text(
        text = name,
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = TextPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 12.dp, end = 30.dp)
    )
}

@Composable
fun BottomBar(name: String) {
    Box(
        contentAlignment = Alignment.CenterEnd, modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .background(AppPrimary)
    ) {
        val negativePadding = 8.dp
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .offset(y = -negativePadding)
                .requiredHeight(28.dp)
                .requiredWidth(28.dp)
                .clip(RoundedCornerShape(topStart = 8.dp))
                .background(AppPrimary)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrows_right),
                contentDescription = "See details of part: $name",
                tint = Background,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
    }
}
