package com.jogaai.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

actual val valEnterTransition: EnterTransition = fadeIn(animationSpec = tween(200))
actual val navExitTransition: ExitTransition = fadeOut(animationSpec = tween(200))