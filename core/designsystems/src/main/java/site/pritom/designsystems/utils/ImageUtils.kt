package site.pritom.designsystems.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColorInt
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Created by Pritom Dutta on 6/10/26 */
object ImageUtils {

    private const val MAX_COLOR_COUNT = 12
    private const val TARGET_SIZE_PX = 112
    private const val RESIZE_BITMAP_AREA = TARGET_SIZE_PX * TARGET_SIZE_PX

    // Thread-safe memory cache (storing primitive ARGB Ints)
    private val colorCache = LruCache<String, Int>(250)

    /** Synchronous check to prevent 1-frame recomposition flicker */
    @ColorInt
    fun getCachedColor(imageUrl: String): Int? = colorCache.get(imageUrl)


    suspend fun getDominantColor(
        context: Context,
        imageUrl: String,
        @ColorInt defaultColor: Int = android.graphics.Color.WHITE,
    ): Int {
        if (imageUrl.isBlank()) return defaultColor

        // 1. Instant Cache Hit
        colorCache.get(imageUrl)?.let { return it }

        return withContext(Dispatchers.IO) {
            try {
                val appContext = context.applicationContext

                // 2. Instruct Coil to downscale during DECODE, saving memory
                val request = ImageRequest.Builder(appContext)
                    .data(imageUrl)
                    .size(TARGET_SIZE_PX, TARGET_SIZE_PX)
                    .scale(Scale.FILL)
                    .allowHardware(false) // Required for Palette software pixels
                    .build()

                val result = appContext.imageLoader.execute(request)
                val drawable = (result as? SuccessResult)?.drawable ?: return@withContext defaultColor
                val bitmap = drawable.toBitmap(TARGET_SIZE_PX, TARGET_SIZE_PX, Bitmap.Config.ARGB_8888)

                // 3. Process Palette on CPU dispatcher
                val extractedColor = withContext(Dispatchers.Default) {
                    val palette = Palette.from(bitmap)
                        .resizeBitmapArea(RESIZE_BITMAP_AREA)
                        .maximumColorCount(MAX_COLOR_COUNT)
                        .generate()

                    // Prefer clean, muted/light tones suited for Brutalist cards
                    val targetSwatch = palette.lightMutedSwatch
                        ?: palette.mutedSwatch
                        ?: palette.dominantSwatch

                    targetSwatch?.rgb ?: defaultColor
                }

                colorCache.put(imageUrl, extractedColor)
                extractedColor
            } catch (e: Exception) {
                defaultColor
            }
        }
    }
}
