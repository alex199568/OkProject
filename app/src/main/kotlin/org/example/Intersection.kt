package org.example

class Intersection(
    val shape: Sphere,
    val t: Double
) : Comparable<Intersection> {

    override fun compareTo(other: Intersection): Int {
        return t.compareTo(other.t)
    }
}
