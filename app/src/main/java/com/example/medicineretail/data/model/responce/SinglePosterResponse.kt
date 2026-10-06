package com.example.medicineretail.models.responseModel

data class SinglePosterResponse(
    val success: Boolean,
    val message: String?,
    val data: Poster?
)