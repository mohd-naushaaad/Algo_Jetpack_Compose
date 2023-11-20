package com.example.algo.navigations

import com.example.algo.R

sealed class MainRoute(
    val route: String,
    val title: String,
    val icon: Int,
    val iconFocused: Int
) {
    object Home : MainRoute(
        route = Route.HOME_BOTTOM_BAR.value,
        title = "Home",
        icon = R.drawable.ic_home,
        iconFocused = R.drawable.ic_home_filled,
    )

    object Algo : MainRoute(
        route = Route.ALGO_BOTTOM_BAR.value,
        title = "Algo",
        icon = R.drawable.ic_algo,
        iconFocused = R.drawable.ic_algo_filled,
    )

    object Empty : MainRoute(
        route = Route.EMPTY_BOTTOM_BAR.value,
        title = "",
        icon = 0,
        iconFocused = 0,
    )

    object Market : MainRoute(
        route = Route.MARKET_BOTTOM_BAR.value,
        title = "Market",
        icon = R.drawable.ic_market,
        iconFocused = R.drawable.ic_market_filled,
    )

    object Menu : MainRoute(
        route = Route.MENU_BOTTOM_BAR.value,
        title = "Menu",
        icon = R.drawable.ic_menu,
        iconFocused = R.drawable.ic_menu_filled,
    )
}