package com.example.medicineretail.view.Screen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.example.medicineretail.models.responseModel.ProductResponse

@Composable
fun ServerProductCard(
    baseUrl: String,
    product: ProductResponse,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit
) {
    val imageUrl = product.product_image
        ?.takeIf { it.isNotBlank() }
        ?.let { imagePath ->
            "${baseUrl}uploads/products/${imagePath.substringAfterLast("/")}"
        }

    val stockQuantity = product.stock.toIntOrNull() ?: 0
    val inStock = stockQuantity > 0

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF7F9FC)),
                contentAlignment = Alignment.Center
            ) {
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = product.product_name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        onSuccess = {
                            Log.d("PRODUCT_SUCCESS", "Product loaded: $imageUrl")
                        },
                        onError = {
                            Log.e(
                                "PRODUCT_ERROR",
                                "Product image failed: $imageUrl",
                                it.result.throwable
                            )
                        }
                    )
                } else {
                    Text(
                        text = "Image unavailable",
                        fontSize = 10.sp,
                        color = Color(0xFF8A9FB8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.product_name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF123B78),
                maxLines = 2,
                minLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.category,
                fontSize = 10.sp,
                color = Color(0xFF8A9FB8),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (inStock) {
                    "In Stock: $stockQuantity"
                } else {
                    "Out of Stock"
                },
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (inStock) {
                    Color(0xFF20A849)
                } else {
                    Color(0xFFEF4444)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onAddToCart,
                enabled = inStock,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1479F2),
                    disabledContainerColor = Color(0xFFCBD5E1)
                ),
                contentPadding = PaddingValues(vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to cart",
                    modifier = Modifier.size(14.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Add to Cart",
                    fontSize = 11.sp,
                    color = Color.White
                )
            }
        }
    }
}