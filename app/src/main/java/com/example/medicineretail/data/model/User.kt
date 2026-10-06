 package com.example.medicineretail.model

data class User(
    val id: Int? = null,
    val user_id: String? = null,
//    val password: String? = null,
    val date_of_account_creation: String? = null,
    val isApproved: Boolean? = null,
    val block: Boolean? = null,
    val name: String? = null,
    val address: String? = null,
    val email: String? = null,
    val phone_number: String? = null,
    val pincode: String? = null
)
