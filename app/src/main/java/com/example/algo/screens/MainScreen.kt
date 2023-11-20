package com.example.algo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.algo.navigations.BottomNavigationBar
import com.example.algo.navigations.RootNavGraph
import com.example.algo.ui.theme.black1
import com.example.algo.ui.theme.blackBackground

@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        containerColor = blackBackground,
        bottomBar = { BottomNavigationBar(navHostController) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        RootNavGraph(
            navController = navHostController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}