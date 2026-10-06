package com.example.medicineretail.data.model


data class Order(
    val id: Int? = null,
    val order_id: String? = null,
    val user_id: String? = null,
    val product_id: String? = null,
    val isApproved: Boolean? = null,
    val quantity: Int? = null,
    val date_of_order_creation: String? = null,
    val price: Double? = null,
    val total_amount: Double? = null,
    val product_name: String? = null,
    val user_name: String? = null,
    val message: String? = null,
    val category: String? = null
)
