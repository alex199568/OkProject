package org.example

import kotlin.time.measureTime

object App {

    private fun renderImage() {
        val image = Image(640, 480)
        for (y in 20 until 100) {
            for (x in 30 until 300) {
                image[x, y] = Color.red
            }
        }
        image.save("renders/image.png")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("---")

        val rayOrigin = Point(0, 0, -5)
        val wallZ = 10.0
        val wallSize = 7.0
        val canvasPixels = 640
        val pixelSize = wallSize / canvasPixels
        val half = wallSize / 2
        val canvas = Image(canvasPixels, canvasPixels)
        val color = Color.red
        val shape = Sphere()
        val buffer = IntersectionsBuffer()

        val duration = measureTime {
            for (y in 0 until canvasPixels) {
                val worldY = half - pixelSize * y
                for (x in 0 until canvasPixels) {
                    buffer.clear()

                    val worldX = -half + pixelSize * x
                    val position = Point(worldX, worldY, wallZ)
                    val ray = Ray(rayOrigin, (position - rayOrigin).unit)
                    shape.intersect(ray, buffer)
                    val hit = buffer.hit
                    if (hit != null) {
                        canvas[x, y] = color
                    }
                }
            }
        }
        println("Rendering time: $duration")

        canvas.save("renders/sphere.png")
    }
}
