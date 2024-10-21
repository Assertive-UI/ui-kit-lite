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

package com.assertiveui.kit.lite.core.components.layout.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.assertiveui.kit.lite.core.theme.Theme
import com.assertiveui.kit.lite.core.theme.color.palette.ColorPaletteToken
import com.assertiveui.kit.lite.core.theme.color.palette.LocalContentColor
import com.assertiveui.kit.lite.core.theme.color.palette.colorFromToken
import com.assertiveui.kit.lite.core.theme.color.palette.contentColorFor
import com.assertiveui.kit.lite.core.utils.window.LocalWindowState

/**
 *
 * The foundation for implementing an organized UI hierarchy.
 *
 * It provides the necessary functionality to put together several UI components
 * into an organized UI hierarchy, by making an assertion
 * based on the provided local context and other factors.
 *
 * @param modifier An optional [Modifier] to be applied to the layout.
 * @param topBar A top bar component, typically a TopBar.
 * @param bottomBar A bottom bar component, typically a NavigationBar.
 * @param sideRail A side rail component, typically a SideRail.
 * @param snackbarHost A component to host snackbars.
 * @param containerColor A color used for the background of the layout. Use [Color.Transparent]
 * to have no color.
 * @param contentColor A preferred color for the content inside the layout. Defaults to either the
 * matching content color for [containerColor], or to the current [LocalContentColor] if
 * [containerColor] is not a color from the theme.
 * @param contentWindowInsets A window insets to be passed to [content] slot via [PaddingValues]
 * params. The layout will take the insets into account from the top/bottom/start only if the [topBar]/
 * [bottomBar]/[sideRail] are not present, as the layout expect [topBar]/[bottomBar]/[sideRail]
 * to handle insets instead.
 * @param content The main content of the screen, e.g. a [LazyColumn]. The lambda receives a [PaddingValues] that should be
 * applied to the content root via [Modifier.padding] and [Modifier.consumeWindowInsets] to
 * properly offset top / bottom bars and side rail. If using [Modifier.verticalScroll], apply this modifier to
 * the child of the scrollable component, and not on the scrollable itself.
 *
 */
@Composable
fun FoundationLayout(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    sideRail: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    containerColor: Color = Theme.colorPalette.colorFromToken(ColorPaletteToken.SurfaceLow),
    contentColor: Color = Theme.colorPalette.contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = WindowInsets.systemBars,
    content: @Composable (safePadding: PaddingValues) -> Unit
) = CompositionLocalProvider(LocalContentColor provides contentColor) {

    val isCompactWidth = LocalWindowState.current.isCompactWidth
    val horizontalPadding = LocalWindowState.current.horizontalPadding

    FoundationLayoutImpl(
        modifier = modifier,
        horizontalPadding = horizontalPadding,
        containerColor = containerColor,
        topBar = topBar,
        bottomBar = if (isCompactWidth) bottomBar else remember { {} },
        sideRail = if (!isCompactWidth) sideRail else remember { {} },
        snackbar = snackbarHost,
        content = content,
        contentWindowInsets = contentWindowInsets,
    )

}

