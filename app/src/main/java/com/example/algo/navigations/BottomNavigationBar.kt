package com.example.algo.navigations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.algo.R
import com.example.algo.ui.theme.black1
import com.example.algo.ui.theme.bottomNavBarColor
import com.example.algo.ui.theme.darkYellow
import com.example.algo.ui.theme.roboto
import com.example.algo.ui.theme.white
import com.example.algo.ui.theme.white40
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val bottomMenu = listOf(
        MainRoute.Home, MainRoute.Algo, MainRoute.Empty, MainRoute.Market, MainRoute.Menu
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavigationBarDestination = bottomMenu.any { it.route == currentDestination?.route }
    if (bottomNavigationBarDestination) {

        ConstraintLayout {

            val (row, fab) = createRefs()


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(30, 30))
                    .background(bottomNavBarColor)
                    .constrainAs(row) {
                    },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                bottomMenu.forEach { screen ->
                    AddItem(
                        menu = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }

            FloatingActionButton(
                containerColor = darkYellow,
                shape = RoundedCornerShape(100),
                modifier = Modifier.constrainAs(fab) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                onClick = { },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus), "",
                    tint = black1
                )
            }
        }
    }
}

@Composable
fun AddItem(
    menu: MainRoute, currentDestination: NavDestination?, navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == menu.route } == true

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable(onClick = {
                navController.navigate(menu.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 24.dp)
                .align(Alignment.Center),
        ) {
            if (menu.title.isNotEmpty()) {
                Image(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    painter = painterResource(
                        id = if (selected) menu.iconFocused else menu.icon
                    ),
                    contentDescription = "icon",
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 12.sp,
                    fontFamily = roboto,
                    fontWeight = FontWeight.Normal,
                    text = menu.title,
                    color = if (selected) white else white40
                )
            }
        }
    }
}
