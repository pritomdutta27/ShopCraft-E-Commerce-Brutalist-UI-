package site.pritom.designsystems.utils

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.palette.graphics.Palette

/** Created by Pritom Dutta on 6/10/26 */
object ImageUtils {
    fun parseColorSwatch(color: Palette.Swatch?): Color {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color.rgb)
            return Color("#$parsedColor".toColorInt())
        } else {
            Color.White
        }
    }
}