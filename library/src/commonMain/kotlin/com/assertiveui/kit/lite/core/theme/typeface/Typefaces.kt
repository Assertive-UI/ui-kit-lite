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

package com.assertiveui.kit.lite.core.theme.typeface

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

/**
 *
 * A collection of [TextStyle]s that can be applied to the application theme.
 *
 * @param titleLarge A [TextStyle] intended for a title that contains a short text.
 * @param titleMedium A [TextStyle] intended for a title that contains a medium text.
 * @param titleSmall A [TextStyle] intended for a title that contains a long text.
 * @param actionLarge A [TextStyle] intended for an action that contains a short text.
 * @param actionMedium A [TextStyle] intended for an action that contains a medium text.
 * @param actionSmall A [TextStyle] intended for an action that contains a long text.
 * @param bodyLarge A [TextStyle] intended for a short body text.
 * @param bodyMedium A [TextStyle] intended for a medium body text.
 * @param bodySmall A [TextStyle] intended for a long body text.
 *
 */
@Immutable
data class Typefaces(
    val titleLarge: TextStyle = DefaultTypefaceTokens.titleLarge,
    val titleMedium: TextStyle = DefaultTypefaceTokens.titleMedium,
    val titleSmall: TextStyle = DefaultTypefaceTokens.titleSmall,
    val actionLarge: TextStyle = DefaultTypefaceTokens.actionLarge,
    val actionMedium: TextStyle = DefaultTypefaceTokens.actionMedium,
    val actionSmall: TextStyle = DefaultTypefaceTokens.actionSmall,
    val bodyLarge: TextStyle = DefaultTypefaceTokens.bodyLarge,
    val bodyMedium: TextStyle = DefaultTypefaceTokens.bodyMedium,
    val bodySmall: TextStyle = DefaultTypefaceTokens.bodySmall,
) {

    companion object {

        /**
         * Returns the default [Typefaces].
         */
        val Default get() = Typefaces()

    }

}

/**
 * A CompositionLocal key used for providing [Typefaces] to the UI.
 */
val LocalTypefaces = staticCompositionLocalOf { Typefaces.Default }

/**
 * A CompositionLocal key used for providing a default [TextStyle] to the UI.
 */
val LocalTextStyle = compositionLocalOf { DefaultTypefaceTokens.bodyMedium }