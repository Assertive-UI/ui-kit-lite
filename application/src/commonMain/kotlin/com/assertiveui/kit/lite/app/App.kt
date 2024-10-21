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

package com.assertiveui.kit.lite.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.assertiveui.kit.lite.app.core.ui.AppUIWrapper
import com.assertiveui.kit.lite.core.components.layout.foundation.FoundationLayout
import com.assertiveui.kit.lite.core.theme.Theme
import com.assertiveui.kit.lite.core.theme.color.palette.ColorPaletteToken
import com.assertiveui.kit.lite.core.theme.color.palette.colorFromToken
import com.assertiveui.kit.lite.core.utils.window.LocalWindowState

@Composable
fun App() {

    AppUIWrapper {

        FoundationLayout(
            topBar = {

                Box(
                    modifier = Modifier
                        .padding(
                            WindowInsets.systemBars.exclude(
                                WindowInsets.systemBars.only(
                                    WindowInsetsSides.Bottom
                                        .plus(WindowInsetsSides.Start)
                                )
                            ).asPaddingValues()
                        )
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(Theme.shapes.extraShape)
                        .background(Theme.colorPalette.surfaceHigh),
                    contentAlignment = Alignment.CenterStart
                ) {

                    val width by rememberUpdatedState(
                        LocalWindowState.current.availableWidthDp
                    )

                    BasicText(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        text = "Window width:  $width",
                        style = Theme.typefaces.actionLarge.copy(
                            color = Theme.colorPalette.colorFromToken(
                                token = ColorPaletteToken.OnSurfaceHigh
                            )
                        )
                    )

                }

            },
            bottomBar = {

                Box(
                    modifier = Modifier
                        .padding(
                            WindowInsets.systemBars.exclude(
                                WindowInsets.systemBars.only(
                                    WindowInsetsSides.Top
                                )
                            ).asPaddingValues()
                        )
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(Theme.shapes.extraShape)
                        .background(Theme.colorPalette.surfaceHigh)
                )

            },
            sideRail = {

                Box(
                    modifier = Modifier
                        .padding(WindowInsets.displayCutout.asPaddingValues())
                        .padding(vertical = 64.dp)
                        .padding(start = 16.dp)
                        .width(64.dp)
                        .height(360.dp)
                        .fillMaxHeight(.67f)
                        .clip(Theme.shapes.extraShape)
                        .background(Theme.colorPalette.surfaceHigh)
                )

            }
        ) { safePadding ->

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = safePadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Adaptive(200.dp)
            ) {

                items(count = 512) { i ->

                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Theme.colorPalette.surfaceMedium)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        text = "Item: ${i + 1}",
                        style = Theme.typefaces.bodyLarge.copy(
                            color = Theme.colorPalette.colorFromToken(
                                token = ColorPaletteToken.OnSurfaceLow
                            )
                        )
                    )

                }

            }

        }

    }

}