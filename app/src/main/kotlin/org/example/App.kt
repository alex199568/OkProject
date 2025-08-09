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

        println(Matrix.identity)
    }
}
