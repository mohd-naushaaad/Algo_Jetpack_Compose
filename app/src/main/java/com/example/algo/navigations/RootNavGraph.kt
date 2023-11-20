package com.example.algo.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(
    modifier: Modifier,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Graph.MAIN,
        route = Graph.ROOT
    ) {

        // main graph
        mainNavGraph(modifier, navController)
    }
}

