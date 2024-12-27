package id.sidiqimawan.makanan.data

import id.sidiqimawan.makanan.R

data class Makanan(
    val id: Int,
    val name: String,
    val harga: Int,
    val imageResourceId: Int,
    val description: String
)

val makanan = listOf(
    Makanan(1, "Nasi Goreng", 15000, R.drawable.nasi_goreng, "Deskripsi Nasi Goreng : Nasi Goreng adalah hidangan nasi goreng khas Indonesia yang dimasak dengan kecap manis, telur, dan bumbu rempah."),
    Makanan(2, "Sate Ayam", 12000, R.drawable.sate_ayam, "Deskripsi Sate Ayam : Sate adalah daging yang ditusuk dan dipanggang, biasanya disajikan dengan saus kacang atau kecap manis."),
    Makanan(3, "Gado-gado", 10000, R.drawable.gado_gado, "Deskripsi Gado-gado : Gado-Gado adalah salad sayuran yang disiram dengan saus kacang yang gurih dan lezat."),
    Makanan(4, "Rendang", 25000, R.drawable.rendang, "Deskripsi Rendang: Rendang adalah daging sapi yang dimasak dengan santan dan bumbu khas Minang hingga empuk dan beraroma."),
    Makanan(5, "Bakso", 22000, R.drawable.bakso, "Deskripsi Bakso : Bakso adalah bola daging yang direbus, biasanya disajikan dengan kuah kaldu yang gurih."),
    Makanan(6, "Mie Ayam", 12000, R.drawable.mie_ayam, "Deskripsi Mie Ayam : Mie Ayam adalah mie yang disajikan dengan potongan ayam berbumbu dan kuah yang gurih atau manis."),
    Makanan(7, "Ayam Goreng", 17000, R.drawable.ayam_goreng, "Deskripsi Ayam Goreng : Ayam Goreng adalah ayam yang digoreng dengan bumbu kuning, renyah di luar dan empuk di dalam."),
    Makanan(8, "Soto", 11000, R.drawable.soto, "Deskripsi Soto : Soto adalah sup dengan bumbu rempah yang khas, berisi daging atau ayam, dan sering disajikan dengan nasi atau lontong."),
    Makanan(9, "Pempek", 20000, R.drawable.pempek, "Deskripsi Pempek : Pempek adalah makanan khas Palembang yang terbuat dari ikan dan tepung, disajikan dengan kuah cuka yang asam manis.")
)
