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

    companion object {

        val zero = Vector(0, 0, 0)
        val x = Vector(1, 0, 0)
        val y = Vector(0, 1, 0)
        val z = Vector(0, 0, 1)
    }
}
