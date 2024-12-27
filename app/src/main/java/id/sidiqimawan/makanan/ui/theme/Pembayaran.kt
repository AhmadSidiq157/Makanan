package id.sidiqimawan.makanan.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pembayaran(
    totalHarga: Int,
    onPaymentSubmit: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var metodePembayaran by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pembayaran") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Total Harga
            Text(
                text = "Total Harga: Rp $totalHarga",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Form Input
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Nama Pelanggan") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = metodePembayaran,
                    onValueChange = { metodePembayaran = it },
                    label = { Text("Metode Pembayaran") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Tombol Konfirmasi
            Button(
                onClick = { onPaymentSubmit(nama, metodePembayaran) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Bayar")
            }
        }
    }
}
