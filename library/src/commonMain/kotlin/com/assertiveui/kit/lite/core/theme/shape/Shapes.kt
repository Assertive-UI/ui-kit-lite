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

package com.assertiveui.kit.lite.core.theme.shape

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

/**
 *
 * A collection of [Shapes] that can be applied to the application theme.
 *
 * @param extraShape A large shape for UI components such as bars and buttons.
 * @param largeShape A large shape for UI components with the highest priority.
 * @param mediumShape A medium shape for UI components with the moderate priority.
 * @param smallShape A medium shape for UI components with the lowest priority.
 *
 */
@Immutable
data class Shapes(
    val extraShape: Shape = DefaultShapeTokens.extraShape,
    val largeShape: Shape = DefaultShapeTokens.largeShape,
    val mediumShape: Shape = DefaultShapeTokens.mediumShape,
    val smallShape: Shape = DefaultShapeTokens.smallShape
) {

    companion object {

        /**
         * Returns the default [Shapes].
         */
        val Default get() = Shapes()

    }

}

/**
 * A CompositionLocal key used for providing [Shapes] to the UI.
 */
val LocalShapes = staticCompositionLocalOf { Shapes.Default }