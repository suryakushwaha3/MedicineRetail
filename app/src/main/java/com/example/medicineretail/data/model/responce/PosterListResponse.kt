package com.example.medicineretail.models.responseModel

data class PosterListResponse(
    val success: Boolean,
    val data: List<Poster>?
)