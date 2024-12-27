package id.sidiqimawan.makanan.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import id.sidiqimawan.makanan.data.Makanan

// ViewModel untuk mengelola data terkait makanan dan keranjang belanja
class MakananViewModel : ViewModel() {

    // Daftar mutable yang menyimpan item dalam keranjang belanja
    // Pair terdiri dari objek `Makanan` dan jumlahnya (Int)
    val keranjangBelanja = mutableStateListOf<Pair<Makanan, Int>>()

    // Fungsi untuk menambahkan makanan ke keranjang
    fun tambahKeKeranjang(makanan: Makanan, jumlah: Int) {
        // Mencari apakah makanan sudah ada di keranjang
        val existingItem = keranjangBelanja.find { it.first == makanan }

        if (existingItem != null) {
            // Jika sudah ada, tambahkan jumlahnya
            keranjangBelanja[keranjangBelanja.indexOf(existingItem)] =
                existingItem.copy(second = existingItem.second + jumlah)
        } else {
            // Jika belum ada, tambahkan item baru ke daftar keranjang
            keranjangBelanja.add(makanan to jumlah)
        }
    }

    // Fungsi untuk menghapus item dari keranjang
    fun hapusDariKeranjang(item: Pair<Makanan, Int>) {
        keranjangBelanja.remove(item) // Menghapus pasangan makanan dan jumlah dari daftar
    }
}
