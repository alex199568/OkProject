package org.example.shape

import org.example.Intersection
import org.example.IntersectionsBuffer
import org.example.Material
import org.example.Matrix
import org.example.Point
import org.example.Ray
import org.example.Vector
import org.example.epsilon
import kotlin.math.absoluteValue

class Plane(
    material: Material,
    transform: Matrix = Matrix.identity
) : Shape(material, transform) {

    override fun localIntersect(ray: Ray, buffer: IntersectionsBuffer) {
        if (ray.direction.y.absoluteValue < epsilon) return
        val t = -ray.origin.y / ray.direction.y
        buffer += Intersection(this, t)
    }

    override fun localNormal(point: Point): Vector {
        return Vector.y
    }
}
