package com.example.medicineretail.models.responseModel

import com.example.medicineretail.data.model.Order

data class OrderResponse(
    val success: Boolean,
    val data: List<Order>
)