package org.example

class IntersectionsBuffer {

    private val buffer = mutableListOf<Intersection>()

    operator fun plusAssign(i: Intersection) {
        buffer.insertSorted(i)
    }

    val hit: Intersection?
        get() {
            return buffer.firstOrNull { it.t >= 0.0 }
        }

    fun clear() {
        buffer.clear()
    }
}
