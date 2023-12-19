package com.example.firebasehub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebasehub.R
import com.example.firebasehub.components.ButtonComponent
import com.example.firebasehub.components.CheckBoxComponent
import com.example.firebasehub.components.ClickableTextComponent
import com.example.firebasehub.components.DividerComponent
import com.example.firebasehub.components.NormalTextComponent
import com.example.firebasehub.components.PasswordTextFieldComponent
import com.example.firebasehub.components.TextFieldComponent

@Composable
fun SignupScreen() {
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
                imageVector = Icons.Outlined.Person
            )
            TextFieldComponent(
                labelValue = stringResource(R.string.last_name),
                imageVector = Icons.Outlined.Person
            )

            TextFieldComponent(
                labelValue = stringResource(R.string.email),
                imageVector = Icons.Outlined.Email
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(R.string.password),
                imageVector = Icons.Outlined.Lock
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(R.string.confirm_password),
                imageVector = Icons.Outlined.Lock
            )

            CheckBoxComponent(value = stringResource(R.string.termsNconditions))

            Spacer(modifier = Modifier.weight(1f))

            ButtonComponent(value = stringResource(R.string.register))

            DividerComponent()
            ClickableTextComponent(onTextSelected = {

            })
            Spacer(modifier = Modifier.height(20.dp))


        }

    }

}

@Preview
@Composable
fun PreviewSignupScreen() {
    SignupScreen()
}