package com.example.medicineretail.repository

import com.example.medicineretail.data.remote.ApiServices
import com.example.medicineretail.models.responseModel.PosterListResponse
import com.example.medicineretail.models.responseModel.SinglePosterResponse
import retrofit2.Response
import javax.inject.Inject

class PosterRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun getAllPosters(): Response<PosterListResponse> {
        return apiServices.getAllPosters()
    }

    suspend fun getSpecificPoster(
        posterId: Int
    ): Response<SinglePosterResponse> {
        return apiServices.getSpecificPoster(posterId)
    }
}