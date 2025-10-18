package saritoga.crud1

// Mobil.kt
class Mobil(val merk: String, val tahun: Int) {
    fun nyalakanMesin() {
        println("$merk tahun $tahun: Mesin dinyalakan!")
    }
}

// Fungsi main adalah titik masuk program Kotlin
fun main() {
    val mobilSaya = Mobil("Toyota", 2020)
    println(mobilSaya.merk)
    mobilSaya.nyalakanMesin()
}
