package org.example

import org.example.shape.Shape

class Scene(
    private val shapes: List<Shape> = listOf(),
    private val lights: List<Light> = listOf()
) {

    private fun shadow(shape: Shape, light: Light, point: Point, buffer: IntersectionsBuffer): Double {
        val v = light.position - point
        val distance = v.length
        val direction = v.unit
        val ray = Ray(point, direction)
        intersect(ray, shape, buffer)
        val hit = buffer.hit ?: return 0.0
        if (hit.t < distance) return 1.0
        return 0.0
    }

    private fun intersect(ray: Ray, buffer: IntersectionsBuffer) {
        buffer.clear()
        for (shape in shapes) {
            shape.intersect(ray, buffer)
        }
    }

    private fun intersect(ray: Ray, ignoring: Shape, buffer: IntersectionsBuffer) {
        buffer.clear()
        for (shape in shapes) {
            if (shape != ignoring) {
                shape.intersect(ray, buffer)
            }
        }
    }

    private fun shade(context: IntersectionContext, buffer: IntersectionsBuffer): Color {
        val result = Color(0, 0, 0)
        for (light in lights) {
            val s = shadow(context.hit.shape, light, context.overPoint, buffer)
            result += light.calculate(context, s)
        }
        return result
    }

    fun color(ray: Ray, buffer: IntersectionsBuffer): Color {
        intersect(ray, buffer)
        val hit = buffer.hit ?: return Color.black
        val context = IntersectionContext(ray, hit)
        return shade(context, buffer)
    }
}
