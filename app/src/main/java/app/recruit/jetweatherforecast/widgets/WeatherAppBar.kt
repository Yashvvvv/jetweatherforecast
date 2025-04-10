package app.recruit.jetweatherforecast.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title :String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .shadow(elevation = elevation),
        shape = RoundedCornerShape(14.dp),
        color = Color(0xFF6FA6D0) // Matching the medium blue from your gradient
    ) {

    TopAppBar(
        title = {
            Text(
                text = title,
                color = androidx.compose.ui.graphics.Color.Black,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 60.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "menu icon")
                }
            } else {
                Box() {}
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon, contentDescription = "Null",
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
