package org.example

class Matrix(
    vararg items: Number
) {

    private val items = items.map { it.toDouble() }.toDoubleArray()

    constructor(size: Int) : this(
        *Array(size * size) { 0.0 }
    )

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

    operator fun times(other: Matrix): Matrix {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                var s = 0.0
                for (k in 0 until size) {
                    s += this[i, k] * other[k, j]
                }
                result[i, j] = s
            }
        }
        return result
    }

    operator fun times(vector: Vector): Vector {
        return Vector(
            x = this[0, 0] * vector.x + this[0, 1] * vector.y + this[0, 2] * vector.z,
            y = this[1, 0] * vector.x + this[1, 1] * vector.y + this[1, 2] * vector.z,
            z = this[2, 0] * vector.x + this[2, 1] * vector.y + this[2, 2] * vector.z,
        )
    }

    operator fun times(point: Point): Point {
        return Point(
            x = this[0, 0] * point.x + this[0, 1] * point.y + this[0, 2] * point.z + this[0, 3],
            y = this[1, 0] * point.x + this[1, 1] * point.y + this[1, 2] * point.z + this[1, 3],
            z = this[2, 0] * point.x + this[2, 1] * point.y + this[2, 2] * point.z + this[2, 3],
        )
    }

    private val det by lazy {
        if (size == 2) return@lazy this[0, 0] * this[1, 1] - this[1, 0] * this[0, 1]
        var result = 0.0
        for (j in 0 until size) {
            result += this[0, j] * cofactor(0, j)
        }
        result
    }

    private fun minor(i: Int, j: Int): Double {
        return submatrix(i, j).det
    }

    private fun cofactor(i: Int, j: Int): Double {
        val m = minor(i, j)
        if ((i + j) % 2 == 0) return m
        return -m
    }

    private fun submatrix(iExclude: Int, jExclude: Int): Matrix {
        val result = Matrix(size - 1)

        var iOffset = 0
        for (i in 0 until size) {
            if (i == iExclude) {
                iOffset = 1
                continue
            }

            var jOffset = 0
            for (j in 0 until size) {
                if (j == jExclude) {
                    jOffset = 1
                    continue
                }

                result[i - iOffset, j - jOffset] = this[i, j]
            }
        }

        return result
    }

    val transposed by lazy {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result[j, i] = this[i, j]
            }
        }
        result
    }

    val inverse by lazy {
        val result = Matrix(size)

        for (i in 0 until size) {
            for (j in 0 until size) {
                result[j, i] = cofactor(i, j) / det
            }
        }

        result
    }

    companion object {

        val identity = Matrix(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        )

        fun translate(x: Number, y: Number, z: Number): Matrix {
            return Matrix(
                1, 0, 0, x,
                0, 1, 0, y,
                0, 0, 1, z,
                0, 0, 0, 1
            )
        }

        fun scale(x: Number, y: Number, z: Number): Matrix {
            return Matrix(
                x, 0, 0, 0,
                0, y, 0, 0,
                0, 0, z, 0,
                0, 0, 0, 1
            )
        }
    }
}
