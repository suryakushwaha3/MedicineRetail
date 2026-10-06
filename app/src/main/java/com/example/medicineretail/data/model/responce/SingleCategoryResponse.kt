package com.example.medicineretail.models.responseModel

data class SingleCategoryResponse(
    val success: Boolean,
    val message: String?,
    val data: Category?
)