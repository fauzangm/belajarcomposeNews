package id.android.belajarcomposenewsapps.presentation.leartwomain.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.data.remote.response.LoginResponse
import id.android.belajarcomposenewsapps.presentation.common.DialogPrimary
import id.android.belajarcomposenewsapps.presentation.common.PrimaryButton
import id.android.belajarcomposenewsapps.presentation.common.PrimaryOutlineButton
import id.android.belajarcomposenewsapps.utils.Dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    event: AuthViewModel
) {
    val viewModel by event.loginResult.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.mipmap.ilust_bg_auth),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 32.dp, horizontal = 32.dp),


            ) {

            Row( // Use Row for horizontal arrangement
                modifier = Modifier
                    .fillMaxWidth() // Make Row occupy full width
                ,
                horizontalArrangement = Arrangement.Center// Center content within Row
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_ewarga),
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .width(180.dp)
                        .padding(top = 16.dp)
                )
            }

            var email by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                value = email,
                label = { Text(text = "Enter Your Email") },
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                )
            )

            var password by remember { mutableStateOf(TextFieldValue("")) }
            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = password,
                label = { Text(text = "Enter Your Password") },
                onValueChange = {
                    password = it
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                ),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Favorite
                    else Icons.Filled.FavoriteBorder

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                }
            )
            Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
            if (viewModel.loading) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(36.dp), color = colorResource(
                            id = R.color.color_primary
                        )
                    )
                }

            } else {
                PrimaryButton(
                    text = "Login",
                    onClick = {
                        event.loginPresensi(email.text, password.text)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
                Log.e("network", "responnya = ${viewModel.response}")
            }
            Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
            PrimaryOutlineButton(
                text = "Login dengan google",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            if (viewModel.isError) {
                AlertDialog(
                    onDismissRequest = {
                        event.resetStateError()
                    },
                    title = { Text(text = stringResource(R.string.error_messange)) },
                    text = { Text(text = viewModel.errorMessage) },

                    confirmButton = {
                        TextButton(onClick = {
                            event.resetStateError()
                        }) {
                     
                            Text(text = "Ok")
                        }
                    },
                )
            }

        }

    }

}