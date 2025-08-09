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

    companion object {

        val zero = Point(0, 0, 0)
    }
}
