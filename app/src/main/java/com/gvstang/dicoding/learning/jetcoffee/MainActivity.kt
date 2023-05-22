@file:OptIn(ExperimentalMaterial3Api::class)

package com.gvstang.dicoding.learning.jetcoffee

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gvstang.dicoding.learning.jetcoffee.model.BottomBarItem
import com.gvstang.dicoding.learning.jetcoffee.model.Menu
import com.gvstang.dicoding.learning.jetcoffee.model.dummyBestSellerMenu
import com.gvstang.dicoding.learning.jetcoffee.model.dummyCategory
import com.gvstang.dicoding.learning.jetcoffee.model.dummyMenu
import com.gvstang.dicoding.learning.jetcoffee.ui.component.CategoryItem
import com.gvstang.dicoding.learning.jetcoffee.ui.component.HomeSection
import com.gvstang.dicoding.learning.jetcoffee.ui.component.MenuItem
import com.gvstang.dicoding.learning.jetcoffee.ui.component.SearchBar
import com.gvstang.dicoding.learning.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JetCoffeeApp() {
    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Banner()
            HomeSection(
                title = stringResource(R.string.section_category)
            ) {
                CategoryRow()
            }
            HomeSection(
                title = stringResource(R.string.section_favorite_menu)
            ) {
                MenuRow(listMenu = dummyMenu)
            }
            HomeSection(
                title = stringResource(R.string.section_best_seller_menu)
            ) {
                MenuRow(listMenu = dummyBestSellerMenu)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title} ) { menu ->
            MenuItem(menu = menu)
        }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dummyCategory, key = {it.textCategory}) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(0) }
    
    NavigationBar(
        modifier = modifier
    ) {
        
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(id = R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            )
        )

        navigationItems.forEachIndexed { index, bottomBarItem ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        imageVector = bottomBarItem.icon,
                        contentDescription = bottomBarItem.title
                    )
                },
                label = {
                    Text(text = bottomBarItem.title)
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun JetCoffeAppPreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}