package id.android.belajarcomposenewsapps.presentation.navigation

sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "searchScreen")
    object BookMarkScreen : Route(route = "bookMarkScreen")
    object DetailsScreen : Route(route = "detailScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigatorScreen : Route(route = "newsNavigator")

    object LearnTwoMain : Route(route = "learnTwoMain")
    object AuthScreen : Route(route = "authScreen")
}