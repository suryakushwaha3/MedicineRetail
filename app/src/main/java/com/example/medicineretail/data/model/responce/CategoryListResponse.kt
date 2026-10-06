package com.example.medicineretail.models.responseModel

data class CategoryListResponse(
    val success: Boolean,
    val data: List<Category>?
)