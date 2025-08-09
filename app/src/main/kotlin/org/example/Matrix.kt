package org.example

class Matrix(
    vararg items: Number
) {

    private val items = items.map { it.toDouble() }.toDoubleArray()

    private val size by lazy {
        when (items.size) {
            4 -> 2
            9 -> 3
            16 -> 4
            else -> 0
        }
    }

    operator fun get(i: Int, j: Int): Double {
        return items[i * size + j]
    }

    operator fun set(i: Int, j: Int, n: Number) {
        items[i * size + j] = n.toDouble()
    }

    override fun toString(): String {
        return buildString {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    append("%6.3f".format(this@Matrix[i, j]))
                }
                appendLine()
            }
        }
    }

    companion object {

        val identity = Matrix(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        )
    }
}
