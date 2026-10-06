package com.example.medicineretail.data.remote

import com.example.medicineretail.data.model.responce.LoginResponse
import com.example.medicineretail.data.model.responce.SignupResponse
import com.example.medicineretail.models.responseModel.CategoryListResponse
import com.example.medicineretail.models.responseModel.OrderResponse
import com.example.medicineretail.models.responseModel.PosterListResponse
import com.example.medicineretail.models.responseModel.ProductResponse
import com.example.medicineretail.models.responseModel.SingleCategoryResponse
import com.example.medicineretail.models.responseModel.SinglePosterResponse
import com.example.medicineretail.models.responseModel.UserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    // =========================
    // AUTH
    // =========================

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("createUser")
    suspend fun signup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("pincode") pincode: String
    ): Response<SignupResponse>


    // =========================
    // PRODUCTS
    // =========================

    @GET("productsDetails")
    suspend fun getAllProducts(): Response<List<ProductResponse>>


    // =========================
    // USER
    // =========================

    @FormUrlEncoded
    @POST("getSpacificUser")
    suspend fun getSpecificUser(
        @Field("user_id") userId: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @PATCH("updateUser")
    suspend fun updateUser(
        @Field("user_id") userId: String,
        @Field("name") name: String? = null,
        @Field("address") address: String? = null,
        @Field("email") email: String? = null,
        @Field("phone_number") phoneNumber: String? = null,
        @Field("pincode") pincode: String? = null,
        @Field("password") password: String? = null
    ): Response<UserResponse>


    // =========================
    // ORDERS
    // =========================

    @FormUrlEncoded
    @POST("addOrder")
    suspend fun createOrder(
        @Field("order_id") orderId: String,
        @Field("user_id") userId: String,
        @Field("product_id") productId: String,
        @Field("isApproved") isApproved: String,
        @Field("quantity") quantity: Int,
        @Field("date_of_order_creation") dateOfOrderCreation: String,
        @Field("price") price: Double,
        @Field("total_amount") totalAmount: Double,
        @Field("product_name") productName: String,
        @Field("user_name") userName: String,
        @Field("message") message: String,
        @Field("category") category: String
    ): Response<OrderResponse>

    @GET("userOrders/{user_id}")
    suspend fun getUserOrders(
        @Path("user_id") userId: String
    ): Response<OrderResponse>


    // =========================
    // POSTERS
    // =========================

    @GET("getAllPosters")
    suspend fun getAllPosters(): Response<PosterListResponse>

    @GET("getPoster/{posterId}")
    suspend fun getSpecificPoster(
        @Path("posterId") posterId: Int
    ): Response<SinglePosterResponse>


    // =========================
    // CATEGORIES
    // =========================

    @GET("getAllCategories")
    suspend fun getAllCategories(): Response<CategoryListResponse>

    @GET("getCategory/{categoryId}")
    suspend fun getSpecificCategory(
        @Path("categoryId") categoryId: Int
    ): Response<SingleCategoryResponse>
}