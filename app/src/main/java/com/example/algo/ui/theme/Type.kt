package com.example.algo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.algo.R

val roboto = FontFamily(
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 34.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    displayMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    displaySmall = TextStyle(
        fontFamily = roboto,
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    headlineLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineSmall = TextStyle(
        fontFamily = roboto,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    titleMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    titleSmall = TextStyle(
        fontFamily = roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodySmall = TextStyle(
        fontFamily = roboto,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium
    ),
    labelLarge = TextStyle(
        fontFamily = roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelMedium = TextStyle(
        fontFamily = roboto,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
    )
)