package com.example.proyectomuseo.ui.theme

import androidx.compose.material3.Typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyectomuseo.R
val playfairFamily = FontFamily(
    Font(R.font.playfair_display_regular, FontWeight.Normal),
    Font(R.font.playfair_display_medium, FontWeight.Medium),
    Font(R.font.playfair_display_bold, FontWeight.Bold),
    Font(R.font.playfair_display_extrabold, FontWeight.ExtraBold),
    Font(R.font.playfair_display_black, FontWeight.Black)
)

val Typography = Typography(

    // -------------------------------------------------
    // Títulos de gran tamaño / Identidad de marca
    // -------------------------------------------------

    displayLarge = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Normal, // O FontWeight.Normal
        fontSize = 72.sp,
        lineHeight = 84.sp,
        letterSpacing = (-2).sp
    ),


    // -------------------------------------------------
    // Encabezados de secciones principales
    // -------------------------------------------------

    headlineLarge = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Black,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = (-1).sp
    ),

    headlineMedium = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp,
        lineHeight = 38.sp,
        letterSpacing = (-1).sp
    ),

    headlineSmall = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),


    // -------------------------------------------------
    // Subtítulos y títulos de tarjetas
    // -------------------------------------------------

    titleLarge = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Black,
        fontSize = 26.sp,
        lineHeight = 30.sp,
        letterSpacing = 1.sp
    ),

    titleMedium = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    titleSmall = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    )
)