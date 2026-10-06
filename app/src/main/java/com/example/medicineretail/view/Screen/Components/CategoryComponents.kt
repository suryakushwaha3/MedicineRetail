package com.example.medicineretail.view.Screen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.medicineretail.models.responseModel.Category

@Composable
fun CategoryItem(
    baseUrl: String,
    category: Category,
    onClick: () -> Unit
) {
    val imageUrl = category.category_image
        ?.takeIf { it.isNotBlank() }
        ?.let {
            "${baseUrl}uploads/categories/${it.substringAfterLast("/")}"
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Card(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = category.category_name ?: "Category",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                onSuccess = {
                    Log.d("CATEGORY_SUCCESS", "Category loaded: $imageUrl")
                },
                onError = {
                    Log.e(
                        "CATEGORY_ERROR",
                        "Category failed: $imageUrl",
                        it.result.throwable
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = category.category_name ?: "Category",
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF123B78),
            maxLines = 1
        )
    }
}