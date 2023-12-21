package com.example.firebasehub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebasehub.R
import com.example.firebasehub.components.ButtonComponent
import com.example.firebasehub.components.ClickableTextComponent
import com.example.firebasehub.components.DividerComponent
import com.example.firebasehub.components.NormalTextComponent
import com.example.firebasehub.components.PasswordTextFieldComponent
import com.example.firebasehub.components.TextFieldComponent
import com.example.firebasehub.components.UnderlinedTextComponent
import com.example.firebasehub.data.LoginUIEvent
import com.example.firebasehub.data.LoginViewModel
import com.example.firebasehub.navigation.Screen
import com.example.firebasehub.ui.theme.Primary

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NormalTextComponent(value = "Hey there", fontSize = 24.sp)
                NormalTextComponent(
                    value = "Welcome Back",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W600,
                    fontColor = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldComponent(
                    labelValue = "Email",
                    imageVector = Icons.Outlined.Email,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value._emailError
                )
                PasswordTextFieldComponent(
                    labelValue = "Password",
                    imageVector = Icons.Outlined.Lock,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value._passwordError

                )
                Spacer(modifier = Modifier.height(20.dp))
                UnderlinedTextComponent(value = "Forgot your Password?")

                Spacer(modifier = Modifier.weight(1f))
                //Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(
                    value = stringResource(R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                DividerComponent()
                ClickableTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate(Screen.SignupScreen.route)
                })

            }

        }

        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()

        }
    }


}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}