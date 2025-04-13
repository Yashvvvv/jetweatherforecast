package app.recruit.jetweatherforecast.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.recruit.jetweatherforecast.widgets.WeatherAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import app.recruit.jetweatherforecast.navigation.WeatherScreens


@Composable
fun SearchScreen(navController: NavController){
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Search",
                elevation = 0.dp,
                isMainScreen = false,
                icon = Icons.AutoMirrored.Filled.ArrowBack
            ){
                navController.popBackStack()
            }
        }
    ){
        Surface(modifier = Modifier.padding(paddingValues = it)) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,)
            {
                SearchBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ){ city ->
                    navController.navigate(WeatherScreens.MainScreen.name + "/$city")
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},)
{
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }

    Column(){
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Search",
            onAction = KeyboardActions
            {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }
}

@Composable
fun CommonTextField(valueState: MutableState<String>,
                    placeholder: String,
                    keyboradType: KeyboardType = KeyboardType.Text,
                    imeAction: ImeAction = ImeAction.Next,
                    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboradType
        ),
        keyboardActions = onAction,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Blue
        ),
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
        },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
}