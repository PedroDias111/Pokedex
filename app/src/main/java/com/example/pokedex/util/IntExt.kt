package com.example.pokedex.util

import android.graphics.Color

fun Int?.orDefaultColor() = this ?: Color.rgb(255f,255f,255f)

