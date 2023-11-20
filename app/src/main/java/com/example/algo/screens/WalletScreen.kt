package com.example.algo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.algo.IconButtonComponent
import com.example.algo.R
import com.example.algo.TextBodyMediumComponent
import com.example.algo.TextDisplayLargeComponent
import com.example.algo.TextHeadingSmallComponent
import com.example.algo.TextLabelLargeComponent
import com.example.algo.TextTitleLargeComponent
import com.example.algo.TextTitleMediumComponent
import com.example.algo.TextTitleSmallComponent
import com.example.algo.ui.theme.AlgoTheme
import com.example.algo.ui.theme.black1
import com.example.algo.ui.theme.black4
import com.example.algo.ui.theme.blackBackground
import com.example.algo.ui.theme.darkYellow
import com.example.algo.ui.theme.green
import com.example.algo.ui.theme.red
import com.example.algo.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    modifier: Modifier,
    onBackScreen: () -> Unit,
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
                IconButtonComponent(
                    icon = R.drawable.ic_back
                ) {
                    onBackScreen()
                }

                Spacer(modifier = Modifier.weight(0.5f))

                TextTitleMediumComponent(
                    modifier = Modifier.align(Alignment.CenterVertically), text = "My Wallet"
                )

                Spacer(modifier = Modifier.weight(0.5f))

                IconButtonComponent(
                    icon = R.drawable.ic_bell, isBadge = true
                ) {

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

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

            Spacer(modifier = Modifier.height(28.dp))

            Row {
                CardSend(Modifier.weight(1f))
                Spacer(modifier = Modifier.width(12.dp))
                CardAdd(Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(28.dp))

            val sheetState = rememberModalBottomSheetState()
            var showBottomSheet by remember { mutableStateOf(false) }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showBottomSheet = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkYellow
                )
            ) {
                TextTitleSmallComponent(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Transaction",
                    color = black1,
                    fontWeight = FontWeight.Medium
                )
            }

            if (showBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    }, sheetState = sheetState,
                    containerColor = black1
                ) {
                    BottomSheetContent()
                }
            }

        }
    }
}

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_sort_horizontal),
                contentDescription = "sort horizontal",
                tint = darkYellow,
            )

            Spacer(modifier = Modifier.width(4.dp))

            TextTitleLargeComponent(text = "Transactions", color = Color.White)

            Spacer(modifier = Modifier.weight(1f))

            TextBodyMediumComponent(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "View all",
                color = darkYellow,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TransactionItem(
            icon = R.drawable.ic_arrow_receive,
            heading = "Received Amount",
            description = "30 May 2023",
            amount = "+$180.00",
            amountColor = green
        )

        TransactionItem(
            icon = R.drawable.ic_arrow_view_details,
            heading = "Send Amount",
            description = "30 May 2023",
            amount = "-$150.00",
            amountColor = red
        )

        TransactionItem(
            icon = R.drawable.ic_arrow_receive,
            heading = "Received Amount",
            description = "30 May 2023",
            amount = "+$180.00",
            amountColor = green
        )

        TransactionItem(
            icon = R.drawable.ic_arrow_view_details,
            heading = "Send Amount",
            description = "30 May 2023",
            amount = "-$150.00",
            amountColor = red
        )

        TransactionItem(
            icon = R.drawable.ic_arrow_receive,
            heading = "Received Amount",
            description = "30 May 2023",
            amount = "+$180.00",
            amountColor = green
        )

        TransactionItem(
            icon = R.drawable.ic_arrow_view_details,
            heading = "Send Amount",
            description = "30 May 2023",
            amount = "-$150.00",
            amountColor = red
        )

    }
}

@Composable
fun CardSend(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16),
        colors = CardDefaults.elevatedCardColors(
            containerColor = white.copy(0.06f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            FilledIconButton(
                onClick = { }, colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = darkYellow,
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_view_details),
                    contentDescription = "view detail"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row {

                TextTitleSmallComponent(
                    text = "Send \nAmount", color = Color.White, fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButtonComponent(
                    size = 36.dp, icon = R.drawable.ic_arrow_right
                ) {

                }

            }
        }
    }
}

@Composable
fun CardAdd(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16),
        colors = CardDefaults.elevatedCardColors(
            containerColor = white.copy(0.06f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            FilledIconButton(
                onClick = { }, colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = darkYellow,
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_receive),
                    contentDescription = "view detail"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row {

                TextTitleSmallComponent(
                    text = "Add \nAmount", color = Color.White, fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButtonComponent(
                    size = 36.dp, icon = R.drawable.ic_arrow_right
                ) {

                }

            }

        }
    }
}


@Composable
fun TransactionItem(
    icon: Int, heading: String, description: String, amount: String, amountColor: Color
) {

    Column {

        Row {

            IconButtonComponent(
                icon = icon,
                size = 40.dp
            ) {}

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                TextTitleSmallComponent(
                    text = heading,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                TextBodyMediumComponent(
                    text = description,
                    color = Color.White.copy(0.5f),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            TextBodyMediumComponent(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = amount,
                color = amountColor,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview
@Composable
fun WalletScreenPreview() {
    AlgoTheme {
        WalletScreen(modifier = Modifier) {

        }
    }
}