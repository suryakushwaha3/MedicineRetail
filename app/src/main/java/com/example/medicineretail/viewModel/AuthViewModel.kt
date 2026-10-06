package com.example.medicineretail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicineretail.Utils.ResultState
import com.example.medicineretail.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    private val _signupSuccess = MutableStateFlow(false)
    val signupSuccess: StateFlow<Boolean> = _signupSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error



    // Login


    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null
            _loginSuccess.value = false

            when (
                val result = repository.login(
                    email = email,
                    password = password
                )
            ) {

                is ResultState.Success -> {

                    _loginSuccess.value = true
                }

                is ResultState.Error -> {

                    _error.value = result.message
                }

                is ResultState.Loading -> {

                }
            }

            _isLoading.value = false
        }
    }



    // Signup

    fun signup(
        name: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        pincode: String
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null
            _signupSuccess.value = false

            when (
                val result = repository.signup(
                    name = name,
                    email = email,
                    password = password,
                    address = address,
                    phoneNumber = phoneNumber,
                    pincode = pincode
                )
            ) {

                is ResultState.Success -> {

                    println("SIGNUP BODY: ${result.data}")

                    _signupSuccess.value = true
                }

                is ResultState.Error -> {

                    println("SIGNUP ERROR: ${result.message}")

                    _error.value = result.message
                }

                is ResultState.Loading -> {

                }
            }

            _isLoading.value = false
        }
    }
}