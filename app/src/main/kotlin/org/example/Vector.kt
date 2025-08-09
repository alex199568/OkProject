package org.example

data class Vector(
    val x: Double,
    val y: Double,
    val z: Double
) {

    constructor(x: Number, y: Number, z: Number) : this(
        x = x.toDouble(),
        y = y.toDouble(),
        z = z.toDouble()
    )

    operator fun plus(other: Vector): Vector {
        return Vector(
            x = x + other.x,
            y = y + other.y,
            z = z + other.z
        )
    }

    operator fun minus(other: Vector): Vector {
        return Vector(
            x = x - other.x,
            y = y - other.y,
            z = z - other.z
        )
    }

    operator fun unaryMinus(): Vector {
        return Vector(-x, -y, -z)
    }

    operator fun times(n: Number): Vector {
        val d = n.toDouble()
        return Vector(x * d, y * d, z * d)
    }

    operator fun div(n: Number): Vector {
        val d = n.toDouble()
        return Vector(x / d, y / d, z / d)
    }

    companion object {

        val zero = Vector(0, 0, 0)
        val x = Vector(1, 0, 0)
        val y = Vector(0, 1, 0)
        val z = Vector(0, 0, 1)
    }
}
