package id.android.belajarcomposenewsapps.presentation.bottomNav.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.ui.theme.BelajarcomposenewsappsTheme
import id.android.belajarcomposenewsapps.utils.Dimens
import id.android.belajarcomposenewsapps.utils.Dimens.IconSize

@Composable
fun NewsBottomNav(
    items: List<BottomNavItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = bottomNavItem.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(IconSize)
                        )

                        Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))

                        Text(text = bottomNavItem.text)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor =  MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.shimmer),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}


data class BottomNavItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomPreview(){
    BelajarcomposenewsappsTheme {
        NewsBottomNav(
            items = listOf(
                BottomNavItem(
                    icon = R.drawable.ic_home, text = "Home"
                ),
                BottomNavItem(
                    icon = R.drawable.ic_search, text = "Search"
                ),
                BottomNavItem(
                    icon = R.drawable.ic_bookmark, text = "Bookmark"
                )
            ),
            selected = 0,
            onItemClick = {}
        )
    }
}