package id.sidiqimawan.makanan.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.sidiqimawan.makanan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodTopAppBar() {
    CenterAlignedTopAppBar(
        title = { Text("Daftar Makanan") },  // Teks judul di AppBar
        navigationIcon = {
            // Menampilkan logo di sisi kiri AppBar
            Image(
                painter = painterResource(id = R.drawable.logo_makanan),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(60.dp) // Ukuran logo
            )
        }
    )
}
