//package com.example.medicineretail.view.Screen
//
//import android.util.Log
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import coil3.compose.AsyncImage
//import com.example.medicineretail.models.responseModel.Category
//import com.example.medicineretail.models.responseModel.Poster
//import com.example.medicineretail.models.responseModel.ProductResponse
//import com.example.medicineretail.view.component.UserTopBar
//import com.example.medicineretail.viewModel.CategoryViewModel
//import com.example.medicineretail.viewModel.PosterViewModel
//import com.example.medicineretail.viewModel.ProductViewModel
//
//private const val BASE_URL = "https://suryakush.pythonanywhere.com/"
//
//@Composable
//fun HomeScreen(
//    navController: NavController,
//    posterViewModel: PosterViewModel = hiltViewModel(),
//    categoryViewModel: CategoryViewModel = hiltViewModel(),
//    productViewModel: ProductViewModel = hiltViewModel()
//) {
//    var searchQuery by remember { mutableStateOf("") }
//
//    // Poster API state
//    val postersResponse by posterViewModel.posters.collectAsState()
//    val posterLoading by posterViewModel.isLoading.collectAsState()
//    val posters = postersResponse?.body()?.data ?: emptyList()
//
//    // Category API state
//    val categoriesResponse by categoryViewModel.categories.collectAsState()
//    val categoryLoading by categoryViewModel.isLoading.collectAsState()
//    val categories = categoriesResponse?.body()?.data ?: emptyList()
//
//    // Product API state
//    val products by productViewModel.products.collectAsState()
//    val productLoading by productViewModel.isLoading.collectAsState()
//    val productError by productViewModel.error.collectAsState()
//
//    // Search products received from the server
//    val filteredProducts = remember(products, searchQuery) {
//        if (searchQuery.isBlank()) {
//            products
//        } else {
//            products.filter { product ->
//                product.product_name.contains(searchQuery, ignoreCase = true) ||
//                        product.category.contains(searchQuery, ignoreCase = true) ||
//                        product.product_id.contains(searchQuery, ignoreCase = true)
//            }
//        }
//    }
//
//    // Load all server data
//    LaunchedEffect(Unit) {
//        posterViewModel.getAllPosters()
//        categoryViewModel.getAllCategories()
//        productViewModel.getAllProducts()
//    }
//
//    Scaffold(
//        topBar = {
//            UserTopBar(
//                onNotificationClick = {
//                    // Notification navigation
//                },
//                onProfileClick = {
//                    // Profile navigation
//                }
//            )
//        },
//        containerColor = Color(0xFFF7F9FC)
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(Color(0xFFF7F9FC))
//                .verticalScroll(rememberScrollState())
//                .padding(bottom = 24.dp)
//        ) {
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // SEARCH BAR
//            OutlinedTextField(
//                value = searchQuery,
//                onValueChange = { searchQuery = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                placeholder = {
//                    Text(
//                        text = "Search medicines, brands, health products...",
//                        fontSize = 13.sp,
//                        color = Color(0xFF8A9FB8)
//                    )
//                },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color(0xFF8A9FB8)
//                    )
//                },
//                shape = RoundedCornerShape(14.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                    focusedContainerColor = Color.White,
//                    unfocusedBorderColor = Color(0xFFE2E8F0),
//                    focusedBorderColor = Color(0xFF1479F2)
//                ),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // SERVER POSTERS
//            when {
//                posterLoading && posters.isEmpty() -> {
//                    PosterLoadingAnimation()
//                }
//
//                posters.isNotEmpty() -> {
//                    PosterBanner(
//                        posters = posters,
//                        onPosterClick = { posterId ->
//                            posterViewModel.getSpecificPoster(posterId)
//                        }
//                    )
//                }
//
//                else -> Unit
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // SERVER CATEGORIES
//            when {
//                categoryLoading && categories.isEmpty() -> {
//                    CategoryLoadingAnimation()
//                }
//
//                categories.isNotEmpty() -> {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .horizontalScroll(rememberScrollState())
//                            .padding(horizontal = 16.dp),
//                        horizontalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        categories.forEach { category ->
//                            CategoryItem(
//                                category = category,
//                                onClick = {
//                                    category.id?.let { id ->
//                                        categoryViewModel.getSpecificCategory(id)
//                                    }
//                                }
//                            )
//                        }
//                    }
//                }
//
//                else -> Unit
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // POPULAR MEDICINES
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Popular Medicines",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF123B78)
//                )
//
//                Text(
//                    text = "View All →",
//                    fontSize = 12.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFF1479F2),
//                    modifier = Modifier.clickable {
//                        // Navigate to all products when that screen is ready.
//                    }
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            when {
//                productLoading && products.isEmpty() -> {
//                    ProductLoadingAnimation()
//                }
//
//                productError != null && products.isEmpty() -> {
//                    ProductMessage(
//                        message = productError ?: "Failed to load products.",
//                        messageColor = Color(0xFFEF4444)
//                    )
//
//                    TextButton(
//                        onClick = {
//                            productViewModel.getAllProducts()
//                        },
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        Text("Retry")
//                    }
//                }
//
//                filteredProducts.isEmpty() -> {
//                    ProductMessage(
//                        message = if (products.isEmpty()) {
//                            "No products available."
//                        } else {
//                            "No products found."
//                        }
//                    )
//                }
//
//                else -> {
//                    Column(
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        verticalArrangement = Arrangement.spacedBy(12.dp)
//                    ) {
//                        filteredProducts.chunked(2).forEach { productRow ->
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.spacedBy(12.dp)
//                            ) {
//                                productRow.forEach { product ->
//                                    ServerProductCard(
//                                        product = product,
//                                        modifier = Modifier.weight(1f),
//                                        onAddToCart = {
//                                            // Connect to cart functionality later.
//                                        }
//                                    )
//                                }
//
//                                if (productRow.size == 1) {
//                                    Spacer(modifier = Modifier.weight(1f))
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // QUICK ACTIONS
//            Text(
//                text = "Quick Actions",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF123B78),
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                QuickActionBox(
//                    title = "Upload Prescription",
//                    icon = "📄",
//                    bg = Color(0xFFEBF3FF),
//                    modifier = Modifier.weight(1f)
//                )
//
//                QuickActionBox(
//                    title = "Track Orders",
//                    icon = "⏰",
//                    bg = Color(0xFFEAFBF1),
//                    modifier = Modifier.weight(1f)
//                )
//
//                QuickActionBox(
//                    title = "My Cart",
//                    icon = "🛒",
//                    bg = Color(0xFFF2EBFB),
//                    modifier = Modifier.weight(1f)
//                )
//
//                QuickActionBox(
//                    title = "Support",
//                    icon = "🎧",
//                    bg = Color(0xFFFFF7EB),
//                    modifier = Modifier.weight(1f)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // HEALTH ESSENTIALS
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Health Essentials",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF123B78)
//                )
//
//                Text(
//                    text = "View All →",
//                    fontSize = 12.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFF1479F2),
//                    modifier = Modifier.clickable {
//                        // Health essentials navigation
//                    }
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            // EXISTING BOTTOM CONTENT
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .clip(RoundedCornerShape(18.dp))
//                    .background(Color(0xFFEAFBF1))
//                    .padding(18.dp)
//            ) {
//                Column {
//                    Text(
//                        text = "Daily Care for a\nHealthier You",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF123B78)
//                    )
//
//                    Spacer(modifier = Modifier.height(4.dp))
//
//                    Text(
//                        text = "Vitamins • Wellness • Personal Care",
//                        fontSize = 11.sp,
//                        color = Color(0xFF5879A2)
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Button(
//                        onClick = {
//                            // Explore navigation
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF20A849)
//                        ),
//                        shape = RoundedCornerShape(8.dp),
//                        contentPadding = PaddingValues(
//                            horizontal = 14.dp,
//                            vertical = 6.dp
//                        )
//                    ) {
//                        Text(
//                            text = "Explore Now →",
//                            fontSize = 12.sp,
//                            color = Color.White
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//        }
//    }
//}
//
//// LOADING ANIMATION
//
//@Composable
//fun rememberLoadingAnimation(): Float {
//    val transition = rememberInfiniteTransition(
//        label = "loading_transition"
//    )
//
//    val alpha by transition.animateFloat(
//        initialValue = 0.35f,
//        targetValue = 0.75f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 700,
//                easing = LinearEasing
//            ),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "loading_alpha"
//    )
//
//    return alpha
//}
//
//@Composable
//fun PosterLoadingAnimation() {
//    val alpha = rememberLoadingAnimation()
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .horizontalScroll(rememberScrollState())
//            .padding(horizontal = 16.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        repeat(2) {
//            Box(
//                modifier = Modifier
//                    .width(340.dp)
//                    .height(180.dp)
//                    .clip(RoundedCornerShape(18.dp))
//                    .background(
//                        Color(0xFFDCE5F2).copy(alpha = alpha)
//                    )
//            )
//        }
//    }
//}
//
//@Composable
//fun CategoryLoadingAnimation() {
//    val alpha = rememberLoadingAnimation()
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .horizontalScroll(rememberScrollState())
//            .padding(horizontal = 16.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        repeat(6) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(64.dp)
//                        .clip(RoundedCornerShape(16.dp))
//                        .background(
//                            Color(0xFFDCE5F2).copy(alpha = alpha)
//                        )
//                )
//
//                Spacer(modifier = Modifier.height(6.dp))
//
//                Box(
//                    modifier = Modifier
//                        .width(48.dp)
//                        .height(10.dp)
//                        .clip(RoundedCornerShape(5.dp))
//                        .background(
//                            Color(0xFFDCE5F2).copy(alpha = alpha)
//                        )
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductLoadingAnimation() {
//    val alpha = rememberLoadingAnimation()
//
//    Column(
//        modifier = Modifier.padding(horizontal = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        repeat(2) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                repeat(2) {
//                    Column(
//                        modifier = Modifier
//                            .weight(1f)
//                            .clip(RoundedCornerShape(16.dp))
//                            .background(Color.White)
//                            .padding(12.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(100.dp)
//                                .clip(RoundedCornerShape(10.dp))
//                                .background(
//                                    Color(0xFFDCE5F2).copy(alpha = alpha)
//                                )
//                        )
//
//                        Spacer(modifier = Modifier.height(10.dp))
//
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth(0.8f)
//                                .height(12.dp)
//                                .clip(RoundedCornerShape(4.dp))
//                                .background(
//                                    Color(0xFFDCE5F2).copy(alpha = alpha)
//                                )
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth(0.5f)
//                                .height(10.dp)
//                                .clip(RoundedCornerShape(4.dp))
//                                .background(
//                                    Color(0xFFDCE5F2).copy(alpha = alpha)
//                                )
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun ProductMessage(
//    message: String,
//    messageColor: Color = Color(0xFF8A9FB8)
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 24.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = message,
//            color = messageColor,
//            fontSize = 13.sp
//        )
//    }
//}
//
//// SERVER POSTER BANNER
//
//@Composable
//fun PosterBanner(
//    posters: List<Poster>,
//    onPosterClick: (Int) -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .horizontalScroll(rememberScrollState())
//            .padding(horizontal = 16.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        posters.forEach { poster ->
//            val imageUrl = poster.poster_image
//                ?.takeIf { it.isNotBlank() }
//                ?.let {
//                    "${BASE_URL}uploads/posters/${it.substringAfterLast("/")}"
//                }
//
//            Card(
//                modifier = Modifier
//                    .width(340.dp)
//                    .height(180.dp)
//                    .clickable {
//                        poster.id?.let { id ->
//                            onPosterClick(id)
//                        }
//                    },
//                shape = RoundedCornerShape(18.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White
//                ),
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 1.dp
//                )
//            ) {
//                AsyncImage(
//                    model = imageUrl,
//                    contentDescription = poster.poster_name ?: "Poster",
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop,
//                    onSuccess = {
//                        Log.d("POSTER_SUCCESS", "Poster loaded: $imageUrl")
//                    },
//                    onError = {
//                        Log.e(
//                            "POSTER_ERROR",
//                            "Poster failed: $imageUrl",
//                            it.result.throwable
//                        )
//                    }
//                )
//            }
//        }
//    }
//}
//
//// SERVER CATEGORY ITEM
//
//@Composable
//fun CategoryItem(
//    category: Category,
//    onClick: () -> Unit
//) {
//    val imageUrl = category.category_image
//        ?.takeIf { it.isNotBlank() }
//        ?.let {
//            "${BASE_URL}uploads/categories/${it.substringAfterLast("/")}"
//        }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.clickable(onClick = onClick)
//    ) {
//        Card(
//            modifier = Modifier.size(64.dp),
//            shape = RoundedCornerShape(16.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = Color.White
//            ),
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 1.dp
//            )
//        ) {
//            AsyncImage(
//                model = imageUrl,
//                contentDescription = category.category_name ?: "Category",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(8.dp)
//                    .clip(RoundedCornerShape(12.dp)),
//                contentScale = ContentScale.Crop,
//                onSuccess = {
//                    Log.d("CATEGORY_SUCCESS", "Category loaded: $imageUrl")
//                },
//                onError = {
//                    Log.e(
//                        "CATEGORY_ERROR",
//                        "Category failed: $imageUrl",
//                        it.result.throwable
//                    )
//                }
//            )
//        }
//
//        Spacer(modifier = Modifier.height(6.dp))
//
//        Text(
//            text = category.category_name ?: "Category",
//            fontSize = 11.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color(0xFF123B78),
//            maxLines = 1
//        )
//    }
//}
//
//// SERVER PRODUCT CARD
//
//@Composable
//fun ServerProductCard(
//    product: ProductResponse,
//    modifier: Modifier = Modifier,
//    onAddToCart: () -> Unit
//) {
//    val imageUrl = product.product_image
//        ?.takeIf { it.isNotBlank() }
//        ?.let { imagePath ->
//            "${BASE_URL}uploads/products/${imagePath.substringAfterLast("/")}"
//        }
//
//    val stockQuantity = product.stock.toIntOrNull() ?: 0
//    val inStock = stockQuantity > 0
//
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 1.dp
//        )
//    ) {
//        Column(
//            modifier = Modifier.padding(12.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(Color(0xFFF7F9FC)),
//                contentAlignment = Alignment.Center
//            ) {
//                if (imageUrl != null) {
//                    AsyncImage(
//                        model = imageUrl,
//                        contentDescription = product.product_name,
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Crop,
//                        onSuccess = {
//                            Log.d("PRODUCT_SUCCESS", "Product loaded: $imageUrl")
//                        },
//                        onError = {
//                            Log.e(
//                                "PRODUCT_ERROR",
//                                "Product image failed: $imageUrl",
//                                it.result.throwable
//                            )
//                        }
//                    )
//                } else {
//                    Text(
//                        text = "Image unavailable",
//                        fontSize = 10.sp,
//                        color = Color(0xFF8A9FB8)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = product.product_name,
//                fontSize = 13.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF123B78),
//                maxLines = 2,
//                minLines = 2
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                text = product.category,
//                fontSize = 10.sp,
//                color = Color(0xFF8A9FB8),
//                maxLines = 1
//            )
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Text(
//                text = if (inStock) {
//                    "In Stock: $stockQuantity"
//                } else {
//                    "Out of Stock"
//                },
//                fontSize = 10.sp,
//                fontWeight = FontWeight.Bold,
//                color = if (inStock) {
//                    Color(0xFF20A849)
//                } else {
//                    Color(0xFFEF4444)
//                }
//            )
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Button(
//                onClick = onAddToCart,
//                enabled = inStock,
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF1479F2),
//                    disabledContainerColor = Color(0xFFCBD5E1)
//                ),
//                contentPadding = PaddingValues(vertical = 6.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ShoppingCart,
//                    contentDescription = "Add to cart",
//                    modifier = Modifier.size(14.dp),
//                    tint = Color.White
//                )
//
//                Spacer(modifier = Modifier.width(4.dp))
//
//                Text(
//                    text = "Add to Cart",
//                    fontSize = 11.sp,
//                    color = Color.White
//                )
//            }
//        }
//    }
//}
//
//// QUICK ACTION
//
//@Composable
//fun QuickActionBox(
//    title: String,
//    icon: String,
//    bg: Color,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier.clickable {
//            // Quick action navigation
//        },
//        shape = RoundedCornerShape(14.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = bg
//        ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 0.dp
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            horizontalAlignment = Alignment.Start
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(36.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(Color.White),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = icon,
//                    fontSize = 16.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Text(
//                text = title,
//                fontSize = 11.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF123B78),
//                maxLines = 2,
//                lineHeight = 14.sp
//            )
//        }
//    }
//}
//





package com.example.medicineretail.view.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
 import com.example.medicineretail.view.Screen.components.*
import com.example.medicineretail.view.component.UserTopBar
import com.example.medicineretail.viewModel.CategoryViewModel
import com.example.medicineretail.viewModel.PosterViewModel
import com.example.medicineretail.viewModel.ProductViewModel

private const val BASE_URL = "https://suryakush.pythonanywhere.com/"

@Composable
fun HomeScreen(
    navController: NavController,
    posterViewModel: PosterViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    productViewModel: ProductViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }

    // Poster API state
    val postersResponse by posterViewModel.posters.collectAsState()
    val posterLoading by posterViewModel.isLoading.collectAsState()
    val posters = postersResponse?.body()?.data ?: emptyList()

    // Category API state
    val categoriesResponse by categoryViewModel.categories.collectAsState()
    val categoryLoading by categoryViewModel.isLoading.collectAsState()
    val categories = categoriesResponse?.body()?.data ?: emptyList()

    // Product API state
    val products by productViewModel.products.collectAsState()
    val productLoading by productViewModel.isLoading.collectAsState()
    val productError by productViewModel.error.collectAsState()

    // Search products received from the server
    val filteredProducts = remember(products, searchQuery) {
        if (searchQuery.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.product_name.contains(searchQuery, ignoreCase = true) ||
                        product.category.contains(searchQuery, ignoreCase = true) ||
                        product.product_id.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    // Load all server data
    LaunchedEffect(Unit) {
        posterViewModel.getAllPosters()
        categoryViewModel.getAllCategories()
        productViewModel.getAllProducts()
    }

    Scaffold(
        topBar = {
            UserTopBar(
                onNotificationClick = {
                    // Notification navigation
                },
                onProfileClick = {
                    // Profile navigation
                }
            )
        },
        containerColor = Color(0xFFF7F9FC)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF7F9FC))
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // SEARCH BAR
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = {
                    Text(
                        text = "Search medicines, brands, health products...",
                        fontSize = 13.sp,
                        color = Color(0xFF8A9FB8)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF8A9FB8)
                    )
                },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    focusedBorderColor = Color(0xFF1479F2)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // SERVER POSTERS
            when {
                posterLoading && posters.isEmpty() -> {
                    PosterLoadingAnimation()
                }

                posters.isNotEmpty() -> {
                    PosterBanner(
                        baseUrl = BASE_URL,
                        posters = posters,
                        onPosterClick = { posterId ->
                            posterViewModel.getSpecificPoster(posterId)
                        }
                    )
                }

                else -> Unit
            }

            Spacer(modifier = Modifier.height(20.dp))

            // SERVER CATEGORIES
            when {
                categoryLoading && categories.isEmpty() -> {
                    CategoryLoadingAnimation()
                }

                categories.isNotEmpty() -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        categories.forEach { category ->
                            CategoryItem(
                                baseUrl = BASE_URL,
                                category = category,
                                onClick = {
                                    category.id?.let { id ->
                                        categoryViewModel.getSpecificCategory(id)
                                    }
                                }
                            )
                        }
                    }
                }

                else -> Unit
            }

            Spacer(modifier = Modifier.height(20.dp))

            // POPULAR MEDICINES
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular Medicines",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF123B78)
                )

                Text(
                    text = "View All →",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1479F2),
                    modifier = Modifier.clickable {
                        // Navigate to all products when that screen is ready.
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            when {
                productLoading && products.isEmpty() -> {
                    ProductLoadingAnimation()
                }

                productError != null && products.isEmpty() -> {
                    ProductMessage(
                        message = productError ?: "Failed to load products.",
                        messageColor = Color(0xFFEF4444)
                    )

                    TextButton(
                        onClick = {
                            productViewModel.getAllProducts()
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Retry")
                    }
                }

                filteredProducts.isEmpty() -> {
                    ProductMessage(
                        message = if (products.isEmpty()) {
                            "No products available."
                        } else {
                            "No products found."
                        }
                    )
                }

                else -> {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        filteredProducts.chunked(2).forEach { productRow ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                productRow.forEach { product ->
                                    ServerProductCard(
                                        baseUrl = BASE_URL,
                                        product = product,
                                        modifier = Modifier.weight(1f),
                                        onAddToCart = {
                                            // Connect to cart functionality later.
                                        }
                                    )
                                }

                                if (productRow.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // QUICK ACTIONS
            Text(
                text = "Quick Actions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF123B78),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionBox(
                    title = "Upload Prescription",
                    icon = "📄",
                    bg = Color(0xFFEBF3FF),
                    modifier = Modifier.weight(1f)
                )

                QuickActionBox(
                    title = "Track Orders",
                    icon = "⏰",
                    bg = Color(0xFFEAFBF1),
                    modifier = Modifier.weight(1f)
                )

                QuickActionBox(
                    title = "My Cart",
                    icon = "🛒",
                    bg = Color(0xFFF2EBFB),
                    modifier = Modifier.weight(1f)
                )

                QuickActionBox(
                    title = "Support",
                    icon = "🎧",
                    bg = Color(0xFFFFF7EB),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // HEALTH ESSENTIALS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Health Essentials",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF123B78)
                )

                Text(
                    text = "View All →",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1479F2),
                    modifier = Modifier.clickable {
                        // Health essentials navigation
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // EXISTING BOTTOM CONTENT
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFEAFBF1))
                    .padding(18.dp)
            ) {
                Column {
                    Text(
                        text = "Daily Care for a\nHealthier You",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF123B78)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Vitamins • Wellness • Personal Care",
                        fontSize = 11.sp,
                        color = Color(0xFF5879A2)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            // Explore navigation
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF20A849)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 6.dp
                        )
                    ) {
                        Text(
                            text = "Explore Now →",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
