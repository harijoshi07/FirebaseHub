package com.example.firebasehub.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebasehub.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {

        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    _email = event.email
                )

            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    _password = event.password
                )

            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()

            }
        }

        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value._email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value._password
        )

        loginUIState.value = loginUIState.value.copy(
            _emailError = emailResult.status,
            _passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {

        loginInProgress.value = true

        val email = loginUIState.value._email
        val password = loginUIState.value._password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_login success")
                Log.d(TAG, "${it.isSuccessful}")

                loginInProgress.value = false

            }
            .addOnFailureListener {
                loginInProgress.value = false

                Log.d(TAG, "Inside_Login failure")
                Log.d(TAG, "${it.localizedMessage}")

            }
    }


}