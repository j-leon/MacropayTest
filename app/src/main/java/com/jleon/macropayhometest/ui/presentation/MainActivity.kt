package com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.jleon.macropayhometest.NavWrapper
import com.jleon.macropayhometest.R
import com.jleon.macropayhometest.ui.theme.MacropayHomeTestTheme
import com.jleon.macropayhometest.ui.theme.pokedexRed

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        setContent {
            navHostController = rememberNavController()
            MacropayHomeTestTheme {
                Scaffold(modifier = Modifier.safeDrawingPadding()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        NavWrapper(navHostController, auth)
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(title: String, navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(pokedexRed)
        .height(48.dp)
        .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if(navController.currentBackStackEntry?.destination?.route == "pokemonDetails") {
            Image(
                modifier = Modifier.clickable { navController.popBackStack() },
                painter = painterResource(id = R.drawable.ic_back_24dp),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
        Text(text = title, textAlign = TextAlign.Center, color = Color.White, fontSize = 20.sp)
        TextButton(onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate("login"){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }) {
            Text("Log Out", color = Color.White, fontSize = 20.sp, )
        }
    }
}
