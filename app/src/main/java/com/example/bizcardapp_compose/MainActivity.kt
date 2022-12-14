package com.example.bizcardapp_compose

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcardapp_compose.ui.theme.BizCardApp_ComposeTheme
import com.example.bizcardapp_compose.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardApp_ComposeTheme() {
                androidx.compose.material.Surface(color=MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){

    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Card(
            Modifier
                .width(200.dp)
                .height(400.dp)
                .padding(15.dp),
        shape = RoundedCornerShape(corner =CornerSize(15.dp)),
        elevation = 4.dp)
         {  Column(modifier = Modifier.height(300.dp),
         verticalArrangement = Arrangement.Top,
         horizontalAlignment = Alignment.CenterHorizontally) {
             CreateProfileImage()
             Divider(Modifier.padding(top = 10.dp), thickness = 5.dp)
             CardInfoSection()
             Button(onClick = {
                 buttonClickedState.value = !buttonClickedState.value
             }) {
                 Text(text = "Portfolio", style = MaterialTheme.typography.button)
             }
             if(buttonClickedState.value){
                 PortfolioContent()
             }else{
                 Box( ){}
             }
         }

        }

    }
}

@Preview
@Composable
private fun PortfolioContent(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(3.dp),
            shape = RoundedCornerShape(CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(data = listOf<String>("Adopem App", "Work-in-Out App", "Audify Music App"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
      items(data){
          item -> 
          Card(modifier = Modifier
              .fillMaxWidth()
              .padding(13.dp),
          shape = RectangleShape,
          elevation = 4.dp) {
            Row(
                Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)) {

                CreateProfileImage(Modifier.size(100.dp))
                
                Column(modifier = Modifier
                    .padding(7.dp)
                    .align(Alignment.CenterVertically)) {
                    Text(text = item, fontWeight = FontWeight.Bold)
                    Text(text = "A test app", fontStyle = FontStyle.Italic)
                }
            }
          }
      }

       }
    }


@Composable
private fun CardInfoSection() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Dhruv Mehta",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primaryVariant
        )

        Text(
            text = "Android Developer Intern",
            modifier = Modifier.padding(3.dp)
        )


        Text(
            text = "dhruv1592001@gmail.com",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(3.dp)
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.surface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile_image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}


//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardApp_ComposeTheme {
            CreateBizCard()
    }
}