package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun HoverMenu() {


    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isHover by interactionSource.collectIsHoveredAsState()

    print(isHover)
    val animatedMaxWidth by animateDpAsState(
        targetValue = if (isHover) 260.dp else 0.dp
    )



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart,

        ) {
        Box(
            modifier = Modifier
                .hoverable(interactionSource)
                .fillMaxHeight()
                .width(80.dp),
            contentAlignment = Alignment.CenterStart
        ) {


            if (!isHover) {
                Box(
                    modifier = Modifier
                    .width(2.dp)
                        .height(60.dp)
                        .background(Color.White.copy(0.5f))
                )
            }
            AnimatedVisibility(
                isHover
            ) {
                Column(
                    modifier = Modifier

                        .widthIn(max = animatedMaxWidth)
//                .height(300.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(0.5f))
                        .padding(12.dp)

                ) {

                    Column {
                        (1..10).forEach {
                            Text(text = "jdjp $it")
                        }
                    }
                }

            }
        }
    }
}