@Composable
private fun FoundationLayoutImpl(
    modifier: Modifier,
    horizontalPadding: Dp,
    containerColor: Color,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    sideRail: @Composable () -> Unit,
    snackbar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    contentWindowInsets: WindowInsets
) = SubcomposeLayout(
    modifier = Modifier
        .background(containerColor)
        .then(modifier)
) { constraints ->

    val layoutWidth = constraints.maxWidth
    val layoutHeight = constraints.maxHeight
    val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

    layout(
        width = layoutWidth,
        height = layoutHeight
    ) {

        // Defining side rail placeables.
        val sideRailPlaceables = subcompose(
            slotId = ResponsiveScaffoldLayoutContent.SideRail,
            content = sideRail
        ).map { it.measure(looseConstraints) }

        val sideRailWidth = sideRailPlaceables.maxByOrNull { it.width }?.width
        val sideRailHeight = sideRailPlaceables.maxByOrNull { it.height }?.height

        // Defining top bar placeables & height.

        val topBarPlaceables = subcompose(
            slotId = ResponsiveScaffoldLayoutContent.TopBar,
            content = topBar
        ).map {

            val leftInset = if (
                layoutDirection == LayoutDirection.Ltr
                && sideRailWidth != null
            ) {
                (sideRailWidth +
                        (horizontalPadding.roundToPx() - sideRailWidth)
                            .coerceAtLeast(0))
            } else {
                horizontalPadding.roundToPx()
            }

            val rightInset = if (
                layoutDirection == LayoutDirection.Rtl
                && sideRailWidth != null
            ) {
                (sideRailWidth +
                        (horizontalPadding.roundToPx() - sideRailWidth)
                            .coerceAtLeast(0))
            } else {
                horizontalPadding.roundToPx()
            }

            it.measure(
                constraints = looseConstraints.offset(
                    horizontal = (-leftInset) - rightInset,
                )
            )

        }

        val topBarHeight = topBarPlaceables.maxByOrNull { it.height }?.height

        // Defining snackbar placeables, width & height.

        val snackbarPlaceables = subcompose(
            slotId = ResponsiveScaffoldLayoutContent.Snackbar,
            content = snackbar
        ).map {

            val leftInset = sideRailWidth ?: contentWindowInsets.getLeft(
                density = this@SubcomposeLayout,
                layoutDirection = layoutDirection
            )

            val rightInset = contentWindowInsets.getRight(
                density = this@SubcomposeLayout,
                layoutDirection = layoutDirection
            )

            val bottomInset = contentWindowInsets.getBottom(
                density = this@SubcomposeLayout
            )

            it.measure(
                constraints = looseConstraints.offset(
                    horizontal = (-leftInset) - rightInset,
                    vertical = (-bottomInset)
                )
            )

        }

        val snackbarHeight = snackbarPlaceables.maxByOrNull { it.height }?.height ?: 0
        val snackbarWidth = (snackbarPlaceables.maxByOrNull { it.width }?.width ?: 0) -
                ((sideRailWidth?.plus(horizontalPadding.roundToPx() * 2))
                    ?: (horizontalPadding.roundToPx() * 2))

        // Defining bottom bar placeables & height.

        val bottomBarPlaceables = subcompose(
            slotId = ResponsiveScaffoldLayoutContent.BottomBar,
            content = bottomBar
        ).map { it.measure(looseConstraints) }

        val bottomBarHeight = bottomBarPlaceables.maxByOrNull { it.height }?.height

        // Snackbar bottom offset.

        val snackbarOffsetFromBottom = if (snackbarHeight != 0) {
            snackbarHeight +
                    (bottomBarHeight
                        ?: contentWindowInsets.getBottom(this@SubcomposeLayout))
        } else 0

        // Defining body content placeables.

        val bodyContentPlaceables = subcompose(ResponsiveScaffoldLayoutContent.MainContent) {

            val insets = contentWindowInsets.asPaddingValues(this@SubcomposeLayout)
            val innerPadding = PaddingValues(
                top = if (topBarPlaceables.isEmpty()) {
                    insets.calculateTopPadding().plus(24.dp)
                } else {
                    topBarHeight?.toDp()?.plus(24.dp) ?: 24.dp
                },
                bottom = if (bottomBarPlaceables.isEmpty()) {
                    insets.calculateBottomPadding().plus(24.dp)
                } else {
                    bottomBarHeight?.toDp()?.plus(24.dp) ?: 24.dp
                },
                start = if (sideRailPlaceables.isEmpty() || sideRailWidth == null) {
                    insets.calculateStartPadding(
                        (this@SubcomposeLayout).layoutDirection
                    ).plus(horizontalPadding).plus(16.dp)
                } else {

                    (sideRailWidth +
                            (horizontalPadding.roundToPx() - sideRailWidth)
                                .coerceAtLeast(0)).toDp() + 16.dp
                },
                end = insets.calculateEndPadding((this@SubcomposeLayout).layoutDirection)
                    .plus(horizontalPadding).plus(16.dp)
            )

            BoxWithConstraints(
                modifier = Modifier
                    .graphicsLayer { alpha = .99f }
                    .drawWithContent {

                        drawContent()

                        if (topBarHeight != null) {

                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0f),
                                        Color.Black.copy(alpha = .1f),
                                        Color.Black.copy(alpha = .1f),
                                        Color.Black.copy(alpha = .1f),
                                        Color.Black
                                    ),
                                    startY = 0.dp.toPx(),
                                    endY = topBarHeight.toFloat() + 24.dp.toPx()
                                ),
                                blendMode = BlendMode.DstIn
                            )

                        }

                        if (bottomBarHeight != null) {

                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black,
                                        Color.Black.copy(alpha = .1f),
                                        Color.Black.copy(alpha = .1f),
                                        Color.Black.copy(alpha = .1f),
                                        Color.Black.copy(alpha = 0f)
                                    ),
                                    startY = this.size.height - (bottomBarHeight + 24.dp.toPx()),
                                    endY = this.size.height - 0.dp.toPx()
                                ),
                                blendMode = BlendMode.DstIn
                            )

                        }

                    },
                content = { content(innerPadding) }
            )

        }.map { it.measure(looseConstraints) }

        /*
         * Defining each content placeables position offset.
         */

        // Defining the top bar placeables position offset.
        val topBarPlaceablesOffset = IntOffset(
            x = if (layoutDirection == LayoutDirection.Ltr) {
                if (sideRailWidth != null) {
                    sideRailWidth +
                            (horizontalPadding.roundToPx() - sideRailWidth)
                                .coerceAtLeast(0)
                } else horizontalPadding.roundToPx()
            } else {
                horizontalPadding.roundToPx()
            },
            y = 0
        )

        // Defining the snackbar placeables position offset.
        val snackbarPlaceablesPositionOffset = IntOffset(
            x = ((layoutWidth - horizontalPadding.roundToPx()) - snackbarWidth) / 2 +
                    contentWindowInsets.getLeft(
                        density = this@SubcomposeLayout,
                        layoutDirection = layoutDirection
                    ),
            y = layoutHeight - snackbarOffsetFromBottom
        )

        // Defining the bottom bar placeables position offset.
        val bottomBarPlaceablesPositionOffset = IntOffset(
            x = horizontalPadding.roundToPx(),
            y = layoutHeight - (bottomBarHeight ?: 0)
        )

        // Defining the side rail placeables position offset.
        val sideRailPlaceablesPositionOffset = IntOffset(
            x = if (layoutDirection == LayoutDirection.Ltr) 0
            else layoutWidth - (sideRailWidth ?: 0),
            y = (layoutHeight / 2) - ((sideRailHeight?.div(2)) ?: 0)
        )

        /*
         * Placing the placeables by maintaining the layout hierarchy:
         * MainContent >> TopBar >> Snackbar >> BottomBar >> SideRail
         */

        // Placing the body content placeables.
        bodyContentPlaceables.forEach { it.place(position = IntOffset.Zero) }

        // Placing the top bar placeables.
        topBarPlaceables.forEach { it.place(position = topBarPlaceablesOffset) }

        // Placing the snackbar placeables.
        snackbarPlaceables.forEach { it.place(position = snackbarPlaceablesPositionOffset) }

        // Placing the bottom bar placeables.
        bottomBarPlaceables.forEach { it.place(position = bottomBarPlaceablesPositionOffset) }

        // Placing the side rail placeables.
        sideRailPlaceables.forEach { it.place(position = sideRailPlaceablesPositionOffset) }

    }

}

/** Constants defining [FoundationLayout]'s content hierarchy slots. */
private enum class ResponsiveScaffoldLayoutContent {
    MainContent, TopBar, Snackbar, BottomBar, SideRail;
}