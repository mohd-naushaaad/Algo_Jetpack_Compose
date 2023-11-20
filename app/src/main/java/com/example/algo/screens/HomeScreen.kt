package com.example.algo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algo.BoxIndicatorComponent
import com.example.algo.IconButtonComponent
import com.example.algo.R
import com.example.algo.TextLabelLargeComponent
import com.example.algo.TextDisplayLargeComponent
import com.example.algo.TextHeadingSmallComponent
import com.example.algo.TextBodyMediumComponent
import com.example.algo.TextTitleSmallComponent
import com.example.algo.chart.rememberMarker
import com.example.algo.ui.theme.AlgoTheme
import com.example.algo.ui.theme.black2
import com.example.algo.ui.theme.blackBackground
import com.example.algo.ui.theme.darkYellow
import com.example.algo.ui.theme.lightYellow
import com.example.algo.ui.theme.white
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
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
fun HomeScreen(
    modifier: Modifier,
    onWalletScreen: () -> Unit,
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
        val dataPoints1 = arrayListOf<FloatEntry>()
        val dataPoints2 = arrayListOf<FloatEntry>()

        for (i in 1..8) {
            val randomYFloat1 = (0..100).random().toFloat()
            val randomYFloat2 = (0..100).random().toFloat()
            dataPoints1.add(FloatEntry(x = xPos, y = randomYFloat1))
            dataPoints2.add(FloatEntry(x = xPos, y = randomYFloat2))
            xPos += 1f
        }

        datasetForModel.add(dataPoints1)
        datasetForModel.add(dataPoints2)
        modelProducer.setEntries(datasetForModel)

        datasetLineSpec.add(
            LineChart.LineSpec(
                lineColor = darkYellow.toArgb(), lineThicknessDp = 3f/*lineBackgroundShader = DynamicShaders.fromBrush(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Green.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                            Color.Green.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                        )
                    )
                )*/
            )
        )
        datasetLineSpec.add(
            LineChart.LineSpec(
                lineColor = lightYellow.toArgb(), lineThicknessDp = 3f
            )
        )
    }

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
                IconButtonComponent(icon = R.drawable.ic_wallet) {
                    onWalletScreen()
                }
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .height(35.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "logo"
                )

                IconButtonComponent(icon = R.drawable.ic_bell, isBadge = true) {

                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextTitleSmallComponent(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Current Balance",
                color = Color.White.copy(0.5f)
            )

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                TextHeadingSmallComponent(
                    modifier = Modifier.padding(top = 8.dp), text = "$"
                )

                TextDisplayLargeComponent(
                    text = "275,458"
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Icon(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_upload),
                    contentDescription = "upload",
                    tint = white,
                )

                TextBodyMediumComponent(text = "15% last 30 days")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(50))
                    .background(Color.White.copy(0.06f))
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 32.dp)
            ) {

                IconButtonComponent(
                    icon = R.drawable.ic_download, size = 36.dp
                ) {}

                Column(modifier = Modifier.padding(start = 12.dp)) {
                    TextLabelLargeComponent(text = "Spent", color = Color.White.copy(0.5f))
                    TextBodyMediumComponent(text = "$17,547.99")
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButtonComponent(
                    icon = R.drawable.ic_upload, size = 36.dp
                ) {}

                Column(modifier = Modifier.padding(start = 12.dp)) {
                    TextLabelLargeComponent(text = "Profit/Loss", color = Color.White.copy(0.5f))
                    TextBodyMediumComponent(text = "+$15,310.99")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (datasetForModel.isNotEmpty()) {

                ProvideChartStyle {
                    Chart(
                        modifier = Modifier.height(320.dp),
                        chart = lineChart(
                            lines = datasetLineSpec,
                            spacing = 70.dp,
                        ),
                        chartModelProducer = modelProducer,
                        chartScrollState = scrollState,
                        isZoomEnabled = true,
                        startAxis = rememberStartAxis(
                            label = axisLabelComponent(
                                textSize = 12.sp,
                                color = white.copy(0.5f),
                                horizontalMargin = 10.dp,
                            ),
                            axis = axisLineComponent(),
                            tickLength = 0.dp,
                            valueFormatter = { value, _ ->
                                val a = value.toInt().toString()
                                "${a}k"
                            },
                            itemPlacer = AxisItemPlacer.Vertical.default(
                                maxItemCount = 6
                            ),
                            guideline = null,
                        ),
                        bottomAxis = rememberBottomAxis(label = axisLabelComponent(
                            textSize = 12.sp,
                            color = white.copy(0.5f),
                        ), axis = axisLineComponent(), guideline = axisGuidelineComponent(
                            color = black2,
                            thickness = 2.dp,
                        ), tickLength = 0.dp, valueFormatter = { value, _ ->
                            val a = ((value.toInt()) * 3).toString()
                            "$a.00"
                        }),
                        marker = marker
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                BoxIndicatorComponent(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp),
                    color = darkYellow
                )

                TextBodyMediumComponent(
                    modifier = Modifier.align(Alignment.CenterVertically), text = "Spent"
                )

                Spacer(modifier = Modifier.width(32.dp))

                BoxIndicatorComponent(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp),
                    color = lightYellow
                )

                TextBodyMediumComponent(
                    modifier = Modifier.align(Alignment.CenterVertically), text = "Profit/Loss"
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    AlgoTheme {
        HomeScreen(modifier = Modifier, onWalletScreen = {})
    }
}