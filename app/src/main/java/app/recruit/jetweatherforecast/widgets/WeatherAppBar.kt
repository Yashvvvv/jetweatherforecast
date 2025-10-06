package app.recruit.jetweatherforecast.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.recruit.jetweatherforecast.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title :String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
    showRefreshButton: Boolean = false,
    onRefreshClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    if(showDialog.value){
        ShowSettingDropDownMenu(
            showDialog = showDialog,
            navController = navController
        )
    }

    Surface(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .shadow(elevation = elevation),
        shape = RoundedCornerShape(14.dp),
        color = Color(0xFF6FA6D0) // Matching the medium blue from your gradient
    ) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                color = Color.Black
            )
        },
        actions = {
            if (isMainScreen) {
                if (showRefreshButton) {
                    IconButton(onClick = {
                        onRefreshClicked.invoke()
                    }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh icon")
                    }
                }

                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                }

                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "menu icon")
                }

            } else {
                Box() {}
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Null",
                    tint = androidx.compose.ui.graphics.Color.Black,
                    modifier = androidx.compose.ui.Modifier
                        .padding(8.dp)
                        .clickable { onButtonClicked.invoke() }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController
) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("Favorites", "About", "Settings")

    // Define appropriate icons for each menu item
    val icons = listOf(
        Icons.Rounded.Favorite,  // Icon for Favorites
        Icons.Rounded.Info,      // Icon for About
        Icons.Rounded.Settings   // Icon for Settings
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 50.dp, right = 20.dp)
    ) {
        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White))
        {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false

                    // You might want to navigate based on selection
                    when(index) {
                        0 -> navController.navigate(WeatherScreens.FavouriteScreen.name)
                        1 -> navController.navigate(WeatherScreens.AboutScreen.name)
                        2 -> navController.navigate(WeatherScreens.SettingsScreen.name)
                    }
                },
                    text = {
                        Text(
                            text = text,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = icons[index],  // Use the appropriate icon based on index
                            contentDescription = "$text icon",
                            tint = Color.Black
                        )
                    })
            }
        }
    }
}