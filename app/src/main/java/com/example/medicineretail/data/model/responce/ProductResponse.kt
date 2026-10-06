package com.example.medicineretail.models.responseModel

data class ProductResponse(
    val id: Int,
    val product_id: String,
    val product_name: String,
    val category: String,
    val stock: String,
    val product_image: String?
)