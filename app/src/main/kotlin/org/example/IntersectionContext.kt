package org.example

class IntersectionContext {

    val hit: Intersection
    val point: Point
    val eye: Vector
    val normal: Vector

    constructor(ray: Ray, hit: Intersection) {
        this.hit = hit
        point = ray[hit.t]
        eye = -ray.direction
        val n = hit.shape.normal(point)
        normal = if (n dot eye < 0) {
            -n
        } else {
            n
        }
    }
}
