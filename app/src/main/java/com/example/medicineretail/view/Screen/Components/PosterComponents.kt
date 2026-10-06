package com.example.medicineretail.view.Screen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.medicineretail.models.responseModel.Poster

@Composable
fun PosterBanner(
    baseUrl: String,
    posters: List<Poster>,
    onPosterClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        posters.forEach { poster ->
            val imageUrl = poster.poster_image
                ?.takeIf { it.isNotBlank() }
                ?.let {
                    "${baseUrl}uploads/posters/${it.substringAfterLast("/")}"
                }

            Card(
                modifier = Modifier
                    .width(400.dp)
                    .height(200.dp)
                    .clickable {
                        poster.id?.let { id ->
                            onPosterClick(id)
                        }
                    },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 1.dp
                )
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = poster.poster_name ?: "Poster",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    onSuccess = {
                        Log.d("POSTER_SUCCESS", "Poster loaded: $imageUrl")
                    },
                    onError = {
                        Log.e(
                            "POSTER_ERROR",
                            "Poster failed: $imageUrl",
                            it.result.throwable
                        )
                    }
                )
            }
        }
    }
}