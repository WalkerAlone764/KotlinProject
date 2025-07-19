package org.example.project

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronDown
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


private const val MAX_PAGE_SIZE = 2

@Composable
fun Portfolio(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val pagerState = rememberPagerState() {
        MAX_PAGE_SIZE
    }

    val isLastPage by remember {
        derivedStateOf {
            pagerState.currentPage == pagerState.pageCount - 1
        }
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        scrollState.scrollToItem(index = 2)
                    }
                }
            ) {
                Text(text = "+")
            }
        }
    ) { innerPadding ->


        VerticalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            snapPosition = SnapPosition.Start,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->

            if (page == 0) {
                HeaderSection()
            } else {
                MainContent(
                    scrollState = scrollState,
                    innerPadding = innerPadding
                )
            }
        }

    }
    HoverMenu()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,

        ) {
        if (!isLastPage) {
            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = FeatherIcons.ChevronDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
    }

}

@Composable
fun MainContent(
    scrollState: LazyListState,
    innerPadding: PaddingValues
) {

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        item { TechStack() }

        item { Separator() }

        item { TechStack() }

        item { Separator() }

        item { TechStack() }
    }
}


@Composable
private fun Separator() {
    HorizontalDivider(
        color = Color.Green
    )
}

@Composable
fun HeaderSection() {
    var onStart by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        onStart = true
    }

    val animateBlur by animateDpAsState(
        targetValue = if (onStart) 0.dp else 10.dp,
        animationSpec = tween(
            durationMillis = 4000,
        )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .height(480.dp)
            .fillMaxSize()
//            .background(Color.Black)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
//                        Color(0xffd9328d),
//                        Color(0xff8e44f9)
                        Color(0xff4e98fe),
                        Color(0xff864afb),
                        Color(0xffba42ed)
                    )
                )
            )
            .blur(animateBlur),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)

        ) {

            BasicText(
                text = "Hi, My name is Jatin Singhal",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    color = Color.White,
                ),
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 12.sp,
                    maxFontSize = 38.sp,
                    stepSize = 0.12.sp
                ),
            )


            BasicText(
                text = "An Android Developer",
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 8.sp,
                    maxFontSize = 18.sp,
                    stepSize = 0.25.sp
                ),
            )
        }
    }
}


@Composable
fun TechStack() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 320.dp)

            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "Tech Stack",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        FlowRow(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically)
        ) {
            repeat(techStackDataset.size) { index ->
                TechBox(
                    res = techStackDataset[index].id
                )
            }
        }
    }

}

@Composable
private fun TechBox(
    res: DrawableResource
) {
    Box(
        modifier = Modifier
            .size(128.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(Color(255, 255, 255, 15))
//            .background(Color.White.copy(alpha = 0.2f))
//            .blur(1.dp)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(12.dp)
        )
        Image(
            painter = painterResource(res),
            contentDescription = null
        )
    }
}