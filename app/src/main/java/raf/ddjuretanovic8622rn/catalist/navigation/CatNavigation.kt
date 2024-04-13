package raf.ddjuretanovic8622rn.catalist.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import raf.ddjuretanovic8622rn.catalist.cats.detail.breed
import raf.ddjuretanovic8622rn.catalist.cats.list.breeds


private const val TAG = "CatNavigation"

@Composable
fun CatNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "breeds"
    ) {
        breeds(route = "breeds", onBreedClick = {
            Log.i(TAG, "Navigating to: ${"breeds/$it"}")
            navController.navigate(route = "breeds/$it")
        })
        breed(route = "breeds/{breedId}", arguments = listOf(
            navArgument(name = "breedId") {
                nullable = false
            }
        ),
            onClose = {
                navController.navigateUp()
            },
        )
    }
}