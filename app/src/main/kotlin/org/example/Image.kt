package org.example

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Image(
    w: Int,
    h: Int
) {

    private val colors = Array(h) { Array(w) { Color.black } }

    operator fun get(x: Int, y: Int): Color {
        return colors[y][x]
    }

    operator fun set(x: Int, y: Int, color: Color) {
        colors[y][x] = color
    }

    val w: Int
        get() {
            return colors[0].size
        }

    val h: Int
        get() {
            return colors.size
        }

    val buffered: BufferedImage
        get() {
            val result = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
            for (y in 0 until h) {
                for (x in 0 until w) {
                    val color = this[x, y]
                    result.setRGB(x, y, color.rgb)
                }
            }
            return result
        }

    fun save(path: String) {
        ImageIO.write(buffered, "png", File(path))
    }
}
