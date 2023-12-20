package com.example.firebasehub.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebasehub.data.rules.Validator

class LoginViewModel : ViewModel() {

    var registrationUiState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvent) {

        validateDataWithRules()


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

            is UIEvent.RegistrationButtonClicked -> {
                fun signUp() {

                    validateDataWithRules()

                }
            }

        }


    }

    private fun validateDataWithRules() {

        val fNameResult = Validator.validateFirstName(
            fName = registrationUiState.value._firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUiState.value._lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUiState.value._email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUiState.value._password
        )

        registrationUiState.value = registrationUiState.value.copy(
            _firstNameError = fNameResult.status,
            _lastNameError = lNameResult.status,
            _emailError = emailResult.status,
            _passwordError = passwordResult.status
        )

    }

    private fun printState() {
        //Log.d("")
    }
}