package org.example

object App {

    private fun renderImage() {
        val image = Image(640, 480)
        for (y in 20 until 100) {
            for (x in 30 until 300) {
                image[x, y] = Color.red
            }
        }
        image.save("renders/image.png")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("---")

        Math.PI

        val p = Point(1, -2, 3)
        val tr = transform {
            translate(1, 2, 3)
            rotateX(pi / 3)
            rotateY(pi / 2)
            rotateZ(pi / 4)
            scale(2, 0.4, 2)
        }
        val transformed = tr * p
        val original = tr.inverse * transformed
        println(p)
        println(transformed)
        println(original)
    }
}
