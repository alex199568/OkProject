package org.example

import org.example.shape.Plane
import org.example.shape.Sphere
import java.util.stream.IntStream
import kotlin.time.measureTimedValue

object App {

    fun renderAaParallel(scene: Scene, camera: Camera, aa: Int): Image {
        val result = Image(camera.w, camera.h)

        IntStream.range(0, camera.h).parallel().forEach { y ->
            val buffer = IntersectionsBuffer()
            for (x in 0 until camera.w) {
                val rays = camera.rays(aa, x, y)
                val color = Color(0, 0, 0)
                for (ray in rays) color += scene.color(ray, buffer)
                result[x, y] = color / rays.size
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("---")

        val redMaterial = Material(Color.red, 0.1, 0.9, 0.9, 200.0)
        val s1 = Sphere(redMaterial, transform { scale(0.3, 0.3, 0.3); move(-0.5, 0.3, 0) })

        val greenMaterial = Material(Color.green, 0.1, 0.9, 0.9, 200.0)
        val s2 = Sphere(greenMaterial, transform { scale(0.2); move(0.3, 0.2, 0) })

        val floorMaterial = Material(Color.white, 0.1, 0.9, 0.9, 200.0)
        val floor = Plane(floorMaterial, transform { scale(16, 0.01, 16) })

        val camera = Camera(640, 480, pi / 3, Matrix.lookAt(Point(0, 1, -2), Point.zero, Vector.y))

        val scene = Scene(
            shapes = listOf(s1, s2, floor),
            lights = listOf(
                Light(Point(-10, 10, -10), Color.lightGray),
                Light(Point(10, 4, -10), Color.gray)
            )
        )

        val (image, duration) = measureTimedValue { renderAaParallel(scene, camera, 4) }
        println("Rendering time: $duration")

        image.save("renders/plane_aa_parallel.png")
    }
}
