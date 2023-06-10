package com.example.proyectodashboard2.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectodashboard2.R

@Composable
fun Page_Ver_Mas() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(
            rememberScrollState()
        ),verticalArrangement = Arrangement.Center) {
        opiniones()
    }
}
@Preview(showBackground = true)
@Composable
fun opiniones(){
    Column() {
        Text(text = "Opiniones", style = TextStyle(fontSize = 30.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold), modifier = Modifier.padding(vertical = 15.dp))
            Row() {
                Image(painter = painterResource(id = R.drawable.persona1), contentDescription = null, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(100.dp))
                Text(text = stringResource(id = R.string.nombre1), modifier = Modifier.padding(top = 30.dp, start = 15.dp))
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
            }
        Card() {
            Text(stringResource(id = R.string.opinion1))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Image(painter = painterResource(id = R.drawable.persona2), contentDescription = null, modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(100.dp))
            Text(text = stringResource(id = R.string.nombre2), modifier = Modifier.padding(top = 30.dp, start = 15.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.padding(top = 28.dp))
        }
        Card() {
            Text(stringResource(id = R.string.opinion2))
        }
    }


}