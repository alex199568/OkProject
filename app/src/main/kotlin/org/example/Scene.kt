package org.example

import org.example.shape.Shape

class Scene(
    private val shapes: List<Shape> = listOf(),
    private val lights: List<Light> = listOf()
) {

    private val buffer = IntersectionsBuffer()

    private fun shadow(shape: Shape, light: Light, point: Point): Double {
        val v = light.position - point
        val distance = v.length
        val direction = v.unit
        val ray = Ray(point, direction)
        intersect(ray, shape)
        val hit = buffer.hit ?: return 0.0
        if (hit.t < distance) return 1.0
        return 0.0
    }

    private fun intersect(ray: Ray) {
        buffer.clear()
        for (shape in shapes) {
            shape.intersect(ray, buffer)
        }
    }

    private fun intersect(ray: Ray, ignoring: Shape) {
        buffer.clear()
        for (shape in shapes) {
            if (shape != ignoring) {
                shape.intersect(ray, buffer)
            }
        }
    }

    private fun shade(context: IntersectionContext): Color {
        val result = Color(0, 0, 0)
        for (light in lights) {
            val s = shadow(context.hit.shape, light, context.overPoint)
            result += light.calculate(context, s)
        }
        return result
    }

    fun color(ray: Ray): Color {
        intersect(ray)
        val hit = buffer.hit ?: return Color.black
        val context = IntersectionContext(ray, hit)
        return shade(context)
    }
}
