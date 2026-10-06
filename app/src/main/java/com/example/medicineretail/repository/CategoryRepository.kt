package com.example.medicineretail.repository

import com.example.medicineretail.data.remote.ApiServices
import com.example.medicineretail.models.responseModel.CategoryListResponse
import com.example.medicineretail.models.responseModel.SingleCategoryResponse
import retrofit2.Response
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun getAllCategories(): Response<CategoryListResponse> {
        return apiServices.getAllCategories()
    }

    suspend fun getSpecificCategory(
        categoryId: Int
    ): Response<SingleCategoryResponse> {
        return apiServices.getSpecificCategory(categoryId)
    }
}