package org.example

fun transform(builder: Transform.() -> Unit): Matrix {
    val tr = Transform()
    tr.builder()
    return tr.build()
}

val pi = Math.PI

val Number.degs: Double
    get() {
        return Math.toDegrees(toDouble())
    }

val Number.rads: Double
    get() {
        return Math.toRadians(toDouble())
    }

fun <T : Comparable<T>> MutableList<T>.insertSorted(item: T) {
    val index = this.binarySearch(item)
    val insertIndex = if (index < 0) -(index + 1) else index
    this.add(insertIndex, item)
}
