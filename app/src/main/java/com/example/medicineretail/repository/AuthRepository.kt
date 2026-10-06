package com.example.medicineretail.repository

import com.example.medicineretail.Utils.ResultState
import com.example.medicineretail.data.remote.ApiServices
import com.example.medicineretail.data.model.responce.LoginResponse
import com.example.medicineretail.data.model.responce.SignupResponse
import org.json.JSONObject
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun login(
        email: String,
        password: String
    ): ResultState<LoginResponse> {

        return try {

            val response = apiServices.login(
                email = email,
                password = password
            )

            if (response.isSuccessful) {

                val body = response.body()

                if (body != null) {
                    ResultState.Success(body)
                } else {
                    ResultState.Error("Login response is empty")
                }

            } else {

                val errorMessage = try {
                    val errorBody = response.errorBody()?.string()

                    JSONObject(errorBody ?: "")
                        .optString("message")
                        .takeIf { it.isNotBlank() }
                        ?: "Login failed: ${response.code()}"

                } catch (e: Exception) {
                    "Login failed: ${response.code()}"
                }

                ResultState.Error(errorMessage)
            }

        } catch (e: Exception) {

            ResultState.Error(
                e.message ?: "Something went wrong"
            )
        }
    }


    suspend fun signup(
        name: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        pincode: String
    ): ResultState<SignupResponse> {

        return try {

            val response = apiServices.signup(
                name = name,
                email = email,
                password = password,
                address = address,
                phoneNumber = phoneNumber,
                pincode = pincode
            )

            if (response.isSuccessful) {

                val body = response.body()

                if (body != null) {
                    ResultState.Success(body)
                } else {
                    ResultState.Error("Signup response is empty")
                }

            } else {

                // Read Flask JSON error response
                val errorMessage = try {

                    val errorBody = response.errorBody()?.string()

                    JSONObject(errorBody ?: "")
                        .optString("message")
                        .takeIf { it.isNotBlank() }
                        ?: "Signup failed: ${response.code()}"

                } catch (e: Exception) {

                    "Signup failed: ${response.code()}"
                }

                ResultState.Error(errorMessage)
            }

        } catch (e: Exception) {

            ResultState.Error(
                e.message ?: "Something went wrong"
            )
        }
    }
}
