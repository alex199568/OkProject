package org.example

import kotlin.time.measureTimedValue

object App {

    private fun render(scene: Scene, camera: Camera): Image {
        val result = Image(camera.w, camera.h)
        for (y in 0 until camera.h) {
            for (x in 0 until camera.w) {
                result[x, y] = scene.color(camera.ray(x, y))
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("---")

        val redMaterial = Material(Color.red, 0.1, 0.9, 0.9, 200.0)
        val s1 = Sphere(redMaterial, transform { scale(0.3, 0.3, 0.3); move(-0.5, 0, 0) })

        val greenMaterial = Material(Color.green, 0.1, 0.9, 0.9, 200.0)
        val s2 = Sphere(greenMaterial, transform { scale(0.2); move(0.3, 0, 0) })

        val camera = Camera(640, 480, pi / 3, Matrix.lookAt(Point(0, 0, -2), Point.zero, Vector.y))

        val scene = Scene(
            shapes = listOf(s1, s2),
            lights = listOf(
                Light(Point(-10, 10, -10), Color.gray),
                Light(Point(10, 4, -10), Color.darkGray)
            )
        )

        val (image, duration) = measureTimedValue { render(scene, camera) }
        println("Rendering time: $duration")

        image.save("renders/scene.png")
    }
}
