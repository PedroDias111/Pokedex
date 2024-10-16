package com.example.pokedex.presentation.detailspresentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.domain.model.StatDomain

@Composable
fun StatsScreen(
    stats: List<StatDomain>, // List of stats with values for the Pokemon
    modifier: Modifier = Modifier
) {

    ElevatedCard (
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        if (stats.isNotEmpty()) {
            // Find the maximum stat value to normalize the bars
            val maxStatValue = stats.maxOf { stat -> stat.baseStat }
            val totalStatValue = stats.sumOf { stat -> stat.baseStat }

            Column(modifier = Modifier.padding(16.dp)) {
                stats.forEach { stat ->
                    stat.name?.let {
                        StatBar(
                            statName = it,
                            statValue = stat.baseStat,
                            maxStatValue = maxStatValue // Max stat value for normalization
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp)) // Spacing between the bars
                }
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Total: ${totalStatValue}",
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp)
                 )
                }
            }



            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun StatBar(
    statName: String,
    statValue: Int,
    maxStatValue: Int,
    modifier: Modifier = Modifier
) {

    val capitalizedStatName = statName.capitalizeWords()

    // Prevent division by zero if maxStatValue is zero
    val normalizedStatValue = if (maxStatValue > 0) {
        statValue.toFloat() / maxStatValue
    } else {
        0f
    }

    // Start progress from 0 and animate to normalized value
    var progressStart by remember { mutableStateOf(0f) }

    // This will trigger the animation when the component is first composed or recomposed
    LaunchedEffect(key1 = statValue) {
        progressStart = normalizedStatValue
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progressStart, // Target progress value
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000)
    )

    Column {
        Text(
            text = "$capitalizedStatName: $statValue",
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = Color.Gray,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        LinearProgressIndicator(
            progress = animatedProgress, // Use the animated progress
            modifier = modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.Green // Customize your bar color
        )
    }
}

fun String.capitalizeWords(): String {
    return this.split("-") // Split the string by hyphen
        .joinToString(" ") { word -> // Join them with space and capitalize each
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}