package com.pluu.sample.navigation.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
    import logcat.logcat

@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "First",
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = "First") { entry ->
            entry.printViewModel(navController)
            SampleUi(
                title = "First",
                onClick = {
                    navController.navigate("Second?test=abcd")
                }
            )
        }
        composable(route = "Second?test={test}",
            arguments = listOf(
                navArgument("test") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            entry.printViewModel(navController)
            SampleUi(title = "Second")
        }
    }
}

@Composable
private fun NavBackStackEntry.printViewModel(navController: NavController) {
    val parentId = destination.parent!!.id
    val parentEntry = remember {
        navController.getBackStackEntry(parentId)
    }
    val viewModelStoreOwner: ViewModelStoreOwner = parentEntry
    val test1: SampleViewModel = viewModel(viewModelStoreOwner)
    val test2: SampleViewModel = hiltViewModel(viewModelStoreOwner)
    logcat {
        "${destination.route} : ViewModelStoreOwner=${viewModelStoreOwner.hashCode()}, vm1=${test1.hashCode()}, vm2=${test2.hashCode()}"
    }
}
