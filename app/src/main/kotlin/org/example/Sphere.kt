package org.example

import kotlin.math.sqrt

class Sphere(
    private val transform: Matrix = Matrix.identity
) {

    private fun localIntersect(ray: Ray, buffer: IntersectionsBuffer) {
        val sphereToRay = ray.origin - Point.zero
        val a = ray.direction dot ray.direction
        val b = 2 * (ray.direction dot sphereToRay)
        val c = (sphereToRay dot sphereToRay) - 1.0
        val d = b * b - 4 * a * c
        if (d < 0) return
        val sd = sqrt(d)
        val t0 = (-b - sd) / (2 * a)
        val t1 = (-b + sd) / (2 * a)
        buffer += Intersection(this, t0)
        buffer += Intersection(this, t1)
    }

    fun intersect(ray: Ray, buffer: IntersectionsBuffer) {
        localIntersect(transform.inverse * ray, buffer)
    }
}
