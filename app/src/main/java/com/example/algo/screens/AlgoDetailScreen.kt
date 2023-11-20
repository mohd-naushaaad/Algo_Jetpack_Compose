package com.example.algo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.algo.IconButtonComponent
import com.example.algo.SearchEditTextComponent
import com.example.algo.R
import com.example.algo.TextBodyMediumComponent
import com.example.algo.TextDisplaySmallComponent
import com.example.algo.TextHeadingSmallComponent
import com.example.algo.TextHeadingSmallComponentPreview
import com.example.algo.TextLabelLargeComponent
import com.example.algo.TextTitleLargeComponent
import com.example.algo.TextTitleMediumComponent
import com.example.algo.TextTitleSmallComponent
import com.example.algo.chart.rememberMarker
import com.example.algo.ui.theme.AlgoTheme
import com.example.algo.ui.theme.black1
import com.example.algo.ui.theme.black2
import com.example.algo.ui.theme.black4
import com.example.algo.ui.theme.black5
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
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun AlgoDetailScreen(
    modifier: Modifier,
    onBackScreen: () -> Unit,
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

        for (i in 1..12) {
            val randomYFloat = (0..1000).random().toFloat()
            dataPoints.add(FloatEntry(x = xPos, y = randomYFloat))
            xPos += 1f
        }

        datasetForModel.add(dataPoints)
        modelProducer.setEntries(datasetForModel)

        datasetLineSpec.add(
            LineChart.LineSpec(
                lineColor = darkYellow.toArgb(), lineThicknessDp = 3f,
                lineBackgroundShader = DynamicShaders.fromBrush(
                    brush = Brush.verticalGradient(
                        listOf(
                            darkYellow.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                            darkYellow.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                        )
                    )
                )
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

            Row(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                IconButtonComponent(
                    icon = R.drawable.ic_back
                ) {
                    onBackScreen()
                }

                TextTitleMediumComponent(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 80.dp),
                    text = "Algo Detail"
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                modifier = Modifier
                    .size(90.dp)
                    .offset(y = 30.dp)
                    .zIndex(10f)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_logo_only),
                contentDescription = "Logo only"
            )

            CardDetail()

            Row(modifier = modifier.fillMaxSize()) {
                TextTitleLargeComponent(text = "Performance")

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "arrow up"
                )
            }

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
                                "$${a}"
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
                            when (value) {
                                1f -> "Jan"
                                2f -> "Feb"
                                3f -> "Mar"
                                4f -> "Apr"
                                5f -> "May"
                                6f -> "June"
                                7f -> "Jul"
                                8f -> "Aug"
                                9f -> "Sep"
                                10f -> "Oct"
                                11f -> "Nov"
                                12f -> "Dec"
                                else -> "All"
                            }
                        }),
                        marker = marker
                    )
                }
            }

            Row(modifier = modifier.fillMaxSize()) {
                TextTitleLargeComponent(text = "Description")

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "arrow up"
                )
            }

            TextLabelLargeComponent(
                text = "AI trading apps are designed to be user-friendly and can be used by both beginners and experienced traders. Some apps offer automated trading where the AI makes decisions on your behalf, while others provide trading signals that users can follow.",
                color = white.copy(0.5f),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkYellow
                )
            ) {
                TextTitleSmallComponent(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Trade this Algo",
                    color = black1,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun CardDetail() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8),
        colors = CardDefaults.elevatedCardColors(
            containerColor = white.copy(0.06f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            TextDisplaySmallComponent(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Streak Ninja"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextNewItemComponent(
                        "Percentage win",
                        "50%"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextNewItemComponent(
                        "Algo age",
                        "3 Years"
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextNewItemComponent(
                        "Total profit",
                        "1400"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextNewItemComponent(
                        "Completed trades",
                        "1400"
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
fun TextNewItemComponent(
    textFirst: String,
    textSecond: String,
) {
    Column {
        TextBodyMediumComponent(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = textFirst,
            color = Color.White.copy(0.5f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextBodyMediumComponent(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = textSecond,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AlgoDetailScreenPreview() {
    AlgoTheme {
        AlgoDetailScreen(modifier = Modifier, onBackScreen = {})
    }
}