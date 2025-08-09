package org.example

import kotlin.math.pow

class Light(
    val position: Point,
    val intensity: Color
) {

    fun calculate(
        material: Material,
        point: Point,
        eye: Vector,
        normal: Vector
    ): Color {
        val effectiveColor = material.color * intensity
        val light = (position - point).unit
        val ambient = effectiveColor * material.ambient
        val lightDotNormal = light dot normal
        if (lightDotNormal < 0) return ambient
        val diffuse = effectiveColor * (material.diffuse * lightDotNormal)
        val reflect = (-light).reflect(normal)
        val reflectDotEye = reflect dot eye
        if (reflectDotEye <= 0) return ambient + diffuse
        val factor = reflectDotEye.pow(material.shininess)
        val specular = intensity * (material.specular * factor)
        return ambient + diffuse + specular
    }
}
