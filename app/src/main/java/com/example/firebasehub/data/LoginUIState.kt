package com.example.firebasehub.data

data class LoginUIState (
    var _email:String="",
    var _password:String="",

    var _emailError:Boolean=false,
    var _passwordError:Boolean=false,
)