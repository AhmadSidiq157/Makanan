package id.sidiqimawan.makanan.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import id.sidiqimawan.makanan.R
import id.sidiqimawan.makanan.data.Makanan
import id.sidiqimawan.makanan.data.makanan
import id.sidiqimawan.makanan.ui.components.MakananItem
import id.sidiqimawan.makanan.ui.theme.components.FoodTopAppBar

@Composable
fun FoodListScreen(
    onAddToCart: (Makanan, Int) -> Unit, // untuk menambah item ke keranjang
    onViewCart: () -> Unit // untuk melihat keranjang
) {
    // Scaffold adalah struktur dasar layar, mencakup AppBar, konten, dan FAB
    Scaffold(
        topBar = { FoodTopAppBar() },
        floatingActionButton = {
            // Tombol melayang di sudut kanan bawah
            FloatingActionButton(onClick = onViewCart) { // Mengarahkan pengguna untuk melihat keranjang
                Text("Cart")
            }
        }
    ) { paddingValues ->
        // LazyColumn digunakan untuk menampilkan daftar makanan
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Menampilkan item dari daftar `makanan`
            items(makanan) { item ->
                MakananItem(
                    makanan = item, // Data makanan yang akan ditampilkan
                    onAddToCart = { jumlah -> onAddToCart(item, jumlah) }, // untuk menambah makanan ke keranjang
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}
