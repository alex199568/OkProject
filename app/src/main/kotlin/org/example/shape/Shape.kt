package org.example.shape

import org.example.IntersectionsBuffer
import org.example.Material
import org.example.Matrix
import org.example.Point
import org.example.Ray
import org.example.Vector

abstract class Shape(
    val material: Material,
    private val transform: Matrix = Matrix.Companion.identity
) {

    abstract fun localIntersect(ray: Ray, buffer: IntersectionsBuffer)

    fun intersect(ray: Ray, buffer: IntersectionsBuffer) {
        localIntersect(transform.inverse * ray, buffer)
    }

    abstract fun localNormal(point: Point): Vector

    fun normal(point: Point): Vector {
        val objectPoint = transform.inverse * point
        val objectNormal = localNormal(objectPoint)
        val worldNormal = transform.inverse.transposed * objectNormal
        return worldNormal.unit
    }
}