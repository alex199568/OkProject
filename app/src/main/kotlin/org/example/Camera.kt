package org.example

import kotlin.math.tan

class Camera(
    val w: Int,
    val h: Int,
    val fov: Double,
    val view: Matrix = Matrix.identity
) {

    private val halfWidth: Double
    private val halfHeight: Double
    private val pixelSize: Double
    private val origin = view.inverse * Point.zero

    init {
        val halfView = tan(fov / 2)
        val aspect = w.toDouble() / h.toDouble()
        if (aspect >= 1) {
            halfWidth = halfView
            halfHeight = halfView / aspect
        } else {
            halfWidth = halfView * aspect
            halfHeight = halfView
        }
        pixelSize = (halfWidth * 2) / w
    }

    fun ray(x: Int, y: Int): Ray {
        val xOffset = (x + 0.5) * pixelSize
        val yOffset = (y + 0.5) * pixelSize
        val worldX = halfWidth - xOffset
        val worldY = halfHeight - yOffset
        val pixel = view.inverse * Point(worldX, worldY, -1)
        val direction = (pixel - origin).unit
        return Ray(origin, direction)
    }
}
