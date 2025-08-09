package org.example

object App {

    @JvmStatic
    fun main(args: Array<String>) {
        println("---")

        val image = Image(640, 480)
        for (y in 20 until 100) {
            for (x in 30 until 300) {
                image[x, y] = Color.red
            }
        }
        image.save("renders/image.png")
    }
}
