package id.sidiqimawan.makanan.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.sidiqimawan.makanan.data.Makanan
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartItems: List<Pair<Makanan, Int>>, // Daftar item keranjang belanja
    onRemoveItem: (Pair<Makanan, Int>) -> Unit, // untuk menghapus item
    onBack: () -> Unit, // untuk kembali ke halaman sebelumnya
    onProceedToPembayaran: () -> Unit //  untuk melanjutkan ke pembayaran
) {
    // Scaffold menyediakan struktur dasar untuk layar, seperti AppBar dan konten
    Scaffold(
        topBar = {
            // Membuat AppBar dengan judul dan tombol kembali
            TopAppBar(
                title = { Text("Keranjang Belanja") }, // Judul AppBar
                navigationIcon = {
                    // Tombol kembali di sisi kiri
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali") // Ikon panah kembali
                    }
                }
            )
        }
    ) { paddingValues ->
        // Kolom utama yang berisi konten layar
        Column(
            modifier = Modifier
                .padding(paddingValues) // Padding sesuai dengan Scaffold
                .padding(16.dp) // Padding tambahan 16 dp
                .fillMaxSize() // Mengisi seluruh layar
        ) {
            // LazyColumn untuk menampilkan daftar item di keranjang
            LazyColumn(
                modifier = Modifier.weight(1f) // Mengambil ruang sisa
            ) {
                // Menampilkan setiap item dalam daftar
                items(cartItems) { item ->
                    CartItemRow(
                        makanan = item.first, // Data makanan
                        jumlah = item.second, // Jumlah makanan
                        onRemoveItem = { onRemoveItem(item) } // Callback untuk menghapus item
                    )
                }
            }

            // Menghitung total harga dari semua item di keranjang
            val totalHarga = cartItems.sumOf { it.first.harga * it.second }
            // Memformat total harga ke dalam format rupiah
            val formattedTotal = NumberFormat.getInstance(Locale("id", "ID")).format(totalHarga.toLong())

            // Menampilkan total harga
            Text(
                text = "Total Harga: Rp $formattedTotal",
                style = MaterialTheme.typography.titleMedium, // Gaya teks
                modifier = Modifier
                    .align(Alignment.End) // Memposisikan teks di kanan
                    .padding(16.dp) // Padding di sekitar teks
            )

            // Tombol untuk melanjutkan ke pembayaran
            Button(
                onClick = onProceedToPembayaran, // Callback untuk pembayaran
                modifier = Modifier.fillMaxWidth() // Tombol mengisi lebar penuh
            ) {
                Text("Lanjut ke Pembayaran") // Teks pada tombol
            }
        }
    }
}

@Composable
fun CartItemRow(
    makanan: Makanan, // Data makanan
    jumlah: Int, // Jumlah makanan
    onRemoveItem: () -> Unit // Callback untuk menghapus item
) {
    // Baris untuk menampilkan detail item di keranjang
    Row(
        modifier = Modifier
            .fillMaxWidth() // Baris mengisi lebar penuh
            .padding(vertical = 8.dp), // Padding atas dan bawah
        verticalAlignment = Alignment.CenterVertically // Item sejajar secara vertikal di tengah
    ) {
        // Kolom untuk nama makanan dan detail harga
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = makanan.name, // Menampilkan nama makanan
                style = MaterialTheme.typography.bodyLarge // Gaya teks untuk nama
            )
            Text(
                text = "Rp ${NumberFormat.getInstance(Locale("id", "ID")).format(makanan.harga.toLong())} x $jumlah", // Harga per item dan jumlah
                style = MaterialTheme.typography.bodyMedium // Gaya teks untuk detail harga
            )
        }
        // Tombol untuk menghapus item
        IconButton(onClick = onRemoveItem) {
            Icon(Icons.Default.ExpandLess, contentDescription = "Hapus") // Ikon panah untuk menghapus item
        }
    }
}
