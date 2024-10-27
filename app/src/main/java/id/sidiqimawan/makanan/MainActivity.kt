package id.sidiqimawan.makanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import id.sidiqimawan.makanan.ui.theme.Makanan
import id.sidiqimawan.makanan.ui.theme.MakananTheme
import id.sidiqimawan.makanan.ui.theme.makanan


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakananTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MakananApp()
                }
            }
        }
    }
}

// layout aplikasi utama
@Composable
fun MakananApp() {
    val keranjangBelanja = remember { mutableStateListOf<Makanan>() }
    Scaffold(
        topBar = {
            FoodTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Daftar makanan
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = dimensionResource(R.dimen.padding_large))
            ) {
                items(makanan) { item ->
                    MakananItem(
                        makanan = item,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                    ButtonKeranjang(makanan = item, cart = keranjangBelanja)
                }
            }
            // untuk menampilkan KartuBelanja
            Box(
                modifier = Modifier
                    .heightIn(max = dimensionResource(R.dimen.cart_max_height)) // menentukan tinggi maximal dari box
                    .verticalScroll(rememberScrollState()) // isi dari Box untuk bisa di-scroll secara vertikal.
                    .background(MaterialTheme.colorScheme.background)
            ) {
                KartuBelanja(kartu = keranjangBelanja, onRemoveItem = { item -> // onRemoveItem untuk menghapus item dari keranjang belanja dengan memanggil keranjangBelanja.remove(item).
                    keranjangBelanja.remove(item)
                })
            }
        }
    }
}


// menampilkan kartu item makanan dengan nama, gambar, dan deskripsi
@Composable
fun MakananItem(
    makanan: Makanan,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(    // untuk membungkus eluruh item dalam sebuah Card untuk memberikan tampilan yang rapi
        modifier = modifier
    ) {
        Column( // digunakan untuk menampilkan elemen secara vertikal
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row( // menampilkan elemen secara horizontal di dalam kartu, seperti ikon, nama, harga makanan, dan tombol expand/collapse.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                IconMakanan(makanan.imageResourceId)
                InformasiMakanan(makanan.name, makanan.harga)
                Spacer(Modifier.weight(1f))
                FoodItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) { //Kondisi ini memeriksa apakah expanded bernilai true. Jika true, DescriptionMakanan akan ditampilkan di bawah Row dengan deskripsi makanan.
                DescriptionMakanan(
                    makanan.description, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

//tombol untuk memperluas atau mengecilkan deskripsi makanan
@Composable
private fun FoodItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

// Tombol untuk menambah makanan ke keranjang
@Composable
fun ButtonKeranjang(makanan: Makanan, cart: MutableList<Makanan>) {
    Button(onClick = { cart.add(makanan) }) {
        Text(text = "Tambah ke Keranjang")
    }
}

// Ubah KartuBelanja untuk memperbolehkan penghapusan item
@Composable
fun KartuBelanja(kartu: List<Makanan>, onRemoveItem: (Makanan) -> Unit) {
    if (kartu.isNotEmpty()) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        ) {
            Text("Keranjang Belanja", style = MaterialTheme.typography.headlineSmall)
            kartu.forEach { makanan ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small))
                ) {
                    Text(
                        text = stringResource(id = makanan.name),
                        modifier = Modifier.weight(1f)
                    )

                    // menambahkan tombol hapus di samping setiap item
                    Text(   //Menampilkan teks "Hapus" berwarna merah
                        text = "Hapus",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .clickable { onRemoveItem(makanan) } // Call onRemoveItem to remove the item
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}


// Top app bar untuk logo dan judul
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logo_makanan),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge // font size header
                )
            }
        },
        modifier = modifier
    )
}

// menampilkan gambar icon makanan
@Composable
fun IconMakanan(
    @DrawableRes foodIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(foodIcon),
        contentDescription = null
    )
}

// menampilkan nama makanan
@Composable
fun InformasiMakanan(
    @StringRes foodName: Int,
    harga: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = foodName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text( // Tampilkan harga di bawah nama makanan
            text = "Harga: Rp.$harga",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
    }
}

// menampilkan deskripsi makanan
@Composable
fun DescriptionMakanan(
    @StringRes DescriptionMakanan: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(id = DescriptionMakanan),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Preview untuk tema terang
@Preview
@Composable
fun FoodPreview() {
    MakananTheme (darkTheme = false) {
        MakananApp()
    }
}

// Preview untuk tema gelap
@Preview
@Composable
fun FoodDarkThemePreview() {
    MakananTheme (darkTheme = true) {
        MakananApp()
    }
}
