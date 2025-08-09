package org.example

data class Point(
    val x: Double,
    val y: Double,
    val z: Double
) {

    constructor(x: Number, y: Number, z: Number) : this(
        x = x.toDouble(),
        y = y.toDouble(),
        z = z.toDouble()
    )

    operator fun plus(vector: Vector): Point {
        return Point(
            x = x + vector.x,
            y = y + vector.y,
            z = z + vector.z
        )
    }

    operator fun minus(vector: Vector): Point {
        return Point(
            x = x - vector.x,
            y = y - vector.y,
            z = z - vector.z
        )
    }

    operator fun minus(point: Point): Vector {
        return Vector(
            x = x - point.x,
            y = y - point.y,
            z = z - point.z
        )
    }

    companion object {

        val zero = Point(0, 0, 0)
    }
}
