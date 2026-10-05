package site.prito.dutta.core.common.ext

/** Created by Pritom Dutta on 6/10/26 */

fun String.firstLetterCapitalize(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}