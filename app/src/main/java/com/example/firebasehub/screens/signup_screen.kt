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
import androidx.compose.material.icons.outlined.Person
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
import com.example.firebasehub.components.CheckBoxComponent
import com.example.firebasehub.components.ClickableTextComponent
import com.example.firebasehub.components.DividerComponent
import com.example.firebasehub.components.NormalTextComponent
import com.example.firebasehub.components.PasswordTextFieldComponent
import com.example.firebasehub.components.TextFieldComponent
import com.example.firebasehub.data.SignupViewModel
import com.example.firebasehub.data.SignupUIEvent
import com.example.firebasehub.navigation.Screen

@Composable
fun SignupScreen(navController: NavController, signUpViewModel: SignupViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(
                    value = stringResource(R.string.hey_there),
                    fontSize = 24.sp
                )
                NormalTextComponent(
                    value = stringResource(R.string.create_an_account),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W600,
                    fontColor = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))

                TextFieldComponent(
                    labelValue = stringResource(R.string.first_name),
                    imageVector = Icons.Outlined.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.signupUIState.value._firstNameError

                )
                TextFieldComponent(
                    labelValue = stringResource(R.string.last_name),
                    imageVector = Icons.Outlined.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.signupUIState.value._lastNameError

                )

                TextFieldComponent(
                    labelValue = stringResource(R.string.email),
                    imageVector = Icons.Outlined.Email,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signUpViewModel.signupUIState.value._emailError

                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(R.string.password),
                    imageVector = Icons.Outlined.Lock,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.signupUIState.value._passwordError

                )

                /*PasswordTextFieldComponent(
                    labelValue = stringResource(R.string.confirm_password),
                    imageVector = Icons.Outlined.Lock,
                    onTextSelected = {
                        loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.registrationUiState.value._passwordError

                )*/

                CheckBoxComponent(
                    value = stringResource(R.string.termsNconditions),
                    onChecked = {
                        signUpViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckboxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonComponent(
                    value = stringResource(R.string.register),
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignupUIEvent.RegistrationButtonClicked)

                    },
                    isEnabled = signUpViewModel.allValidationsPassed.value
                )

                DividerComponent()
                ClickableTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate(Screen.LoginScreen.route)
                })
                Spacer(modifier = Modifier.height(20.dp))


            }

        }

        if (signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }

    }


}

@Preview
@Composable
fun PreviewSignupScreen() {
    SignupScreen(navController = rememberNavController())
}