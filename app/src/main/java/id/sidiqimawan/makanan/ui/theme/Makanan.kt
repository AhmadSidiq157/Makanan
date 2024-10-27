package id.sidiqimawan.makanan.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import id.sidiqimawan.makanan.R

data class Makanan(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    val harga: String
)

val makanan = listOf(
    Makanan(R.drawable.nasi_goreng, R.string.nasi_goreng, R.string.description_nasi_goreng,"15.000"),
    Makanan(R.drawable.sate_ayam, R.string.sate_ayam, R.string.description_sate_ayam,"12.000"),
    Makanan(R.drawable.gado_gado, R.string.gado_gado, R.string.description_gado_gado, "10.000"),
    Makanan(R.drawable.rendang, R.string.rendang, R.string.description_rendang, "25.000"),
    Makanan(R.drawable.bakso, R.string.bakso, R.string.description_bakso, "22.000"),
    Makanan(R.drawable.mie_ayam, R.string.mie_ayam, R.string.description_mie_ayam,"12.000"),
    Makanan(R.drawable.ayam_goreng, R.string.ayam_goreng, R.string.description_ayam_goreng, "17.000"),
    Makanan(R.drawable.soto, R.string.soto, R.string.description_soto, "11.000"),
    Makanan(R.drawable.pempek, R.string.pempek, R.string.description_pempek, "20.000")
)
