package id.sidiqimawan.makanan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import id.sidiqimawan.makanan.data.makanan
import id.sidiqimawan.makanan.ui.components.MakananItem

@Composable
fun LazyListOrGrid() {
    // Variabel untuk menentukan apakah menggunakan grid layout atau list layout
    val isGrid = true

    if (isGrid) {
        // Jika isGrid = true, gunakan LazyVerticalGrid untuk tata letak grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(
                minSize = dimensionResource(R.dimen.grid_item_min_size) // Ukuran minimum item grid
            ),
            modifier = Modifier.fillMaxSize() // Grid mengisi seluruh ukuran layar
        ) {
            // Tambahkan item dari data makanan ke dalam grid
            items(makanan) { item ->
                MakananItem(
                    makanan = item, // Data makanan untuk item ini
                    onAddToCart = {}, // tombol Tambah ke Keranjang
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)) // Padding di sekitar item
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize() // Kolom mengisi seluruh ukuran layar
        ) {
            // Tambahkan item dari data makanan ke dalam daftar
            items(makanan) { item ->
                MakananItem(
                    makanan = item, // Data makanan untuk item ini
                    onAddToCart = {}, // tombol Tambah ke Keranjang
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)) // Padding di sekitar item
                )
            }
        }
    }
}
