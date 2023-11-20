package com.example.algo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.algo.IconButtonComponent
import com.example.algo.SearchEditTextComponent
import com.example.algo.R
import com.example.algo.TextBodyMediumComponent
import com.example.algo.TextDisplaySmallComponent
import com.example.algo.TextHeadingSmallComponent
import com.example.algo.TextHeadingSmallComponentPreview
import com.example.algo.TextTitleLargeComponent
import com.example.algo.TextTitleSmallComponent
import com.example.algo.ui.theme.AlgoTheme
import com.example.algo.ui.theme.black4
import com.example.algo.ui.theme.black5
import com.example.algo.ui.theme.blackBackground
import com.example.algo.ui.theme.darkYellow
import com.example.algo.ui.theme.white

@Composable
fun AlgoScreen(
    modifier: Modifier,
    onAlgoDetailScreen: () -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(blackBackground)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                TextDisplaySmallComponent(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Algo"
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButtonComponent(
                    icon = R.drawable.ic_filter
                ) {

                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            var searchText by rememberSaveable { mutableStateOf("") }

            SearchEditTextComponent(
                modifier = Modifier.fillMaxWidth(),
                textState = searchText,
                onTextChange = {
                    searchText = it
                },
                label = "Search",
                icon = R.drawable.ic_search,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextBodyMediumComponent(
                text = "See recommended algos first",
                color = Color.White.copy(0.5f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            for (i in 0 until 5) {
                CardItem(onAlgoDetailScreen)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

}

@Composable
fun CardItem(
    onAlgoDetailScreen: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16),
        colors = CardDefaults.elevatedCardColors(
            containerColor = black4
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Row {

                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_logo_only),
                    contentDescription = "Logo only"
                )

                Spacer(modifier = Modifier.width(16.dp))

                TextHeadingSmallComponent(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Streak Ninja"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Column {
                    TextItemComponent(
                        "Total profit",
                        "1400"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextItemComponent(
                        "Algo age",
                        "3 Years"
                    )
                }

                Spacer(modifier = Modifier.width(18.dp))

                Column {
                    TextItemComponent(
                        "Percentage win",
                        "50%"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextItemComponent(
                        "Completed trades",
                        "1400"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    onAlgoDetailScreen()
                }
            ) {

                TextTitleSmallComponent(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "View Detail",
                    color = darkYellow,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.width(8.dp))

                FilledIconButton(
                    onClick = { onAlgoDetailScreen() },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = darkYellow,
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_view_details),
                        contentDescription = "view detail"
                    )
                }
            }

        }
    }
}


@Composable
fun TextItemComponent(
    textFirst: String,
    textSecond: String,
) {
    Row {
        TextBodyMediumComponent(
            text = textFirst,
            color = Color.White.copy(0.5f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextBodyMediumComponent(
            text = textSecond,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun AlgoScreenPreview() {
    AlgoTheme {
        AlgoScreen(modifier = Modifier, onAlgoDetailScreen = {})
    }
}