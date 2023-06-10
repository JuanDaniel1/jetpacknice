package com.example.jetpackcompose2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.provider.Telephony.MmsSms.PendingMessages
import android.provider.Telephony.Sms.Conversations
import android.renderscript.Sampler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.jetpackcompose2.ui.theme.JetpackCompose2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose2Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(SampleData.conversationSample)
                }
            }
           
        }
    }
}
object  SampleData{
    val conversationSample = listOf(
        Message(
            "Daniel",
            "Hola amigo, Como estas :)",
            "Hace tiempo que no charlamos, ojala nos volvamos a ver"
        ),
        Message(
            "Daniel",
            "He estado muy ocupado ultimamente",
            "Incluso los dias pasan muy rapido"
        ),
        Message(
            "Daniel",
            "Ya casi se siente que se termina el año",
            "Y volveremos a preparar esos deliciosos tamales"
        ),
        Message(
            "Daniel",
            "Tambien note que esta John Wick en cine",
            "En cualquier momento cuadramos y vamos"
        ),
        Message(
            "Daniel",
            "Vamos la proxima vez a hacer algo",
            "Como en los viejos tiempos"
        ),
        Message(
            "Daniel",
            "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto.",
            "Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen."
        ),
        Message(
            "Daniel",
            "No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original.",
            "Fue popularizado en los 60s con la creación de las hojas Letraset, las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum."
        ),
        Message(
            "Daniel",
            "Es un hecho establecido hace demasiado tiempo que un lector se distraerá con el contenido del texto de un sitio mientras que mira su diseño.",
            "El punto de usar Lorem Ipsum es que tiene una distribución más o menos normal de las letras, al contrario de usar textos como por ejemplo Contenido aquí, contenido aquí. Estos textos hacen parecerlo un español que se puede leer."
        ),


    )
}
data class  Message(val author: String, val body: String, val complete: String)
@SuppressLint("InvalidColorHexValue")
@Composable
fun MessageCard(msg: com.example.jetpackcompose2.Message){
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(painter = painterResource(R.mipmap.profile_picture_foreground), contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

            )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,)


            Text(text = msg.author, color = MaterialTheme.colors.secondaryVariant, style = MaterialTheme.typography.subtitle2)

        Spacer(modifier = Modifier.padding(all = 4.dp))
        Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp,
        color = surfaceColor, modifier = Modifier
                .animateContentSize()
                .padding(1.dp)) {
            Column {
                Column (modifier = Modifier.clickable { isExpanded = !isExpanded }){
                    Text(text = msg.body, modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = TextStyle(color = if (isExpanded){
                            Color(0xFFFFFFFF)
                        } else {
                            Color(0xFF000000)
                        }))
                }

                if(isExpanded){
                    Text(text = msg.complete, style = TextStyle(color = Color(0xFFFFFFFFFF)))
                }
            }

        }
    }
    
}
@Preview(name = "Light Mode")
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard(){
    JetpackCompose2Theme {
        Surface {
            MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!", "nobody knows"))
        }
    }
}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}
@Preview
@Composable
fun PreviewConversation() {
    JetpackCompose2Theme() {
        Conversation(SampleData.conversationSample)
    }
}





