package com.example.littlelemonapp

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemonapp.ui.theme.ButtonColor
import com.example.littlelemonapp.ui.theme.PrimaryColor
import com.example.littlelemonapp.util.EMAIL
import com.example.littlelemonapp.util.EMPTY_STRING
import com.example.littlelemonapp.util.FIRST_NAME
import com.example.littlelemonapp.util.LAST_NAME

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(
    sharedPreferences: SharedPreferences,
    context: Context,
    onItemClick: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar() }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            fun validateInput(text: String): Boolean {
                return text.isNotBlank()
            }

            Text(
                text = stringResource(id = R.string.let_get_to_know_you),
                modifier = Modifier
                    .background(color = PrimaryColor)
                    .fillMaxWidth()
                    .height(100.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                style = TextStyle(color = Color.White)
            )

            Text(
                text = stringResource(id = R.string.personal_information),
                modifier = Modifier.padding(start = 16.dp, top = 32.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = stringResource(id = R.string.first_name),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                )
            )

            var firstName by remember { mutableStateOf(EMPTY_STRING) }
            OutlinedTextField(
                value = firstName,
                onValueChange = { name ->
                    firstName = name
                    validateInput(firstName)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                label = { Text(stringResource(id = R.string.first_name), style = TextStyle(fontSize = 13.sp)) }
            )

            Text(
                text = stringResource(id = R.string.last_name),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                )
            )

            var lastName by remember { mutableStateOf(EMPTY_STRING) }
            OutlinedTextField(
                value = lastName,
                onValueChange = { name ->
                    lastName = name
                    validateInput(lastName)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                label = { Text(stringResource(id = R.string.last_name), style = TextStyle(fontSize = 13.sp)) }
            )

            Text(
                text = stringResource(id = R.string.email),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                )
            )

            var emailAddress by remember { mutableStateOf(EMPTY_STRING) }
            OutlinedTextField(
                value = emailAddress,
                onValueChange = { email ->
                    emailAddress = email
                    validateInput(emailAddress)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                label = { Text(stringResource(id = R.string.email), style = TextStyle(fontSize = 13.sp)) }
            )

            Button(
                onClick = {
                      if (validateInput(firstName) && validateInput(lastName) && validateInput(emailAddress)) {
                          saveUserData(sharedPreferences, firstName, lastName, emailAddress)
                          Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                          onItemClick.invoke()
                      } else {
                          Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
                      }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(text = stringResource(id = R.string.register),
                    style = TextStyle(fontWeight = FontWeight.Light)
                )
            }
        }
    }
}

fun saveUserData(
    sharedPreferences: SharedPreferences,
    firstName: String,
    lastName: String,
    email: String
) {
    with(sharedPreferences.edit()) {
        putString(FIRST_NAME, firstName)
        putString(LAST_NAME, lastName)
        putString(EMAIL, email)
        apply()
    }
}

@Composable
fun TopAppBar() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(24.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
//    Onboarding()
}
