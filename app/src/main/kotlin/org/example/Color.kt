package org.example

data class Color(
    var r: Double,
    var g: Double,
    var b: Double
) {

    constructor(r: Number, g: Number, b: Number): this(
        r = r.toDouble(),
        g = g.toDouble(),
        b = b.toDouble()
    )

    operator fun plus(other: Color): Color {
        return Color(
            r = r + other.r,
            g = g + other.g,
            b = b + other.b
        )
    }

    operator fun plusAssign(other: Color) {
        r += other.r
        g += other.g
        b += other.b
    }

    operator fun minus(other: Color): Color {
        return Color(
            r = r - other.r,
            g = g - other.g,
            b = b - other.b
        )
    }

    operator fun times(other: Color): Color {
        return Color(
            r = r * other.g,
            g = g * other.g,
            b = b * other.b
        )
    }

    operator fun times(n: Number): Color {
        val d = n.toDouble()
        return Color(
            r = r * d,
            g = g * d,
            b = b * d
        )
    }

    operator fun div(n: Number): Color {
        val d = n.toDouble()
        return Color(
            r = r / d,
            g = g / d,
            b = b / d
        )
    }

    val rgb: Int
        get() {
            val r = (r * 255.0).toInt().coerceIn(0, 255)
            val g = (g * 255.0).toInt().coerceIn(0, 255)
            val b = (b * 255.0).toInt().coerceIn(0, 255)
            return (r shl 16) or (g shl 8) or b
        }

    companion object {

        val white = Color(1, 1, 1)
        val lightGray = Color(0.75, 0.75, 0.75)
        val gray = Color(0.5, 0.5, 0.5)
        val darkGray = Color(0.25, 0.25, 0.25)
        val black = Color(0, 0, 0)

        val red = Color(1, 0, 0)
        val green = Color(0, 1, 0)
        val blue = Color(0, 0, 1)
    }
}
