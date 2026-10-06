package com.example.medicineretail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicineretail.models.responseModel.PosterListResponse
import com.example.medicineretail.models.responseModel.SinglePosterResponse
import com.example.medicineretail.repository.PosterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response

@HiltViewModel
class PosterViewModel @Inject constructor(
    private val repository: PosterRepository
) : ViewModel() {

    private val _posters = MutableStateFlow<Response<PosterListResponse>?>(null)
    val posters: StateFlow<Response<PosterListResponse>?> = _posters.asStateFlow()

    private val _specificPoster = MutableStateFlow<Response<SinglePosterResponse>?>(null)
    val specificPoster: StateFlow<Response<SinglePosterResponse>?> =
        _specificPoster.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun getAllPosters() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = repository.getAllPosters()

                if (response.isSuccessful) {
                    _posters.value = response
                } else {
                    _error.value = "Failed to load posters"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Something went wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getSpecificPoster(posterId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = repository.getSpecificPoster(posterId)

                if (response.isSuccessful) {
                    _specificPoster.value = response
                } else {
                    _error.value = "Failed to load poster"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Something went wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }
}