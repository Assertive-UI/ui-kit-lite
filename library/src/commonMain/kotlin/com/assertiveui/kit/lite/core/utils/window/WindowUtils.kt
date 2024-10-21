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

package com.assertiveui.kit.lite.core.utils.window

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import com.assertiveui.kit.lite.core.utils.etc.transformFraction
import com.assertiveui.kit.lite.core.utils.window.ScreenOrientation.Companion.isPortrait

/**
 *
 * A [WindowState] empowers the ability to implement adaptive UIs in your app,
 * simply by evaluating the width and the height of a root composable layout.
 *
 * This is a Top-Level function and it should be called at the root UI composable.
 *
 * ```
 * BoxWithConstraints {
 *
 *     val windowState by rememberWindowState(
 *         rootSize = DpSize(
 *             width = this@BoxWithConstraints.maxWidth,
 *             height = this@BoxWithConstraints.maxHeight
 *         )
 *     )
 *
 *     CompositionLocalProvider(
 *         LocalWindowState provides windowState
 *     ) {
 *
 *         // UI Content ...
 *
 *     }
 *
 * }
 * ```
 *
 * @param rootSize The [DpSize] of the root composable layout (e.g. [BoxWithConstraints]).
 *
 */
@Stable
@Composable
fun rememberWindowState(rootSize: DpSize): State<WindowState> {
    return rememberUpdatedState(
        WindowState(
            availableWidthSize = calculateWindowSize(rootSize.width),
            availableHeightSize = calculateWindowSize(rootSize.height),
            availableWidthDp = rootSize.width,
            availableHeightDp = rootSize.height
        )
    )
}

/**
 * Calculates a [WindowSize] from the provided [Dp] size.
 * @param size The provided [Dp] size.
 */
private fun calculateWindowSize(size: Dp): WindowSize {
    return when {
        size < WindowUtilsTokens.WindowSize.MaxCompactWidth -> WindowSize.Compact
        size < WindowUtilsTokens.WindowSize.MaxMediumWidth -> WindowSize.Medium
        else -> WindowSize.Large
    }
}

/**
 * A definition of the available width / height area size for a window.
 */
enum class WindowSize {

    /**
     * A constant that represents a small width / height area size.
     */
    Compact,

    /**
     * A constant that represents a medium width / height area size.
     */
    Medium,

    /**
     * A constant that represents a large width / height area size.
     */
    Large;

}

/**
 *
 * A definition of the form factor for a window, used for implementing
 * responsive UIs in the app.
 *
 * ```
 * @Composable
 * fun AppContent() = when (LocalWindowState.current.formFactor) {
 *
 *     // Phone in portrait orientation.
 *     WindowFormFactor.PhoneInPortrait -> {}
 *
 *     // Phone in landscape orientation.
 *     WindowFormFactor.PhoneInLandscape -> {}
 *
 *     // Foldable / Small Tablet in portrait orientation.
 *     WindowFormFactor.FoldableTabletPortrait -> {}
 *
 *     // Foldable / Small Tablet in landscape orientation.
 *     WindowFormFactor.FoldableTabletLandscape -> {}
 *
 *     // Desktop / Large Tablet in portrait orientation.
 *     WindowFormFactor.DesktopPortrait -> {}
 *
 *     // Desktop / Large Tablet in landscape orientation.
 *     WindowFormFactor.DesktopLandscape -> {}
 *
 *     else -> {}
 *
 * }
 * ```
 *
 */
enum class WindowFormFactor {

    /**
     * A constant that represents a small window in portrait screen orientation (possibly a phone).
     */
    PhoneInPortrait,

    /**
     * A constant that represents a small window in landscape screen orientation (possibly a phone).
     */
    PhoneInLandscape,

    /**
     * A constant that represents a medium-sized window in portrait screen orientation (possibly a foldable, or small tablet).
     */
    FoldableTabletPortrait,

    /**
     * A constant that represents a medium-sized window in landscape screen orientation (possibly a foldable, or small tablet).
     */
    FoldableTabletLandscape,

    /**
     * A constant that represents a large window in portrait screen orientation (possibly a large tablet, or a desktop).
     */
    DesktopPortrait,

    /**
     * A constant that represents a large window in portrait screen orientation (possibly a large tablet, or a desktop).
     */
    DesktopLandscape;

}

