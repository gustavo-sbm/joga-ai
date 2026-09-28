package com.jogaai.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogaai.theme.Spacing

@Composable
fun ColumnResponsive (content: @Composable () -> Unit) {
    BoxWithConstraints {
        val padding = if (WindowSizeClass.fromWidth(maxWidth.value.toInt())== WindowSizeClass.COMPACT) Spacing.xl
                        else Spacing.xxxl

        Column (modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            content()
        }
    }
}