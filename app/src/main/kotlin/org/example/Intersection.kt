package org.example

import org.example.shape.Shape

class Intersection(
    val shape: Shape,
    val t: Double
) : Comparable<Intersection> {

    override fun compareTo(other: Intersection): Int {
        return t.compareTo(other.t)
    }
}
