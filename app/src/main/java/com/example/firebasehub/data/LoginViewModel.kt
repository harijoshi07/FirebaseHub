package com.example.firebasehub.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var registrationUiState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvent) {

        when (event) {
            is UIEvent.FirstNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    _firstName = event.firstName
                )
            }

            is UIEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    _lastName = event.lastName
                )
            }

            is UIEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    _email = event.email
                )
            }

            is UIEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    _password = event.password
                )
            }

        }


    }

    private fun printState(){
        //Log.d("")
    }
}