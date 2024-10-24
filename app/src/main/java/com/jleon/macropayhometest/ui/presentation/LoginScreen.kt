package com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.jleon.macropayhometest.ui.theme.SelectedField
import com.jleon.macropayhometest.ui.theme.UnselectedField
import com.jleon.macropayhometest.ui.theme.pokedexBlue
import com.jleon.macropayhometest.ui.theme.pokedexRed

@Composable
fun LoginScreen(auth: FirebaseAuth, navigateToPokemonList:() -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pokedexRed)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val focusManager = LocalFocusManager.current

        Text("Pokedex", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        Spacer(Modifier.height(48.dp))
        Text("Email", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Password", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        TextField(
            value = password, onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    signIn(auth, email, password, navigateToPokemonList)
                }
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Button(
            onClick = {
                signIn(auth, email, password, navigateToPokemonList)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = pokedexBlue
            )
        ) {
            Text(text = "Login",)
        }
    }
}
fun signIn(auth: FirebaseAuth, email: String, password: String, navigateToPokemonList: () -> Unit){
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
        if(task.isSuccessful){
            navigateToPokemonList()
            Log.i("MacroTest", "LOGIN OK")
        }else{
            //Error
            Log.i("MacroTest", "LOGIN KO")
        }
    }
}