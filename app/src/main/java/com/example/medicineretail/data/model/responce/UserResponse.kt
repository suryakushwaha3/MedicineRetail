package com.example.medicineretail.models.responseModel

data class UserResponse(
    val success: Boolean,
    val message: String?,
    val user: UserData?
)

data class UserData(
    val id: Int?,
    val user_id: String?,
    val name: String?,
    val address: String?,
    val email: String?,
    val phone_number: String?,
    val pincode: String?,
    val date_of_account_creation: String?,
    val isApproved: Boolean?,
    val block: Boolean?
)