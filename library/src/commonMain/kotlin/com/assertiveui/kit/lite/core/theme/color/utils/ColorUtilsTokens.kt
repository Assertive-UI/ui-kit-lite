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

package com.assertiveui.kit.lite.core.theme.color.utils

/**
 * A collection of default token values used across [ColorUtils].
 */
internal sealed interface ColorUtilsTokens {

    /**
     * Default token values used for color palette generation.
     */
    data object ColorPaletteGeneration : ColorUtilsTokens {

        const val ACCENT_HUE_OFFSET = 67

        /**
         * The color tones indices of a corresponding Light Color Palette color.
         */
        object LightColorsExtraction {
            const val BASE = 10
            const val ON_BASE = 28
            const val ACCENT = 10
            const val ON_ACCENT = 28
            const val SURFACE_LOW = 26
            const val ON_SURFACE_LOW = 5
            const val SURFACE_MEDIUM = 27
            const val ON_SURFACE_MEDIUM = 4
            const val SURFACE_HIGH = 28
            const val ON_SURFACE_HIGH = 3
            const val ERROR = 10
            const val ON_ERROR = 28
            const val OUTLINE = 24
        }

        /**
         * The color tones indices of a corresponding Dark Color Palette color.
         */
        object DarkColorsExtraction {
            const val BASE = 20
            const val ON_BASE = 3
            const val ACCENT = 20
            const val ON_ACCENT = 3
            const val SURFACE_LOW = 4
            const val ON_SURFACE_LOW = 26
            const val SURFACE_MEDIUM = 5
            const val ON_SURFACE_MEDIUM = 27
            const val SURFACE_HIGH = 6
            const val ON_SURFACE_HIGH = 28
            const val ERROR = 20
            const val ON_ERROR = 3
            const val OUTLINE = 6
        }

    }

    /**
     * Default token values used for color tones generation.
     */
    data object ColorToneGeneration : ColorUtilsTokens {
        const val TONES_COUNT = 30
        const val TONES_LIGHTNESS_START = 0f
        const val TONES_LIGHTNESS_MID = 50f
        const val TONES_LIGHTNESS_END = 100f
        const val TONES_CHROMA_VIBRANT = 75f
        const val TONES_CHROMA_MUTED = 15f
        const val TONES_CHROMA_ZERO = 0f
        const val TONES_HUE_OFFSET = 30
        const val TONES_ALPHA = 1f
    }

}