package com.example.medicineretail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicineretail.models.responseModel.CategoryListResponse
import com.example.medicineretail.models.responseModel.SingleCategoryResponse
import com.example.medicineretail.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _categories =
        MutableStateFlow<Response<CategoryListResponse>?>(null)

    val categories: StateFlow<Response<CategoryListResponse>?> =
        _categories.asStateFlow()

    private val _specificCategory =
        MutableStateFlow<Response<SingleCategoryResponse>?>(null)

    val specificCategory: StateFlow<Response<SingleCategoryResponse>?> =
        _specificCategory.asStateFlow()

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?> =
        _error.asStateFlow()

    fun getAllCategories() {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                val response = repository.getAllCategories()

                if (response.isSuccessful) {

                    _categories.value = response

                } else {

                    _error.value =
                        "Failed to load categories"
                }

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Something went wrong"

            } finally {

                _isLoading.value = false
            }
        }
    }

    fun getSpecificCategory(
        categoryId: Int
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                val response =
                    repository.getSpecificCategory(categoryId)

                if (response.isSuccessful) {

                    _specificCategory.value = response

                } else {

                    _error.value =
                        "Failed to load category"
                }

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Something went wrong"

            } finally {

                _isLoading.value = false
            }
        }
    }
}