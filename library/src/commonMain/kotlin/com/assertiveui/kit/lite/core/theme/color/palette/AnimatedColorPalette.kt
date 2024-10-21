/*
 * Copyright 2024 Assertive UI (assertiveui.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.assertiveui.kit.lite.core.theme.color.palette

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Stable
@Composable
fun ColorPalette.asAnimatedColorPalette(): ColorPalette {

    val animatedBaseHue by animateHue(this.baseHue)
    val animatedBase by animateColor(this.base)
    val animatedOnBase by animateColor(this.onBase)

    val animatedAccentHue by animateHue(this.accentHue)
    val animatedAccent by animateColor(this.accent)
    val animatedOnAccent by animateColor(this.onAccent)

    val animatedsurfaceLow by animateColor(this.surfaceLow)
    val animatedonSurfaceLow by animateColor(this.onSurfaceLow)

    val animatedsurfaceMedium by animateColor(this.surfaceMedium)
    val animatedonSurfaceMedium by animateColor(this.onSurfaceMedium)

    val animatedsurfaceHigh by animateColor(this.surfaceHigh)
    val animatedonSurfaceHigh by animateColor(this.onSurfaceHigh)

    val animatedError by animateColor(this.error)
    val animatedOnError by animateColor(this.onError)

    val animatedOutline by animateColor(this.outline)

    return ColorPalette(
        baseHue = animatedBaseHue,
        base = animatedBase,
        onBase = animatedOnBase,
        accentHue = animatedAccentHue,
        accent = animatedAccent,
        onAccent = animatedOnAccent,
        surfaceLow = animatedsurfaceLow,
        onSurfaceLow = animatedonSurfaceLow,
        surfaceMedium = animatedsurfaceMedium,
        onSurfaceMedium = animatedonSurfaceMedium,
        surfaceHigh = animatedsurfaceHigh,
        onSurfaceHigh = animatedonSurfaceHigh,
        error = animatedError,
        onError = animatedOnError,
        outline = animatedOutline
    )

}

@Stable
@Composable
private fun animateHue(hue: Int): State<Int> {
    return animateIntAsState(
        targetValue = hue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
}

@Stable
@Composable
private fun animateColor(color: Color): State<Color> {
    return animateColorAsState(
        targetValue = color,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
}