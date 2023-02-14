package com.della.hassintmdbtask.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity

/**
 * The function takes an Int value as its receiver and returns a Float value representing the equivalent dp value.

The LocalDensity.current.density property is used to get the current device's density. The density value is then used to divide the input Int value, resulting in the equivalent dp value.
 */
@Composable
fun Int.toDp(): Float {
    val density = LocalDensity.current.density
    return remember(this) { this / density }
}