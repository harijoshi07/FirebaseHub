package com.example.firebasehub.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebasehub.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class SignupViewModel : ViewModel() {
    private val TAG = SignupViewModel::class.simpleName

    var signupUIState = mutableStateOf(SignupUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress= mutableStateOf(false)


    fun onEvent(event: SignupUIEvent) {


        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    _firstName = event.firstName
                )
            }

            is SignupUIEvent.LastNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    _lastName = event.lastName
                )
            }

            is SignupUIEvent.EmailChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    _email = event.email
                )
            }

            is SignupUIEvent.PasswordChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    _password = event.password
                )
            }

            is SignupUIEvent.RegistrationButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckboxClicked -> {
                signupUIState.value = signupUIState.value.copy(
                    _privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signup")
        createUserInFirebase(
            email = signupUIState.value._email,
            password = signupUIState.value._password
        )
    }

    private fun validateDataWithRules() {

        val fNameResult = Validator.validateFirstName(
            fName = signupUIState.value._firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = signupUIState.value._lastName
        )

        val emailResult = Validator.validateEmail(
            email = signupUIState.value._email
        )

        val passwordResult = Validator.validatePassword(
            password = signupUIState.value._password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = signupUIState.value._privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules: ")
        Log.d(TAG, "fNameResult=$fNameResult")
        Log.d(TAG, "lNameResult=$lNameResult")
        Log.d(TAG, "emailResult=$emailResult")
        Log.d(TAG, "passwordResult=$passwordResult")
        Log.d(TAG, "privacyPolicyResult=$privacyPolicyResult")



        signupUIState.value = signupUIState.value.copy(
            _firstNameError = fNameResult.status,
            _lastNameError = lNameResult.status,
            _emailError = emailResult.status,
            _passwordError = passwordResult.status,
            _privacyError = privacyPolicyResult.status
        )

        if (fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status) {
            allValidationsPassed.value = true
        } else {
            allValidationsPassed.value = false
        }

    }


    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value=true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener ")
                Log.d(TAG, "isSuccessful={${it.isSuccessful}} ")

                signUpInProgress.value=false

                if (it.isSuccessful) {

                }

            }
            .addOnFailureListener {
                Log.d(TAG, "inside_OnFailureListener ")
                Log.d(TAG, "Exception={${it.message}}")
                Log.d(TAG, "Exception={${it.localizedMessage}}")

            }

    }

}





