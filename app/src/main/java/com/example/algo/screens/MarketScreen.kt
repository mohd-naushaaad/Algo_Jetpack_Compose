package com.example.algo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algo.CircularImageWithBackground
import com.example.algo.DashedVerticalLine
import com.example.algo.IconButtonComponent
import com.example.algo.R
import com.example.algo.TextBodyMediumComponent
import com.example.algo.TextDisplaySmallComponent
import com.example.algo.TextTitleLargeComponent
import com.example.algo.TextTitleMediumComponent
import com.example.algo.TextTitleSmallComponent
import com.example.algo.chart.rememberMarker
import com.example.algo.ui.theme.AlgoTheme
import com.example.algo.ui.theme.black1
import com.example.algo.ui.theme.black2
import com.example.algo.ui.theme.black4
import com.example.algo.ui.theme.black6
import com.example.algo.ui.theme.black7
import com.example.algo.ui.theme.blackBackground
import com.example.algo.ui.theme.darkYellow
import com.example.algo.ui.theme.greenGraph
import com.example.algo.ui.theme.greenGraphBackground
import com.example.algo.ui.theme.lightYellow
import com.example.algo.ui.theme.red
import com.example.algo.ui.theme.redGraph
import com.example.algo.ui.theme.redGraphBackground
import com.example.algo.ui.theme.white
import com.google.android.material.tabs.TabItem
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun MarketScreen(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(blackBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                TextDisplaySmallComponent(
                    modifier = Modifier.align(Alignment.CenterVertically), text = "Market Alerts"
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButtonComponent(
                    icon = R.drawable.ic_search
                ) {

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TabScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen() {
    val tabs = listOf("News", "Alerts")

    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(key1 = selectedItemIndex) {
        pagerState.animateScrollToPage(selectedItemIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        selectedItemIndex = pagerState.currentPage
    }

    Column {

        TabRow(
            selectedTabIndex = selectedItemIndex,
            containerColor = Color.Transparent,
            indicator = {
                if (selectedItemIndex < it.size) {
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(it[selectedItemIndex]),
                        color = darkYellow,
                        height = 2.dp
                    )
                }

            },
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedItemIndex,
                    onClick = { selectedItemIndex = index },
                    text = {
                        TextTitleSmallComponent(
                            text = tabs[index]
                        )
                    },
                    selectedContentColor = white,
                    unselectedContentColor = white
                )
            }
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->

            Box(
                modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
            ) {
                if (index == 1) {
                    AlertScreen()
                } else {
                    TextTitleSmallComponent(text = "News")
                }
            }
        }
    }
}

@Composable
fun AlertScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        AlertScreenItem(
            graphBackgroundColor = redGraphBackground,
            graphLineColor = redGraph,
            percentColor = redGraph,
            amount = "$1699.48",
            percent = "-24.26%",
            companyName = "Apple Inc.",
            companyShortName = "AAPL",
            companyIcon = R.drawable.apple
        )

        AlertScreenItem(
            graphBackgroundColor = greenGraphBackground,
            graphLineColor = greenGraph,
            percentColor = greenGraph,
            amount = "$1899.48",
            percent = "+1.76%",
            companyName = "Microsoft Corporation",
            companyShortName = "MSFT",
            companyIcon = R.drawable.windows
        )

        AlertScreenItem(
            graphBackgroundColor = redGraphBackground,
            graphLineColor = redGraph,
            percentColor = redGraph,
            amount = "$1699.48",
            percent = "-24.26%",
            companyName = "Google Inc.",
            companyShortName = "GGL",
            companyIcon = R.drawable.google
        )

        AlertScreenItem(
            graphBackgroundColor = greenGraphBackground,
            graphLineColor = greenGraph,
            percentColor = greenGraph,
            amount = "$1899.48",
            percent = "+1.76%",
            companyName = "Meta Platforms Inc.",
            companyShortName = "META",
            companyIcon = R.drawable.meta
        )

        AlertScreenItem(
            graphBackgroundColor = redGraphBackground,
            graphLineColor = redGraph,
            percentColor = redGraph,
            amount = "$1699.48",
            percent = "-24.26%",
            companyName = "Apple Inc.",
            companyShortName = "AAPL",
            companyIcon = R.drawable.apple
        )

        AlertScreenItem(
            graphBackgroundColor = greenGraphBackground,
            graphLineColor = greenGraph,
            percentColor = greenGraph,
            amount = "$1899.48",
            percent = "+1.76%",
            companyName = "Microsoft Corporation",
            companyShortName = "MSFT",
            companyIcon = R.drawable.windows
        )

        AlertScreenItem(
            graphBackgroundColor = redGraphBackground,
            graphLineColor = redGraph,
            percentColor = redGraph,
            amount = "$1699.48",
            percent = "-24.26%",
            companyName = "Google Inc.",
            companyShortName = "GGL",
            companyIcon = R.drawable.google
        )

        AlertScreenItem(
            graphBackgroundColor = greenGraphBackground,
            graphLineColor = greenGraph,
            percentColor = greenGraph,
            amount = "$1899.48",
            percent = "+1.76%",
            companyName = "Meta Platforms Inc.",
            companyShortName = "META",
            companyIcon = R.drawable.meta
        )

    }
}

