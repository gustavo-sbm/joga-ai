package com.jogaai.ui.shared

enum class WindowSizeClass(val minWidthDp: Int) {
    COMPACT(0),
    MEDIUM(600),
    EXPANDED(840);

    companion object {
        fun fromWidth(widthDp: Int): WindowSizeClass{
            return when{
                widthDp < MEDIUM.minWidthDp -> COMPACT
                widthDp < EXPANDED.minWidthDp -> MEDIUM
                else -> EXPANDED
            }
        }
    }
}