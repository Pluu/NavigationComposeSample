package com.pluu.sample.navigation.compose

import android.net.Uri
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
import com.pluu.sample.navigation.compose.model.SampleParcelableModel
import com.pluu.sample.navigation.compose.model.SampleSerializableModel
import logcat.logcat

sealed class Screen(val route: String) {
    object First : Screen("First")
    object Second : Screen("Second?test={test}") {
        const val KEY_NAME_TEST = "test"
        fun createRoute(value: String) = "Second?test=${value}"
    }

    object Third : Screen("Third?key1={test_serialize}&key2={test_parcelable}") {
        const val KEY_NAME_SERIALIZE = "test_serialize"
        const val KEY_NAME_PARCELABLE = "test_parcelable"

        fun createRoute(
            value1: SampleSerializableModel,
            value2: SampleParcelableModel
        ): String {
            val value1Serialize = CustomModelAdapter.toSerialize(value1)
            val value2Serialize = CustomModelAdapter.toSerialize(value2)
            return "Third?key1=${Uri.encode(value1Serialize)}&key2=${Uri.encode(value2Serialize)}"
        }
    }
}

@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.First.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = Screen.First.route) { entry ->
            entry.PrintViewModel(navController)
            SampleFirstUi(
                onSecondClick = {
                    navController.navigate(
                        Screen.Second.createRoute("abcd")
                    )
                },
                onThirdClick = {
                    navController.navigate(
                        Screen.Third.createRoute(
                            SampleSerializableModel("abcd"),
                            SampleParcelableModel("efgh")
                        )
                    )
                }
            )
        }
        composable(route = Screen.Second.route,
            arguments = listOf(
                navArgument(Screen.Second.KEY_NAME_TEST) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            entry.PrintViewModel(navController)
            SampleUi(title = "Second")
        }
        composable(route = Screen.Third.route,
            arguments = listOf(
                navArgument(Screen.Third.KEY_NAME_SERIALIZE) {
                    type = createSerializableNavType<SampleSerializableModel>()
                },
                navArgument(Screen.Third.KEY_NAME_PARCELABLE) {
                    type = createParcelableNavType<SampleParcelableModel>()
                }
            )
        ) { entry ->
            entry.PrintViewModel(navController)
            val arguments = requireNotNull(entry.arguments)
            val sample1 =
                arguments.getSerializable(Screen.Third.KEY_NAME_SERIALIZE) as SampleSerializableModel
            val sample2 =
                arguments.getParcelable<SampleParcelableModel>(Screen.Third.KEY_NAME_PARCELABLE)!!
            logcat(tag = "logger") {
                "Third : Serializable=${sample1}, Parcelable=${sample2}"
            }
            SampleUi(title = "Third")
        }
    }
}

@Composable
private fun NavBackStackEntry.PrintViewModel(navController: NavController) {
    val parentId = destination.parent!!.id
    val parentEntry = remember {
        navController.getBackStackEntry(parentId)
    }
    val viewModelStoreOwner: ViewModelStoreOwner = parentEntry
    val test1: SampleViewModel = viewModel(viewModelStoreOwner)
    val test2: SampleViewModel = hiltViewModel(viewModelStoreOwner)
    logcat(tag = "logger") {
        "${destination.route} : ViewModelStoreOwner=${viewModelStoreOwner.hashCode()}, vm1=${test1.hashCode()}, vm2=${test2.hashCode()}"
    }
}
