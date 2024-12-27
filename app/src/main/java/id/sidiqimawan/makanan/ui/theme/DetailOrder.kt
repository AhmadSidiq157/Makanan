package id.sidiqimawan.makanan.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.sidiqimawan.makanan.data.Makanan
import java.text.NumberFormat
import java.util.*
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(
    orderDetails: List<Pair<Makanan, Int>>, // List berisi pasangan item makanan dan jumlah
    namaPemesan: String, // Nama pemesan yang ditampilkan di UI
    metodePembayaran: String, // Metode pembayaran yang ditampilkan di UI
    onBackToMenu: () -> Unit // Kembali untuk navigasi kembali ke menu
) {
    // Menghitung total harga dari semua item di orderDetails
    val totalHarga = orderDetails.sumOf { it.first.harga * it.second }
    // Memformat total harga menjadi format mata uang Indonesia
    val formattedTotal = NumberFormat.getInstance(Locale("id", "ID")).format(totalHarga)

    // Scaffold menyediakan kerangka tata letak untuk layar, termasuk TopAppBar
    Scaffold(
        topBar = {
            // Menampilkan top bar dengan judul "Detail Pemesanan"
            TopAppBar(title = { Text("Detail Pemesanan") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp) // Padding tambahan
                .fillMaxSize() // Mengisi seluruh layar
        ) {
            // Informasi nama pemesan
            Text(text = "Nama Pemesan: $namaPemesan", style = MaterialTheme.typography.bodyLarge)
            // Informasi metode pembayaran
            Text(text = "Metode Pembayaran: $metodePembayaran", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp)) // Memberikan jarak vertikal

            // Daftar item pesanan
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orderDetails) { item ->
                    // Menampilkan item pesanan dalam satu baris
                    Row(
                        modifier = Modifier
                            .fillMaxWidth() // Baris memenuhi lebar layar
                            .padding(vertical = 8.dp), // Jarak antar item
                        horizontalArrangement = Arrangement.SpaceBetween // Elemen berjarak di kiri dan kanan
                    ) {
                        // Nama makanan
                        Text(item.first.name, style = MaterialTheme.typography.bodyLarge)
                        // Jumlah makanan
                        Text("x${item.second}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Memberikan jarak vertikal

            // Menampilkan total harga
            Text(
                text = "Total Harga: Rp $formattedTotal", // Menampilkan total harga
                style = MaterialTheme.typography.titleMedium, // Menggunakan gaya teks yang lebih besar
                modifier = Modifier.align(Alignment.End) // Menempatkan teks di sebelah kanan
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol untuk kembali ke menu
            Button(
                onClick = onBackToMenu, // Fungsi kembali saat tombol ditekan
                modifier = Modifier.fillMaxWidth() // Tombol memenuhi lebar layar
            ) {
                Text("Kembali ke Menu") // Teks pada tombol
            }
        }
    }
}
