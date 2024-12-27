package id.sidiqimawan.makanan.ui.theme


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.sidiqimawan.makanan.viewmodel.MakananViewModel

@Composable
fun MakananApp() {
    // Membuat NavController untuk mengatur navigasi antar layar
    val navController = rememberNavController()

    // Mendapatkan instance `MakananViewModel` yang akan digunakan untuk manajemen data
    val viewModel: MakananViewModel = viewModel()

    // Mendefinisikan NavHost untuk mengatur navigasi dengan rute awal `food_list`
    NavHost(navController = navController, startDestination = "food_list") {

        composable("food_list") {
            // Memanggil `FoodListScreen` dan memberikan fungsi callback/kembali
            FoodListScreen(
                onAddToCart = { makanan, jumlah -> viewModel.tambahKeKeranjang(makanan, jumlah) },     // Menambahkan makanan ke keranjang melalui ViewModel
                onViewCart = { navController.navigate("cart") }    // Navigasi ke layar keranjang
            )
        }

        composable("cart") {
            // Memanggil `CartScreen` dan memberikan data serta fungsi callback/kembali
            CartScreen(
                cartItems = viewModel.keranjangBelanja,       // Mengambil daftar item di keranjang dari ViewModel
                onRemoveItem = { item -> viewModel.hapusDariKeranjang(item) },    // Menghapus item dari keranjang melalui ViewModel
                onBack = { navController.popBackStack() },  // Navigasi kembali ke layar sebelumnya
                onProceedToPembayaran = {  // Navigasi ke layar pembayaran
                    // Navigasi ke pembayaran
                    navController.navigate("pembayaran")
                }
            )
        }

        // Rute untuk layar pembayaran
        composable("pembayaran") {
            // Saat ini hanya menampilkan teks placeholder
            Text(text = "Halaman Pembayaran (dalam pengembangan)")
        }
    }
}
