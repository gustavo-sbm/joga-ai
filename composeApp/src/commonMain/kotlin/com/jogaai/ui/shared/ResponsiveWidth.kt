package com.jogaai.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.responsiveWidth(maxWidth: Dp = 600.dp): Modifier {
    return this.widthIn(max = maxWidth).fillMaxWidth()
}