@Composable
fun AlertScreenItem(
    graphBackgroundColor: Color,
    graphLineColor: Color,
    percentColor: Color,
    amount: String,
    percent: String,
    companyName: String,
    companyShortName: String,
    companyIcon: Int,
) {
    Row {

        Column {

            CircularImageWithBackground(
                icon = companyIcon,
            )

            DashedVerticalLine(
                modifier = Modifier.padding(start = 22.dp),
                height = 135.dp, width = 1.dp, color = black7
            )

        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                TextTitleSmallComponent(
                    fontWeight = FontWeight.Medium, text = companyShortName
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextTitleSmallComponent(
                    color = Color.White.copy(0.5f),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = companyName
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            GraphComponent(
                graphBackgroundColor = graphBackgroundColor,
                graphLineColor = graphLineColor,
                percentColor = percentColor,
                amount = amount,
                percent = percent
            )
        }
    }
}

@Composable
fun GraphComponent(
    graphBackgroundColor: Color,
    graphLineColor: Color,
    percentColor: Color,
    amount: String,
    percent: String,
) {
    val modelProducer = remember { ChartEntryModelProducer() }
    val datasetForModel = remember { mutableStateListOf(listOf<FloatEntry>()) }
    val datasetLineSpec = remember { arrayListOf<LineChart.LineSpec>() }
    val scrollState = rememberChartScrollState()
    val marker = rememberMarker()

    LaunchedEffect(key1 = true) {
        datasetForModel.clear()
        datasetLineSpec.clear()
        var xPos = 1f
        val dataPoints = arrayListOf<FloatEntry>()

        for (i in 1..1000) {
            val randomYFloat = (0..100).random().toFloat()
            dataPoints.add(FloatEntry(x = xPos, y = randomYFloat))
            xPos += 1f
        }

        datasetForModel.add(dataPoints)
        modelProducer.setEntries(datasetForModel)

        datasetLineSpec.add(
            LineChart.LineSpec(
                lineColor = graphLineColor.toArgb(), lineThicknessDp = 1f
            )
        )
    }

    Card(
        shape = RoundedCornerShape(16), colors = CardDefaults.elevatedCardColors(
            containerColor = graphBackgroundColor
        )
    ) {

        Row {
            if (datasetForModel.isNotEmpty()) {

                ProvideChartStyle {
                    Chart(
                        modifier = Modifier
                            .height(120.dp)
                            .width(130.dp),
                        chart = lineChart(
                            lines = datasetLineSpec, spacing = 10.dp
                        ),
                        chartModelProducer = modelProducer,
                        chartScrollState = scrollState,
                        isZoomEnabled = true,
                        marker = marker
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 20.dp)
            ) {
                TextTitleLargeComponent(
                    modifier = Modifier.align(Alignment.End), text = amount
                )
                TextBodyMediumComponent(
                    modifier = Modifier.align(Alignment.End), text = percent, color = percentColor
                )
            }
        }

    }
}

@Preview
@Composable
fun MarketScreenPreview() {
    AlgoTheme {
        // MarketScreen(modifier = Modifier)
    }
}