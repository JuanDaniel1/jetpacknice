package com.example.proyectodashboard2.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectodashboard2.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@Preview
@Composable
fun Page_Inicio() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Carrousel()

            GridsProd()
        
    }

}
@OptIn(ExperimentalPagerApi::class, ExperimentalTime::class)
@Composable
fun Carrousel(autoScrollInterval: Duration = 3.seconds){

    val pagerState = rememberPagerState(initialPage = 1)
    val slider = listOf(
        R.drawable.pinchoscarr,
        R.drawable.vegetablescarr,
        R.drawable.berriescarr,
        R.drawable.pastacarr,
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(autoScrollInterval.inWholeMilliseconds)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % slider.size)
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Bienvenido a Tienda Sena", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp), modifier = Modifier.padding(top = 10.dp))
        HorizontalPager(
            count = slider.size,
            state = pagerState,
            modifier = Modifier
                .height(250.dp)
        ) {
            page ->
            Card(shape = RoundedCornerShape(20.dp), modifier = Modifier
                .height(200.dp)
                .padding(horizontal = 15.dp)) {
                Image(painter = painterResource(id = slider[page]), contentDescription = null,
                    Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }
        }
    }
}
private data class Pairs(
    @DrawableRes val imagen:Int,
    @StringRes val nombre:Int
        )

private val ListaProductos = listOf(
        R.drawable.milk to R.string.leche,
        R.drawable.queso to R.string.queso,
        R.drawable.yogurt to R.string.yogurt,
        R.drawable.brocoli to R.string.brocoli,
        R.drawable.cereza to R.string.cereza,
        R.drawable.fresa to R.string.fresa,
        R.drawable.manzana to R.string.manzana,
        R.drawable.pera to R.string.pera,



    ).map { Pairs(it.first, it.second) }

@Composable
fun cardprod(
    @DrawableRes drawable:Int,
    @StringRes text:Int,
){
    Card( shape = RoundedCornerShape(10.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = drawable), contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp), contentScale = ContentScale.Crop)
            Text(text = stringResource(id = text), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun GridsProd(){
    Text(text = "Productos Populares", style = TextStyle(fontWeight = FontWeight.Light, fontSize = 25.sp), modifier = Modifier.padding(bottom = 15.dp))
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.height(400.dp).padding(bottom = 25.dp).fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(ListaProductos){
            item -> cardprod(drawable = item.imagen, text = item.nombre)
        }
    }
}
