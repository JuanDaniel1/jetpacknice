package com.example.proyectodashboard2.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.proyectodashboard2.R

@Composable
fun Page_Contenidos() {
    grids()
}



data class secciones(
    @StringRes val titulo:Int,
    @StringRes val texto:Int,
    @StringRes val textolargo:Int
)

private val listado = listOf(
    secciones(R.string.titulo1, R.string.texto1, R.string.textoL1),
    secciones(R.string.titulo2, R.string.texto2, R.string.textoL2),
    secciones(R.string.titulo3, R.string.texto3, R.string.textoL3),
    secciones(R.string.titulo4, R.string.texto4, R.string.textoL4)
)

@Composable
fun card(
    @StringRes titulop:Int,
    @StringRes textop:Int,
    @StringRes textolargop:Int
){
    var expanded by rememberSaveable{ mutableStateOf(false) }
    Card(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)) {
            Column(modifier = Modifier.width(300.dp)) {
                Text(text = stringResource(id = titulop), style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold, color = colorResource(
                    id = R.color.white
                )), modifier = Modifier.padding(bottom =5.dp))
                Text(text = stringResource(id = textop), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = colorResource(
                    id = R.color.white
                )), modifier = Modifier.padding(bottom =5.dp))
                if (expanded == true){
                    Text(text = stringResource(id = textolargop), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light, color = colorResource(
                        id = R.color.white
                    )), modifier = Modifier.padding(bottom =5.dp))
                }
            }
            IconButton(onClick = { expanded = !expanded}) {
                if (expanded == true){
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null, tint = Color.White)
                }
                else{
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null, tint = Color.White)
                }

            }
        }
    }



}

@Composable
fun grids(){
    LazyColumn(Modifier.padding(bottom = 50.dp)){
        items(listado){
            item ->
            card(titulop = item.titulo, textop = item.texto, textolargop = item.textolargo)
        }
    }
}