package com.example.firebasehub.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebasehub.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginViewModel : ViewModel() {

    var registrationUiState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    private val TAG = LoginViewModel::class.simpleName

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
                signUp()
            }


        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signup")
        createUserInFirebase(
            email = registrationUiState.value._email,
            password = registrationUiState.value._password
        )
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

        if (fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status) {
            allValidationsPassed.value = true
        } else {
            allValidationsPassed.value = false
        }

    }


    private fun createUserInFirebase(email: String, password: String) {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener ")
                Log.d(TAG, "isSuccessful={${it.isSuccessful}} ")

            }
            .addOnFailureListener {
                Log.d(TAG, "inside_OnFailureListener ")
                Log.d(TAG, "Exception={${it.message}}")
                Log.d(TAG, "Exception={${it.localizedMessage}}")

            }

    }

}





