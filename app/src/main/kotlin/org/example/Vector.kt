package org.example

import kotlin.math.sqrt

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

    infix fun dot(other: Vector): Double {
        return x * other.x + y * other.y + z * other.z
    }

    val squaredLength by lazy {
        this dot this
    }

    val length by lazy {
        sqrt(squaredLength)
    }

    val unit by lazy {
        this / length
    }

    infix fun cross(other: Vector): Vector {
        return Vector(
            x = y * other.z - z * other.y,
            y = z * other.x - x * other.z,
            z = x * other.y - y * other.x
        )
    }

    companion object {

        val zero = Vector(0, 0, 0)
        val x = Vector(1, 0, 0)
        val y = Vector(0, 1, 0)
        val z = Vector(0, 0, 1)
    }
}
