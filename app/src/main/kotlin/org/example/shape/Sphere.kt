package org.example.shape

import org.example.Intersection
import org.example.IntersectionsBuffer
import org.example.Material
import org.example.Matrix
import org.example.Point
import org.example.Ray
import org.example.Vector
import kotlin.math.sqrt

class Sphere(
    material: Material,
    transform: Matrix = Matrix.Companion.identity
) : Shape(material, transform) {

    override fun localIntersect(ray: Ray, buffer: IntersectionsBuffer) {
        val sphereToRay = ray.origin - Point.Companion.zero
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

    override fun localNormal(point: Point): Vector {
        return point - Point.Companion.zero
    }
}
