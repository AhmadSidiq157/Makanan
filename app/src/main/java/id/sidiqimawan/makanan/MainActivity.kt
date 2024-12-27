package id.sidiqimawan.makanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.sidiqimawan.makanan.data.Makanan
import id.sidiqimawan.makanan.ui.theme.CartScreen
import id.sidiqimawan.makanan.ui.theme.FoodListScreen
import id.sidiqimawan.makanan.ui.theme.MakananTheme
import id.sidiqimawan.makanan.ui.theme.Pembayaran
import id.sidiqimawan.makanan.ui.theme.OrderDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakananTheme { // Menerapkan tema aplikasi
                MakananApp()
            }
        }
    }
}

@Composable
fun MakananApp() {
    // Navigation controller untuk mengatur perpindahan antar layar
    val navController = rememberNavController()

    // State untuk menyimpan item yang ditambahkan ke keranjang belanja
    val keranjangBelanja = remember { mutableStateListOf<Pair<Makanan, Int>>() }

    // Variabel untuk menyimpan detail pesanan terakhir
    var lastOrderDetails = mutableListOf<Pair<Makanan, Int>>()

    // NavHost dengan berbagai destinasi
    NavHost(navController = navController, startDestination = "food_list") {
        // untuk daftar makanan
        composable("food_list") {
            FoodListScreen(
                onAddToCart = { makanan, jumlah ->
                    // Menambahkan item ke keranjang, atau memperbarui jumlah jika sudah ada
                    val existingItem = keranjangBelanja.find { it.first == makanan }
                    if (existingItem != null) {
                        keranjangBelanja[keranjangBelanja.indexOf(existingItem)] =
                            existingItem.copy(second = existingItem.second + jumlah)
                    } else {
                        keranjangBelanja.add(makanan to jumlah)
                    }
                },
                onViewCart = { navController.navigate("cart") } // Navigasi ke keranjang belanja
            )
        }

        // untuk keranjang belanja
        composable("cart") {
            CartScreen(
                cartItems = keranjangBelanja,
                onRemoveItem = { item -> keranjangBelanja.remove(item) }, // Menghapus item dari keranjang
                onBack = { navController.popBackStack() }, // Kembali ke layar sebelumnya
                onProceedToPembayaran = {
                    // Hitung total harga dan navigasi ke layar pembayaran
                    val totalHarga = keranjangBelanja.sumOf { it.first.harga * it.second }
                    navController.navigate("payment/$totalHarga")
                }
            )
        }

        // untuk layar pembayaran
        composable("payment/{totalHarga}") { backStackEntry ->
            val totalHarga = backStackEntry.arguments?.getString("totalHarga")?.toInt() ?: 0
            Pembayaran(
                totalHarga = totalHarga,
                onPaymentSubmit = { nama, metodePembayaran ->
                    // Simpan detail pesanan terakhir
                    lastOrderDetails.clear()
                    lastOrderDetails.addAll(keranjangBelanja)
                    keranjangBelanja.clear() // Kosongkan keranjang setelah pembayaran selesai

                    // Navigasi ke layar detail pemesanan
                    navController.navigate("order_detail/$nama/$metodePembayaran")
                },
                onBack = { navController.popBackStack() } // Kembali ke layar sebelumnya
            )
        }

        // untuk layar detail pemesanan
        composable("order_detail/{nama}/{metodePembayaran}") { backStackEntry ->
            val nama = backStackEntry.arguments?.getString("nama") ?: "Tidak Diketahui" // Ambil nama dari argumen navigasi
            val metodePembayaran = backStackEntry.arguments?.getString("metodePembayaran") ?: "Tidak Diketahui" // Ambil metode pembayaran
            OrderDetailScreen(
                orderDetails = lastOrderDetails, // Kirim detail pesanan terakhir
                namaPemesan = nama,
                metodePembayaran = metodePembayaran,
                onBackToMenu = { navController.navigate("food_list") } // Kembali ke daftar makanan
            )
        }
    }
}