/**
 * A definition of the screen orientation for a window.
 */
enum class ScreenOrientation {

    /**
     * A constant that represents a portrait (width < height) screen orientation.
     */
    Portrait,

    /**
     * A constant that represents a square (width = height) screen orientation.
     */
    Square,

    /**
     * A constant that represents a landscape (width > height) screen orientation.
     */
    Landscape;

    companion object {

        /**
         * Returns true if the current screen orientation is [Portrait] or [Square],
         * false otherwise.
         */
        val ScreenOrientation.isPortrait get() = this == Portrait || this == Square

    }

}

/**
 * Contains the information about the available width & height in [Dp] and [WindowSize] values,
 * with additional functionality.
 */
@Immutable
data class WindowState(
    val availableWidthSize: WindowSize,
    val availableHeightSize: WindowSize,
    val availableWidthDp: Dp,
    val availableHeightDp: Dp
) {

    /**
     * Returns true if the available [WindowSize] width is [WindowSize.Compact], false otherwise.
     */
    val isCompactWidth get() = availableWidthSize == WindowSize.Compact

    /**
     * Returns true if the available [WindowSize] width is [WindowSize.Medium], false otherwise.
     */
    val isMediumWidth get() = availableWidthSize == WindowSize.Medium

    /**
     * Returns true if the available [WindowSize] width is [WindowSize.Large], false otherwise.
     */
    val isLargeWidth get() = availableWidthSize == WindowSize.Large

    /**
     * Returns true if the available [WindowSize] height is [WindowSize.Compact], false otherwise.
     */
    val isCompactHeight get() = availableHeightSize == WindowSize.Compact

    /**
     * Returns true if the available [WindowSize] height is [WindowSize.Medium], false otherwise.
     */
    val isMediumHeight get() = availableHeightSize == WindowSize.Medium

    /**
     * Returns true if the available [WindowSize] height is [WindowSize.Large], false otherwise.
     */
    val isLargeHeight get() = availableHeightSize == WindowSize.Large

    /**
     * The current form factor of the window.
     */
    val formFactor: WindowFormFactor
        get() = when {

            isCompactWidth -> WindowFormFactor.PhoneInPortrait

            isMediumWidth && isCompactHeight
                    || isLargeWidth && isCompactHeight -> WindowFormFactor.PhoneInLandscape

            isMediumWidth && isMediumHeight
                    && screenOrientation.isPortrait -> WindowFormFactor.FoldableTabletPortrait

            isMediumWidth && isMediumHeight
                    && !screenOrientation.isPortrait
                    || isLargeWidth && isMediumHeight -> WindowFormFactor.FoldableTabletLandscape

            isLargeWidth && isLargeHeight
                    && screenOrientation.isPortrait -> WindowFormFactor.DesktopPortrait

            isLargeWidth && isCompactHeight
                    || isLargeWidth && isMediumHeight
                    || isLargeWidth && isLargeHeight -> WindowFormFactor.DesktopLandscape

            else -> WindowFormFactor.PhoneInLandscape

        }

    /**
     * The current screen orientation.
     */
    val screenOrientation: ScreenOrientation
        get() = when {
            availableWidthDp < availableHeightDp -> ScreenOrientation.Portrait
            availableWidthDp > availableHeightDp -> ScreenOrientation.Landscape
            else -> ScreenOrientation.Square
        }

    /**
     * The horizontal content padding based on the available width.
     */
    val horizontalPadding: Dp
        get() = transformFraction(
            value = availableWidthDp.coerceIn(
                minimumValue = WindowUtilsTokens.WindowSize.MaxCompactWidth,
                maximumValue = WindowUtilsTokens.WindowSize.MaxLargeWidth
            ).value,
            startX = WindowUtilsTokens.WindowSize.MaxCompactWidth.value,
            endX = WindowUtilsTokens.WindowSize.MaxLargeWidth.value,
            startY = WindowUtilsTokens.HorizontalPadding.MinHorizontalPadding.value,
            endY = WindowUtilsTokens.HorizontalPadding.MaxHorizontalPadding.value
        ).dp

}

/**
 * A CompositionLocal key used for providing a [WindowState] to the UI.
 */
val LocalWindowState = compositionLocalOf<WindowState> {
    error("CompositionLocal LocalWindowState not provided.")
}