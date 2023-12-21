package com.example.firebasehub.data

data class SignupUIState(
    var _firstName: String = "",
    var _lastName: String = "",
    var _email: String = "",
    var _password: String = "",
    var _privacyPolicyAccepted: Boolean=false,

    var _firstNameError: Boolean = false,
    var _lastNameError: Boolean = false,
    var _emailError: Boolean = false,
    var _passwordError: Boolean = false,
    var _privacyError: Boolean=false
)