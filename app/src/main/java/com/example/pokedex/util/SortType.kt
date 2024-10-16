package com.example.myapplication.util

enum class SortBy {
    TYPE,         // Sort by type
    ALPHABETIC,   // Sort alphabetically by name
    TOTAL_STATS,  // Sort by total stats
    HP,              // Sort by specific stat
    ATTACK,          // Sort by specific stat
    DEFENSE,          // Sort by specific stat
    SPECIAL_ATTACK,          // Sort by specific stat
    SPECIAL_DEFENSE,          // Sort by specific stat
    SPEED,          // Sort by specific stat
}

enum class SortOrder {
    ASC, // Ascending
    DESC // Descending
}