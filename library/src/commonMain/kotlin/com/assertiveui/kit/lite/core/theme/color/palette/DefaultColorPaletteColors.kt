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

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * A function that returns a default dark [ColorPalette], with an optional overriding.
 */
@Stable
fun lightColorPalette(
    baseHue: Int = DefaultColorPaletteColors.Light.baseHue,
    base: Color = DefaultColorPaletteColors.Light.base,
    onBase: Color = DefaultColorPaletteColors.Light.onBase,
    accentHue: Int = DefaultColorPaletteColors.Light.accentHue,
    accent: Color = DefaultColorPaletteColors.Light.accent,
    onAccent: Color = DefaultColorPaletteColors.Light.onAccent,
    surfaceLow: Color = DefaultColorPaletteColors.Light.surfaceLow,
    onSurfaceLow: Color = DefaultColorPaletteColors.Light.onSurfaceLow,
    surfaceMedium: Color = DefaultColorPaletteColors.Light.surfaceMedium,
    onSurfaceMedium: Color = DefaultColorPaletteColors.Light.onSurfaceMedium,
    surfaceHigh: Color = DefaultColorPaletteColors.Light.surfaceHigh,
    onSurfaceHigh: Color = DefaultColorPaletteColors.Light.onSurfaceHigh,
    error: Color = DefaultColorPaletteColors.Light.error,
    onError: Color = DefaultColorPaletteColors.Light.onError,
    outline: Color = DefaultColorPaletteColors.Light.outline
) = ColorPalette(
    baseHue = baseHue,
    base = base,
    onBase = onBase,
    accentHue = accentHue,
    accent = accent,
    onAccent = onAccent,
    surfaceLow = surfaceLow,
    onSurfaceLow = onSurfaceLow,
    surfaceMedium = surfaceMedium,
    onSurfaceMedium = onSurfaceMedium,
    surfaceHigh = surfaceHigh,
    onSurfaceHigh = onSurfaceHigh,
    error = error,
    onError = onError,
    outline = outline
)

/**
 * A function that returns a default dark [ColorPalette], with an optional overriding.
 */
@Stable
fun darkColorPalette(
    baseHue: Int = DefaultColorPaletteColors.Dark.baseHue,
    base: Color = DefaultColorPaletteColors.Dark.base,
    onBase: Color = DefaultColorPaletteColors.Dark.onBase,
    accentHue: Int = DefaultColorPaletteColors.Dark.accentHue,
    accent: Color = DefaultColorPaletteColors.Dark.accent,
    onAccent: Color = DefaultColorPaletteColors.Dark.onAccent,
    surfaceLow: Color = DefaultColorPaletteColors.Dark.surfaceLow,
    onSurfaceLow: Color = DefaultColorPaletteColors.Dark.onSurfaceLow,
    surfaceMedium: Color = DefaultColorPaletteColors.Dark.surfaceMedium,
    onSurfaceMedium: Color = DefaultColorPaletteColors.Dark.onSurfaceMedium,
    surfaceHigh: Color = DefaultColorPaletteColors.Dark.surfaceHigh,
    onSurfaceHigh: Color = DefaultColorPaletteColors.Dark.onSurfaceHigh,
    error: Color = DefaultColorPaletteColors.Dark.error,
    onError: Color = DefaultColorPaletteColors.Dark.onError,
    outline: Color = DefaultColorPaletteColors.Dark.outline
) = ColorPalette(
    baseHue = baseHue,
    base = base,
    onBase = onBase,
    accentHue = accentHue,
    accent = accent,
    onAccent = onAccent,
    surfaceLow = surfaceLow,
    onSurfaceLow = onSurfaceLow,
    surfaceMedium = surfaceMedium,
    onSurfaceMedium = onSurfaceMedium,
    surfaceHigh = surfaceHigh,
    onSurfaceHigh = onSurfaceHigh,
    error = error,
    onError = onError,
    outline = outline
)

@Immutable
sealed class DefaultColorPaletteColors(
    val baseHue: Int,
    val base: Color,
    val onBase: Color,
    val accentHue: Int,
    val accent: Color,
    val onAccent: Color,
    val surfaceLow: Color,
    val onSurfaceLow: Color,
    val surfaceMedium: Color,
    val onSurfaceMedium: Color,
    val surfaceHigh: Color,
    val onSurfaceHigh: Color,
    val error: Color,
    val onError: Color,
    val outline: Color
) {

    data object Light : DefaultColorPaletteColors(
        baseHue = 210,
        base = Color(0xFF004A8A),
        onBase = Color(0xFFE3fAFF),
        accentHue = 140,
        accent = Color(0xFF004F2D),
        onAccent = Color(0xFFECF8F3),
        surfaceLow = Color(0xFFDBE4E8),
        onSurfaceLow = Color(0xFF0D181D),
        surfaceMedium = Color(0xFFE7EDF0),
        onSurfaceMedium = Color(0xFF070E13),
        surfaceHigh = Color(0xFFF3F6F7),
        onSurfaceHigh = Color(0xFF030609),
        error = Color(0xFF830012),
        onError = Color(0xFFFFF2F1),
        outline = Color(0xFFCFDBE1),
    )

    data object Dark : DefaultColorPaletteColors(
        baseHue = 210,
        base = Color(0xFF00BEFF),
        onBase = Color(0xFF000817),
        accentHue = 140,
        accent = Color(0xFF00BB8D),
        onAccent = Color(0xFF000903),
        surfaceLow = Color(0xFF070E13),
        onSurfaceLow = Color(0xFFDBE4E8),
        surfaceMedium = Color(0xFF0D181D),
        onSurfaceMedium = Color(0xFFE7EDF0),
        surfaceHigh = Color(0xFF132229),
        onSurfaceHigh = Color(0xFFF3F6F7),
        error = Color(0xFFFF7672),
        onError = Color(0xFF160001),
        outline = Color(0xFF070E13)
    )

}