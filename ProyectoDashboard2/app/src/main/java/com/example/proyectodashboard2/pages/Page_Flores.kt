package com.example.proyectodashboard2.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyectodashboard2.R
import com.example.proyectodashboard2.components.CreateChannelNotification
import com.example.proyectodashboard2.components.NotificacionProgramada
import com.example.proyectodashboard2.components.notificacionSencilla

@Composable
fun Page_Flores() {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 10.dp)) {
        GridFlores()
    }
}

@Composable
fun cardFlor(
    @DrawableRes imagen:Int,
    @StringRes nombre:Int,
    @StringRes precio:Int,
    @StringRes desc:Int,
    @StringRes descL:Int

    ){
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = "CanalTienda"
    val name = "CanalTienda"
    val descriptionText = "Canal de Notificaciones"

    val textShort: String = "Felicidades! Has comprado un producto en tienda CBA"
    val textLong: String = "Saludos! Esta es una prueba de notificaciones. Debe aparecer "+
            "un icono a la derecha y el texto puede tener una longitud de 200 caracteres. "+
            "El tamaño de la notificacion puede colapsar y/o expandirse." +
            "Gracias y hasta pronto"
    val iconoBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.correo
    )
    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda_cba
    )

    LaunchedEffect(Unit){
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    var showdialog by rememberSaveable{
        mutableStateOf(false)
    }
    Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.clickable { showdialog = true }) {
        Column() {
            Image(painter = painterResource(id = imagen), contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .width(500.dp)
                    .height(200.dp)
            )
            Text(
                stringResource(id = desc), style = TextStyle(fontStyle = FontStyle.Italic), fontSize = 10.sp, color = colorResource(
                id = R.color.grey
            ))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = nombre), style = TextStyle(fontWeight = FontWeight.Black, fontSize = 15.sp), modifier = Modifier.padding(top = 15.dp))
                Text(text = stringResource(id = precio), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp), modifier = Modifier.padding(top = 15.dp))
            }
            Row(horizontalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { showdialog = true }, modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(text = "Detalles")
                }
                IconButton(onClick = { notificacionSencilla(
                    context,
                    idChannel,
                    idNotification,
                    "Producto Comprado",
                    textShort
                ) }) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
            if (showdialog == true){
                Dialog(
                    onDismissRequest = {showdialog = false},


                ) {
                    Card(shape = RoundedCornerShape(15.dp)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = nombre),
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.padding(10.dp)
                            )
                            Image(
                                painter = painterResource(id = imagen),
                                contentDescription = null,
                                modifier = Modifier.size(300.dp)
                            )
                            Text(stringResource(id = descL), style = TextStyle(fontSize = 18.sp), modifier = Modifier.padding(horizontal = 10.dp))
                                Text(text = "Precio: ", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
                                Text(
                                    text = stringResource(id = precio),
                                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.ExtraBold),
                                    modifier = Modifier.padding(16.dp)
                                )
                            Button(onClick = { notificacionSencilla(
                                context,
                                idChannel,
                                idNotification,
                                "Producto Comprado",
                                textShort
                            )
                                showdialog = false
                            }, Modifier.fillMaxWidth()) {
                                Text(text = "Comprar")
                            }


                        }


                    }



                }
            }



        }

    }
}
data class flordata(
    @DrawableRes val drawable:Int,
    @StringRes val name:Int,
    @StringRes val price:Int,
    @StringRes val desc:Int,
    @StringRes val desl:Int
)
private val ListaFlores = listOf(
    flordata(R.drawable.dienteleon, R.string.dienteleon, R.string.precio_dienteleon, R.string.desc_dienteleon, R.string.descl_dienteleon),
    flordata(R.drawable.girasol, R.string.girasol, R.string.precio_girasol, R.string.desc_girasol, R.string.descl_girasol),
    flordata(R.drawable.lavanda, R.string.lavanda, R.string.precio_lavanda, R.string.desc_lavanda, R.string.descl_lavanda),
    flordata(R.drawable.margarita, R.string.margarita, R.string.precio_margarita, R.string.desc_margarita, R.string.descl_margarita),
    flordata(R.drawable.orquidea, R.string.orquidea, R.string.precio_orquidea, R.string.desc_orquidea, R.string.descl_orquidea),
    flordata(R.drawable.rosa, R.string.rosa, R.string.precio_rosa, R.string.desc_rosa, R.string.descl_rosa),
    flordata(R.drawable.tulipanes, R.string.tulipanes, R.string.precio_tulipanes, R.string.desc_tulipanes, R.string.descl_tulipanes)
)

@Composable
fun GridFlores(){
    Text(text = "Flores", style = TextStyle(fontWeight = FontWeight.Light, fontSize = 25.sp), modifier = Modifier.padding(top = 10.dp, bottom = 15.dp))
    LazyVerticalGrid(columns = GridCells.Fixed(2),modifier = Modifier
        .height(800.dp)
        .padding(bottom = 50.dp),horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),){
        items(ListaFlores){
            item ->
            cardFlor(imagen = item.drawable, nombre = item.name, precio = item.price, desc = item.desc, descL = item.desl)
        }
    }
}