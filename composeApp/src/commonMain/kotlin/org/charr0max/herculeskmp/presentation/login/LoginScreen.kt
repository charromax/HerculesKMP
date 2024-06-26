package org.charr0max.herculeskmp.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {
    val viewModel = getViewModel(Unit, viewModelFactory { LoginViewModel() })
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("Login") }
    var signIn by remember { mutableStateOf(true) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var signInText by remember { mutableStateOf("Create a new account") }
    var loginError by remember { mutableStateOf(false) }
    var coroutineScope: CoroutineScope = MainScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = title,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)
        )

        OutlinedTextField(
            value = email,
            isError = emailError,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            isError = passwordError,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        AnimatedVisibility(
            visible = !signIn,
            enter = fadeIn(initialAlpha = 0.4f),
            exit = fadeOut(animationSpec = tween(250))
        ) {
            OutlinedTextField(
                value = password,
                isError = confirmPasswordError,
                onValueChange = { password = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (email.isEmpty())
                    emailError = true
                else if (password.isEmpty())
                    passwordError = true

                when(signIn) {
                    true -> {
                        emailError = viewModel.validateEmail(email)
                        if (!emailError)
                        {
                            coroutineScope.launch {
                                val authResult = viewModel.login(email, password)
                                loginError = (authResult==null)
                            }
                        }
                    }
                    false -> {
                        var error = viewModel.signUp(email,password,confirmPassword)
                        if (error == email) emailError = true
                        else if(error == password) passwordError = true
                        else if(error == confirmPassword) confirmPasswordError = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Next")
        }
        Spacer(modifier = Modifier.height(32.dp))

        if (loginError) {
            Text(text = "Login Failed")
        }
        ClickableText(
            text = AnnotatedString(signInText),
            onClick = {
                if (signIn) {
                    signIn = false
                    title = "Sign In"
                    signInText = "Log In to existing Account"
                } else {
                    signIn = true
                    title = "Login"
                    signInText = "Create a New Account"
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}