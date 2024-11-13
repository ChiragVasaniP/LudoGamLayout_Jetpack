package com.example.ludojetpackcompose.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.ludojetpackcompose.ui.theme.ThemePreviews

@Composable
fun GameBox(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val boxHeight = screenHeight / (2.25f)
    val boxWidth = boxHeight

    ConstraintLayout(
        modifier = modifier
            .height(boxHeight)
            .width(boxWidth)
            .background(MaterialTheme.colorScheme.inverseSurface)

    ) {
        val (verticalLine, horizontalLine, topLeftBox, topRightBox, bottomLeftBox, bottomRightBox, centerBox) = createRefs()

        val targetNumberOfBoxes = 15

        // Calculate the size of each box based on the target number of boxes
        val gamePathBoxHeight = boxHeight / targetNumberOfBoxes
        val gamePathBoxWidth = gamePathBoxHeight  // Keep it square

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(topLeftBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(verticalLine.start)
                    bottom.linkTo(horizontalLine.top)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                },
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(topRightBox) {
                    top.linkTo(parent.top)
                    start.linkTo(verticalLine.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(horizontalLine.top)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                },
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(bottomLeftBox) {
                    top.linkTo(horizontalLine.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(verticalLine.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                },
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(bottomRightBox) {
                    top.linkTo(horizontalLine.bottom)
                    start.linkTo(verticalLine.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                },
        )



        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .height(gamePathBoxWidth * 2)
                .constrainAs(horizontalLine) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,

            ) {
            items(targetNumberOfBoxes * 3) { index ->
                val currentIndex =  index+1
                val backgroundColor =
                    if (currentIndex == 4 || currentIndex == 9 || currentIndex == 42 || currentIndex == 37) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface
                Box(
                    modifier = Modifier
                        .height(gamePathBoxHeight)
                        .width(gamePathBoxWidth)
                        .background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .width(gamePathBoxWidth * 2)
                .constrainAs(verticalLine) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,

            ) {

            items(targetNumberOfBoxes * 3) { index ->
                val currentIndex = index + 1
                val backgroundColor =
                    if (currentIndex == 6 || currentIndex == 7 || currentIndex == 40 || currentIndex == 39) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface
                Box(
                    modifier = Modifier
                        .height(gamePathBoxHeight)
                        .width(gamePathBoxWidth)
                        .background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        //Center
        Box(
            modifier = Modifier
                .height(gamePathBoxHeight * 2.85f)
                .width(gamePathBoxHeight * 2.85f)
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.error)
                .constrainAs(centerBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        )

    }
}


@Composable
@ThemePreviews
private fun ShowGameView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)

    ) {
        val (centerGameView) = createRefs()
        GameBox(modifier = Modifier.constrainAs(centerGameView) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        })
    }

}