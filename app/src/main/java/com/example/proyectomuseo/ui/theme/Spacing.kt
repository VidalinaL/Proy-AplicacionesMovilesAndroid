package com.example.proyectomuseo.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacing(

    // Espaciado general
    val none: Dp = 0.dp,
    val micro: Dp = 2.dp,
    val tiny: Dp = 3.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val regular: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 48.dp
)
data class AppComponentSizes(

    // Logos
    val logoSizeLarge: Dp = 150.dp,
    val logoSizeMedium: Dp = 100.dp,

    // Iconos
    val iconTiny: Dp = 10.dp,
    val iconSmall: Dp = 16.dp,
    val iconRegular: Dp = 20.dp,
    val iconMedium: Dp = 24.dp,
    val iconLarge: Dp = 26.dp,

    // Contenedores de iconos
    val iconContainerMedium: Dp = 48.dp,
    val iconContainerLarge: Dp = 64.dp,

    // Cards
    val dashboardCardHeight: Dp = 175.dp,
    val cardCornerRadius: Dp = 24.dp,
    val cardElevation: Dp = 4.dp,

    // Hero / banner
    val heroBannerHeight: Dp = 320.dp,
    val heroCornerRadius: Dp = 12.dp,

    // Panel inferior
    val panelCornerRadius: Dp = 32.dp,

    // Indicadores
    val indicatorWidth: Dp = 36.dp,
    val indicatorHeight: Dp = 4.dp,
    val indicatorHeightThin: Dp = 3.5.dp,

    // Línea decorativa
    val quoteLineWidth: Dp = 42.dp,
    val quoteLineHeight: Dp = 1.5.dp,

    // Proporciones
    val heroImageHeightFraction: Float = 0.75f,
    val heroTextWidthFraction: Float = 0.55f,

    // Gradiente hero
    val heroGradientStartX: Float = 0f,
    val heroGradientEndX: Float = 750f
)

val LocalAppSpacing = staticCompositionLocalOf { AppSpacing() }
val LocalAppComponentSizes = staticCompositionLocalOf { AppComponentSizes() }
