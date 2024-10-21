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

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Represents a color palette containing a variety of colors, each with a specific role in Assertive UI.
 *
 * @property baseHue The base hue for the primary colors in the palette, represented as an integer value.
 * @property base The primary color in the palette, typically used for backgrounds or major UI components.
 * @property onBase The color used for text and icons displayed on the primary color background.
 * @property accentHue The hue for the accent color, represented as an integer value.
 * @property accent The accent color in the palette, used to draw attention to important UI elements.
 * @property onAccent The color used for text and icons displayed on the accent color background.
 * @property surfaceLow The color used for low surfaces, such as container backgrounds or overlays.
 * @property onSurfaceLow The color used for text and icons displayed on low surfaces.
 * @property surfaceMedium The color used for medium surfaces, such as cards or list items.
 * @property onSurfaceMedium The color used for text and icons displayed on medium surfaces.
 * @property surfaceHigh The color used for high surfaces, typically for elevated components like bars or buttons.
 * @property onSurfaceHigh The color used for text and icons displayed on high surfaces.
 * @property error The color used to indicate errors or warnings in the UI.
 * @property onError The color used for text and icons displayed on the error color background.
 * @property outline The color used for outlines or borders in the UI.
 */
@Stable
data class ColorPalette(
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
)

/**
 * Returns a [ColorPalette] color based on the provided [ColorPaletteToken].
 * @param token The provided [ColorPaletteToken].
 */
fun ColorPalette.colorFromToken(token: ColorPaletteToken) = when (token) {
    ColorPaletteToken.Base -> base
    ColorPaletteToken.OnBase -> onBase
    ColorPaletteToken.Accent -> accent
    ColorPaletteToken.OnAccent -> onAccent
    ColorPaletteToken.SurfaceLow -> surfaceLow
    ColorPaletteToken.OnSurfaceLow -> onSurfaceLow
    ColorPaletteToken.SurfaceMedium -> surfaceMedium
    ColorPaletteToken.OnSurfaceMedium -> onSurfaceMedium
    ColorPaletteToken.SurfaceHigh -> surfaceHigh
    ColorPaletteToken.OnSurfaceHigh -> onSurfaceHigh
    ColorPaletteToken.Error -> error
    ColorPaletteToken.OnError -> onError
    ColorPaletteToken.Outline -> outline
}

/**
 * Returns the content color that should be displayed on top the provided container color.
 * @param containerColor The provided container color.
 */
fun ColorPalette.contentColorFor(containerColor: Color) = when (containerColor) {
    base -> onBase
    accent -> onAccent
    surfaceLow -> onSurfaceLow
    surfaceMedium -> onSurfaceMedium
    surfaceHigh -> surfaceHigh
    error -> onError
    else -> Color.Unspecified
}

/**
 * A CompositionLocal key used for providing a [ColorPalette] to the UI.
 */
val LocalColorPalette = compositionLocalOf { lightColorPalette() }

/**
 * A CompositionLocal key used for providing a content [Color] to the UI.
 */
val LocalContentColor = compositionLocalOf { Color.Unspecified }