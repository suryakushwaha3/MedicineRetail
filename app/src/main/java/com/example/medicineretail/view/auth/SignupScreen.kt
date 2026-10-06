package com.example.medicineretail.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicineretail.R
import com.example.medicineretail.view.navigation.Routes
import com.example.medicineretail.viewModel.AuthViewModel
import kotlinx.coroutines.delay

private val Blue = Color(0xFF1479F2)
private val DarkBlue = Color(0xFF123B78)
private val Green = Color(0xFF20A849)
private val TextBlue = Color(0xFF5879A2)
private val BorderBlue = Color(0xFFD2E2F4)
private val FieldBgColor = Color(0xFFF9FBFC)

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var address by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var pincode by rememberSaveable { mutableStateOf("") }
    var showSuccess by rememberSaveable { mutableStateOf(false) }
    var localError by rememberSaveable { mutableStateOf<String?>(null) }

    val isLoading by viewModel.isLoading.collectAsState()
    val signupSuccess by viewModel.signupSuccess.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(signupSuccess) {
        if (signupSuccess) {
            showSuccess = true
            delay(1500)
            navController.navigate(Routes.LoginScreen) {
                popUpTo(Routes.SignupScreen) { inclusive = true }
            }
        }
    }

    Surface(Modifier.fillMaxSize(), color = Color.White) {
        Box(Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.backgraound),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.fillMaxSize()) {

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(top = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = "Medical Retail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            "Create Account",
                            color = DarkBlue,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(2.dp))

                        Text(
                            "Sign up to get started with your healthy journey.",
                            color = TextBlue,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(Modifier.height(14.dp))

                    SignupTextField(
                        value = name,

                        // CHANGED: Name me sirf letters aur spaces allow
                        onValueChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isWhitespace()
                                }
                            ) {
                                name = it
                                localError = null
                            }
                        },

                        placeholder = "Full Name",
                        leadingIcon = {
                            Icon(
                                Icons.Default.Person,
                                null,
                                tint = Blue,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))

                    SignupTextField(
                        value = email,

                        // CHANGED: Email ko normal String lene diya,
                        // actual email format Signup button par check hoga
                        onValueChange = {
                            email = it
                            localError = null
                        },

                        placeholder = "Email Address",
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                null,
                                tint = Blue,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))

                    SignupTextField(
                        value = password,

                        // CHANGED: Password typing par error clear
                        onValueChange = {
                            password = it
                            localError = null
                        },

                        placeholder = "Password",
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                null,
                                tint = Blue,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible)
                                        Icons.Default.VisibilityOff
                                    else
                                        Icons.Default.Visibility,
                                    contentDescription = null,
                                    tint = TextBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation()
                    )

                    Spacer(Modifier.height(10.dp))

                    SignupTextField(
                        value = address,

                        // CHANGED: Address typing par error clear
                        onValueChange = {
                            address = it
                            localError = null
                        },

                        placeholder = "Address",
                        leadingIcon = {
                            Icon(
                                Icons.Default.Home,
                                null,
                                tint = Blue,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))

                    SignupTextField(
                        value = phoneNumber,

                        // CHANGED: Phone me sirf digits aur maximum 10 digits
                        onValueChange = {
                            if (it.all { char -> char.isDigit() } &&
                                it.length <= 10
                            ) {
                                phoneNumber = it
                                localError = null
                            }
                        },

                        placeholder = "Phone Number",
                        leadingIcon = {
                            Icon(
                                Icons.Default.Phone,
                                null,
                                tint = Blue,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))

                    SignupTextField(
                        value = pincode,

                        // CHANGED: Pincode me sirf digits aur maximum 6 digits
                        onValueChange = {
                            if (it.all { char -> char.isDigit() } &&
                                it.length <= 6
                            ) {
                                pincode = it
                                localError = null
                            }
                        },

                        placeholder = "Pincode",
                        leadingIcon = {
                            Icon(
                                Icons.Default.PinDrop,
                                null,
                                tint = Blue,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    )

                    Spacer(Modifier.height(18.dp))

                    Button(
                        onClick = {

                            // CHANGED: Field-by-field validation
                            when {
                                name.isBlank() -> {
                                    localError = "Name is required"
                                }

                                email.isBlank() -> {
                                    localError = "Email is required"
                                }

                                // CHANGED: Proper email format check
                                !android.util.Patterns.EMAIL_ADDRESS
                                    .matcher(email)
                                    .matches() -> {
                                    localError = "Enter a valid email address"
                                }

                                password.isBlank() -> {
                                    localError = "Password is required"
                                }

                                // CHANGED: Password minimum length
                                password.length < 6 -> {
                                    localError =
                                        "Password must be at least 6 characters"
                                }

                                address.isBlank() -> {
                                    localError = "Address is required"
                                }

                                phoneNumber.isBlank() -> {
                                    localError = "Phone number is required"
                                }

                                // CHANGED: Phone exactly 10 digits
                                phoneNumber.length != 10 -> {
                                    localError =
                                        "Phone number must be 10 digits"
                                }

                                pincode.isBlank() -> {
                                    localError = "Pincode is required"
                                }

                                // CHANGED: Pincode exactly 6 digits
                                pincode.length != 6 -> {
                                    localError =
                                        "Pincode must be 6 digits"
                                }

                                else -> {
                                    localError = null

                                    viewModel.signup(
                                        name = name,
                                        email = email,
                                        password = password,
                                        address = address,
                                        phoneNumber = phoneNumber,
                                        pincode = pincode
                                    )
                                }
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .shadow(
                                6.dp,
                                RoundedCornerShape(26.dp),
                                spotColor = Blue.copy(alpha = 0.4f)
                            ),

                        enabled = !isLoading,

                        shape = RoundedCornerShape(26.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "Sign Up",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(Modifier.width(8.dp))

                                Icon(
                                    Icons.Default.ArrowForward,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    if (showSuccess) {
                        Spacer(Modifier.height(6.dp))

                        Text(
                            "Account Created Successfully",
                            color = Green,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    localError?.let {
                        Spacer(Modifier.height(6.dp))

                        Text(
                            it,
                            color = Color.Red,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    error?.let {
                        Spacer(Modifier.height(6.dp))

                        Text(
                            it,
                            color = Color.Red,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(Modifier.height(14.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text(
                            "Already have an account? ",
                            color = TextBlue,
                            fontSize = 14.sp
                        )

                        Text(
                            "Login",
                            color = Blue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate(Routes.LoginScreen) {
                                    popUpTo(Routes.SignupScreen) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(FieldBgColor)
            .border(
                1.2.dp,
                BorderBlue,
                RoundedCornerShape(26.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon()

        Spacer(Modifier.width(12.dp))

        androidx.compose.foundation.text.BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = TextStyle(
                color = DarkBlue,
                fontSize = 15.sp
            ),
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            placeholder,
                            color = TextBlue.copy(alpha = 0.8f),
                            fontSize = 15.sp
                        )
                    }

                    innerTextField()
                }
            }
        )

        trailingIcon?.invoke()
    }
}
