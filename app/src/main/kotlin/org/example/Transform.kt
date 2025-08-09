package org.example

class Transform {

    private var translation = Matrix.identity
    private var rotationX = Matrix.identity
    private var rotationY = Matrix.identity
    private var rotationZ = Matrix.identity
    private var scale = Matrix.identity

    fun translate(x: Number, y: Number, z: Number): Transform {
        translation = Matrix.translate(x, y, z)
        return this
    }

    fun rotateX(rads: Number): Transform {
        rotationX = Matrix.rotationX(rads)
        return this
    }

    fun rotateY(rads: Number): Transform {
        rotationY = Matrix.rotationY(rads)
        return this
    }

    fun rotateZ(rads: Number): Transform {
        rotationZ = Matrix.rotationZ(rads)
        return this
    }

    fun scale(x: Number, y: Number, z: Number): Transform {
        scale = Matrix.scale(x, y, z)
        return this
    }

    fun build(): Matrix {
        return translation * rotationZ * rotationY * rotationX * scale
    }
}
