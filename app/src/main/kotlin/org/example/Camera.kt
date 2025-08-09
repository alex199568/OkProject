package org.example

import kotlin.math.tan

class Camera(
    val w: Int,
    val h: Int,
    val fov: Double,
    val view: Matrix = Matrix.identity
) {

    val halfWidth: Double
    val halfHeight: Double
    val pixelSize: Double
    val origin = view.inverse * Point.zero

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

    fun rays(aa: Int, x: Int, y: Int): List<Ray> {
        val samplesPerDim = aa
        val res = ArrayList<Ray>(samplesPerDim * samplesPerDim)
        val step = 1.0 / samplesPerDim

        for (ix in 0 until samplesPerDim) {
            for (iy in 0 until samplesPerDim) {
                val u = (ix + 0.5) * step   // in [0,1)
                val v = (iy + 0.5) * step   // in [0,1)

                val xOffset = (x + u) * pixelSize
                val yOffset = (y + v) * pixelSize
                val worldX = halfWidth - xOffset
                val worldY = halfHeight - yOffset

                val pixel = view.inverse * Point(worldX, worldY, -1)
                val direction = (pixel - origin).unit
                res.add(Ray(origin, direction))
            }
        }
        return res
    }
}
