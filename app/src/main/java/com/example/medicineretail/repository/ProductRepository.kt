package com.example.medicineretail.repository

import com.example.medicineretail.data.remote.ApiServices
import com.example.medicineretail.models.responseModel.ProductResponse
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun getAllProducts(): Response<List<ProductResponse>> {
        return apiServices.getAllProducts()
    }
}