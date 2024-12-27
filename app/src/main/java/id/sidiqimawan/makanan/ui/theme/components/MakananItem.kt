package id.sidiqimawan.makanan.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.sidiqimawan.makanan.R
import id.sidiqimawan.makanan.data.Makanan

@Composable
fun MakananItem(
    makanan: Makanan,     // Data makanan yang ditampilkan
    onAddToCart: (Int) -> Unit,  // untuk menambahkan jumlah ke keranjang
    modifier: Modifier = Modifier   // Modifier opsional untuk mengatur tampilan
) {
    // untuk mengatur apakah deskripsi makanan diperluas atau tidak
    var expanded by remember { mutableStateOf(false) }
    // untuk mengatur jumlah makanan yang ingin ditambahkan ke keranjang
    var jumlah by remember { mutableStateOf(1) } // Default jumlah adalah 1

    // Card sebagai container utama
    Card(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small)), // Padding card
        shape = RoundedCornerShape(8.dp), // Bentuk card dengan sudut membulat
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // Kolom untuk mengatur tata letak secara vertikal
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            // Baris untuk bagian header dari card
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Baris mengisi lebar penuh
                    .padding(dimensionResource(R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically // Isi sejajar secara vertikal
            ) {
                // Gambar makanan
                Image(
                    painter = painterResource(id = makanan.imageResourceId), // Gambar dari resource ID
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp) // Ukuran gambar 64dp
                        .clip(RoundedCornerShape(4.dp)), // Bentuk gambar dengan sudut membulat
                    contentScale = ContentScale.Crop // Skalakan gambar sesuai frame
                )
                Spacer(modifier = Modifier.width(8.dp)) // Spasi antara gambar dan teks
                // Kolom untuk teks
                Column(modifier = Modifier.weight(1f)) { // Mengambil sisa ruang
                    Text(
                        text = makanan.name, // Nama makanan
                        style = MaterialTheme.typography.headlineSmall // Gaya teks
                    )
                    Text(
                        text = "Harga: Rp ${makanan.harga}", // Harga makanan
                        style = MaterialTheme.typography.bodyMedium, // Gaya teks harga
                        modifier = Modifier.padding(top = 4.dp), // Padding atas
                        color = MaterialTheme.colorScheme.primary // Warna teks harga
                    )
                    if (expanded) { // Jika diperluas, tampilkan deskripsi
                        Text(
                            text = makanan.description, // Deskripsi makanan
                            style = MaterialTheme.typography.bodySmall, // Gaya teks deskripsi
                            modifier = Modifier.padding(top = 4.dp) // Padding atas
                        )
                    }
                }
                // Tombol untuk memperluas atau menciutkan deskripsi
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null
                    )
                }
            }
            // Baris untuk mengatur jumlah makanan
            Row(
                verticalAlignment = Alignment.CenterVertically, // Sejajar secara vertikal
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
            ) {
                // Tombol untuk mengurangi jumlah
                Button(onClick = { if (jumlah > 1) jumlah-- }) {
                    Text("-")
                }
                // Teks untuk menampilkan jumlah saat ini
                Text(
                    text = jumlah.toString(), // Tampilkan jumlah
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                // Tombol untuk menambah jumlah
                Button(onClick = { jumlah++ }) {
                    Text("+")
                }
            }
            // Tombol untuk menambahkan makanan ke keranjang
            Button(
                onClick = { onAddToCart(jumlah) },
                modifier = Modifier
                    .fillMaxWidth() // Mengisi lebar penuh
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
            ) {
                Text("Tambah ke Keranjang")
            }
        }
    }
}
