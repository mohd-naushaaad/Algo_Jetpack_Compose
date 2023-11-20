package com.example.algo.navigations

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.algo.screens.AlgoDetailScreen
import com.example.algo.screens.AlgoScreen
import com.example.algo.screens.HomeScreen
import com.example.algo.screens.MarketScreen
import com.example.algo.screens.MenuScreen
import com.example.algo.screens.WalletScreen

fun NavGraphBuilder.mainNavGraph(
    modifier: Modifier,
    navController: NavHostController,
) {
    navigation(
        startDestination = MainRoute.Home.route, route = Graph.MAIN
    ) {

        composable(route = MainRoute.Home.route) {
            HomeScreen(modifier = modifier, onWalletScreen = {
                navController.navigate(Route.WALLET.value)
            })
        }
        composable(route = MainRoute.Algo.route) {
            AlgoScreen(modifier = modifier, onAlgoDetailScreen = {
                navController.navigate(Route.ALGO_DETAIL.value)
            })
        }
        composable(route = MainRoute.Market.route) {
            MarketScreen(modifier = modifier)
        }
        composable(route = MainRoute.Menu.route) {
            MenuScreen(modifier = modifier)
        }
        composable(route = Route.ALGO_DETAIL.value) {
            AlgoDetailScreen(modifier = modifier, onBackScreen = {
                navController.navigateUp()
            })
        }
        composable(route = Route.WALLET.value) {
            WalletScreen(modifier = modifier, onBackScreen = {
                navController.navigateUp()
            })
        }
    }

}