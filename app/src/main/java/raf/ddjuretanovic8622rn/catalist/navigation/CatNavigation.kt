package raf.ddjuretanovic8622rn.catalist.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import raf.ddjuretanovic8622rn.catalist.cats.list.breeds

@Composable
fun CatNavigation() {
    val navController = rememberNavController()
    val TAG = "CatNavigation"
    NavHost(
        navController = navController,
        startDestination = "breeds"
    ) {
        breeds(
            route = "breeds",
            onBreedClick = {
                Log.i(TAG, "Navigating to: ${"breeds/$it"}")
            }
        )
    }
}