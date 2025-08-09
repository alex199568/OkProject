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

        val p = Point(1, -2, 3)
        val tr = Matrix.translate(1, 2, -3) * Matrix.scale(0.2, 1.2, 0.5)
        val transformed = tr * p
        val original = tr.inverse * transformed
        println(p)
        println(transformed)
        println(original)
    }
}
