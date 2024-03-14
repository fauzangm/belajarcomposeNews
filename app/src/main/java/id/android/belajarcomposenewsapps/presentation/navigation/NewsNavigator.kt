package id.android.belajarcomposenewsapps.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.presentation.bookmark.BookMarkScreen
import id.android.belajarcomposenewsapps.presentation.bookmark.BookMarkViewModel
import id.android.belajarcomposenewsapps.presentation.details.DetailScreen
import id.android.belajarcomposenewsapps.presentation.details.DetailViewModel
import id.android.belajarcomposenewsapps.presentation.home.HomeScreen
import id.android.belajarcomposenewsapps.presentation.home.HomeViewModel
import id.android.belajarcomposenewsapps.presentation.leartwomain.onBoarding.OnboardingScreen
import id.android.belajarcomposenewsapps.presentation.navigation.bottomNav.BottomNavItem
import id.android.belajarcomposenewsapps.presentation.navigation.bottomNav.NewsBottomNav
import id.android.belajarcomposenewsapps.presentation.search.SearchScreen
import id.android.belajarcomposenewsapps.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(

) {

    val bottomNavItem = remember {
        listOf(
            BottomNavItem(
                icon = R.drawable.ic_home, text = "Home"
            ),
            BottomNavItem(
                icon = R.drawable.ic_search, text = "Search"
            ),
            BottomNavItem(
                icon = R.drawable.ic_bookmark, text = "Bookmark"
            )
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookMarkScreen.route -> 2
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookMarkScreen.route
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNav(
                    items = bottomNavItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookMarkScreen.route
                            )
                        }
                    })
            }

        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    },
                    navigateToLearnTwoMain = {
                        navigateToLearnTwoMain(
                            navController
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
                    }

            }
            composable(route = Route.BookMarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookMarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.LearnTwoMain.route) {
                OnboardingScreen(
                    navigateUp = { navController.navigateUp() },
                )
            }


        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

private fun navigateToLearnTwoMain(navController: NavController) {
    navController.navigate(
        route = Route.LearnTwoMain.route
    )
}