package com.formation.composeformation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.formation.composeformation.R

val UrbanistFontFamily = FontFamily(
    Font(R.font.urbanist_regular, FontWeight.Normal),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_bold, FontWeight.Bold)
)

private val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = UrbanistFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = UrbanistFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = UrbanistFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = UrbanistFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = UrbanistFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = UrbanistFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = UrbanistFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = UrbanistFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = UrbanistFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = UrbanistFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = UrbanistFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = UrbanistFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = UrbanistFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = UrbanistFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = UrbanistFontFamily)
)
