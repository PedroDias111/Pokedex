package com.example.pokedex.presentation.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import java.util.Locale

fun Int?.orDefaultColor() = this ?: android.graphics.Color.rgb(255f,255f,255f)

fun Color.lighter(factor: Float = 0.2f): Color {
    val r = red + factor * (1 - red)
    val g = green + factor * (1 - green)
    val b = blue + factor * (1 - blue)
    return Color(r.coerceIn(0f, 1f), g.coerceIn(0f, 1f), b.coerceIn(0f, 1f), alpha)
}

fun Color.darker(factor: Float = 0.2f): Color {
    val r = red * (1 - factor)
    val g = green * (1 - factor)
    val b = blue * (1 - factor)
    return Color(r.coerceIn(0f, 1f), g.coerceIn(0f, 1f), b.coerceIn(0f, 1f), alpha)
}


fun getTypeColor(typeName: String): Color {
    return when (typeName.lowercase(Locale.getDefault())) {
        "grass" -> Color(0xFF78C850)
        "fire" -> Color(0xFFF08030)
        "water" -> Color(0xFF6890F0)
        "electric" -> Color(0xFFF8D030)
        "ice" -> Color(0xFF98D8D8)
        "fighting" -> Color(0xFFC03028)
        "poison" -> Color(0xFFA040A0)
        "ground" -> Color(0xFFE0C068)
        "flying" -> Color(0xFFA890F0)
        "psychic" -> Color(0xFFF85888)
        "bug" -> Color(0xFFA8B820)
        "rock" -> Color(0xFFB8A038)
        "ghost" -> Color(0xFF705898)
        "dragon" -> Color(0xFF7038F8)
        "dark" -> Color(0xFF705848)
        "steel" -> Color(0xFFB8B8D0)
        "fairy" -> Color(0xFFEE99AC)
        "normal" -> Color(0xFFA8A878)
        else -> Color.Gray // Default color for unknown types
    }
}

// Custom Shape for Circle on the Left and Square on the Right
class CircleCutSquareShape : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            // Create a circle on the left side
            addArc(
                oval = Rect(0f, 0f, size.height, size.height), // Circle with the height of the image
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f
            )
            // Draw the rest as a rectangle
            lineTo(size.width, 0f) // Top-right corner
            lineTo(size.width, size.height) // Bottom-right corner
            lineTo(size.height / 2, size.height) // Bottom-left arc start
            close() // Close the path
        }
        return Outline.Generic(path)
    }
}

fun getContrastingTextColor(color: Color): Color {
    return if (color.luminance() < 0.15) Color.White else color.darker(0.4f)
}