package com.example.proyectodashboard2.pages

import android.graphics.Bitmap
import android.icu.number.Scale
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectodashboard2.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.widgets.ScaleBar

val senaCba = LatLng(4.69606, -74.21563)
val tiendaCba = LatLng(4.696385, -74.214835)
val unidadLacteosCba = LatLng(4.695038, -74.215446)
val unidadAgricolaCba = LatLng(4.694168, -74.218453)
val ganaderia = LatLng(4.6915730, -74.2191036)
val gastronomia = LatLng(4.6954549, -74.2164663)
val flores = LatLng(4.6943158, -74.2170497)
val huevow = LatLng(4.6931098, -74.2181467)

@Composable
fun Page_Principal() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, bottom = 100.dp)
        .verticalScroll(
            rememberScrollState()
        ),verticalArrangement = Arrangement.Center) {
        Text(text = "Quienes Somos?", style = TextStyle(fontWeight = FontWeight.Light, fontSize = 25.sp))
        QuienesSomos()
        Text(text = "Direccion", style = TextStyle(fontWeight = FontWeight.Light, fontSize = 25.sp), modifier = Modifier.padding(top = 15.dp, bottom = 10.dp))
        mapa()
        MisionVision()
    }
}

@Composable
fun QuienesSomos(){
    Box(Modifier.padding(start = 30.dp)){
        Image(painter = painterResource(id = R.drawable.ic_principal_web), contentDescription = null)
        Column(horizontalAlignment = Alignment.End) {
            Card(shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .alpha(0.8f)
                    .width(260.dp)
                    .padding(top = 135.dp)

            ) {
                Text(text = "Somos un establecimiento público del orden Nacional y con autonomía administrativa, adscrito al Ministerio del Trabajo.")
            }
        }

    }
}

@Composable
fun mapa(){
    val defaultCameraPosition = CameraPosition.fromLatLngZoom(senaCba, 15f)
    val cameraPositionState = rememberCameraPositionState{
        position = defaultCameraPosition
    }

    var isMapLoaded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)
            .padding(bottom = 80.dp)
    ) {
        GoogleMapView(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            }
        )
        if(!isMapLoaded){
            AnimatedVisibility(
                modifier = Modifier.matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .wrapContentSize()
                )
            }
        }
    }
}
@Composable
fun GoogleMapView(
    modifier: Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val senaCbaState = rememberMarkerState(position = senaCba)
    val tiendaCbaState = rememberMarkerState(position = tiendaCba)
    val unidadLacteosCbaState = rememberMarkerState(position = unidadLacteosCba)
    val unidadAgricolaCbaState = rememberMarkerState(position = unidadAgricolaCba)
    val ganaderiaState = rememberMarkerState(position = ganaderia)
    val huevosState = rememberMarkerState(position = huevow)
    val floresState = rememberMarkerState(position = flores)
    val gastronomiaState = rememberMarkerState(position = gastronomia)

    Box(modifier = Modifier
        .fillMaxSize()){
        GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState,
            onMapLoaded = onMapLoaded
        ){
            Marker(
                state = unidadAgricolaCbaState,
                title = "Unidad de Produccion Agricola CBA",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            )
            MarkerInfoWindowContent(
                state = unidadLacteosCbaState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ){
                Text(text = "Unidad de Produccin Lacteos CBA", color = Color.Black)
            }
            MarkerInfoWindow(
                state = tiendaCbaState,
            ){
                Box(modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(35.dp,35.dp,35.dp,35.dp)
                    )){
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.bg_tienda_cba), contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth())
                        Spacer(modifier = Modifier
                            .padding(24.dp)
                        )
                        Text(
                            text = "Tienda Sena CBA",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            MarkerInfoWindow(
                state = senaCbaState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ){
                Box(){
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.sena), contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth())
                        Spacer(modifier = Modifier
                            .padding(24.dp)
                        )
                        Text(
                            text = "SENA",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            MarkerInfoWindow(
                state = ganaderiaState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ){
                Box(modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(35.dp,35.dp,35.dp,35.dp)
                    )){
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.ganaderiasite), contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth())
                        Spacer(modifier = Modifier
                            .padding(24.dp)
                        )
                        Text(
                            text = "Ganaderia",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            MarkerInfoWindow(
                state = floresState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ){
                Box(modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(35.dp,35.dp,35.dp,35.dp)
                    )){
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.floresite), contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth())
                        Spacer(modifier = Modifier
                            .padding(24.dp)
                        )
                        Text(
                            text = "Cultivos y flores",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            MarkerInfoWindow(
                state = gastronomiaState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ){
                Box(modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(35.dp,35.dp,35.dp,35.dp)
                    )){
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.gastronomysite), contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth())
                        Spacer(modifier = Modifier
                            .padding(24.dp)
                        )
                        Text(
                            text = "Gastronomia",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }


            content()
        }
        ScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopEnd),
            cameraPositionState = cameraPositionState
        )

    }
}

@Composable
fun MisionVision(){
    Column(horizontalAlignment = Alignment.CenterHorizontally ,modifier = Modifier.fillMaxWidth()) {

            Text(text = "Mision", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp), modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
            Card(backgroundColor = colorResource(id = R.color.greyLight) ,modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)) {
                Text(text = "El SENA está  encargado de cumplir la función que le corresponde al Estado de invertir en el desarrollo social y técnico de los trabajadores colombianos, ofreciendo y ejecutando la formación profesional integral, para la incorporación y el desarrollo de las personas en actividades productivas que contribuyan al desarrollo social, económico y tecnológico del país (Ley 119/1994)", modifier = Modifier.padding(10.dp))
            }


            Text(text = "Vision", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp),  modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
            Card(backgroundColor = colorResource(id = R.color.greenLight) ,modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)) {
                Text(color = colorResource(id = R.color.white) ,text = "El SENA será una organización de conocimiento para todos los colombianos, innovando permanentemente en sus estrategias y metodologías de aprendizaje, en total acuerdo con las tendencias y cambios tecnológicos y las necesidades del sector empresarial y de los trabajadores, impactando positivamente la productividad, la competitividad, la equidad y el desarrollo del país", modifier = Modifier.padding(10.dp))

        }
    }
}