package org.example

data class Ray(
    val origin: Point,
    val direction: Vector
) {

    operator fun get(t: Double): Point {
        return origin + direction * t
    }
}
