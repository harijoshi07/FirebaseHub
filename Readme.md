## This repo is a learning curve for Firebase and its features.

# State, Events and Viewmodel:

```
class LoginViewModel: ViewModel() {

    var registrationUiState= mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvent){

        when(event){
            is UIEvent.FirstNameChanged->{
                registrationUiState.value=registrationUiState.value.copy(
                    _firstName = event.firstName
                )
            }

        }


    }

}

data class RegistrationUIState(
var _firstName: String = "",
var _lastName: String = "",
var _email: String = "",
var _password: String = "",
)

sealed class UIEvent {
data class FirstNameChanged( val firstName:String): UIEvent()
data class LastNameChanged( val lastName:String): UIEvent()
data class EmailChanged( val email:String): UIEvent()
data class PasswordChanged(val password:String): UIEvent()
}
```

# Code Flow

1. User Action: FirstNameChanged Event
The user interacts with the UI, presumably by entering or changing the first name in a form field.

2. UIEvent: FirstNameChanged
This user action triggers the creation of a UIEvent.FirstNameChanged instance with the new first name as its parameter.

3. ViewModel: onEvent Function
The onEvent function in the LoginViewModel receives the FirstNameChanged event.

4. ViewModel Update: registrationUiState
The onEvent function updates the registrationUiState property in the LoginViewModel.
It creates a new instance of RegistrationUIState using the copy function, updating only the _firstName field with the new first name.

5. UI Observers (e.g., UI Components)
Components observing the registrationUiState property get notified of the change in state.

6. UI Update:
The UI components that are bound to the registrationUiState property update their display to reflect the new state. For example, if there's a UI element displaying the first name, it will now show the updated value.
