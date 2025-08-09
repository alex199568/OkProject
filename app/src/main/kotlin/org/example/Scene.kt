package org.example

class Scene(
    private val shapes: List<Sphere> = listOf(),
    private val lights: List<Light> = listOf()
) {

    private val buffer = IntersectionsBuffer()

    fun intersect(ray: Ray) {
        buffer.clear()
        for (shape in shapes) {
            shape.intersect(ray, buffer)
        }
    }

    fun shade(context: IntersectionContext): Color {
        val result = Color(0, 0, 0)
        for (light in lights) {
            result += light.calculate(context)
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
