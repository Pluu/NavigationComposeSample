package com.pluu.sample.navigation.compose

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import logcat.LogPriority.WARN
import logcat.logcat

fun NavController.navigateAndArgument(
    route: String,
    args: List<Pair<String, Any>>? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    // ⓵ navigate 호출하면, route의 NavBackStackEntry가 backQueue에 추가됨
    navigate(route, navOptions, navigatorExtras)

    if (args == null || args.isEmpty()) {
        return
    }

    // ⓶ backQueue.lastOrNull()의 결과로 route의 NavBackStackEntry가 반환
    val bundle = backQueue.lastOrNull()?.arguments
    if (bundle != null) {
        // ⓷ Bundle 타입의 arguments가 Null이 아니면, 전달하려는 데이터를 추가한다.
        bundle.putAll(bundleOf(*args.toTypedArray()))
    } else {
        logcat(WARN) {
            "The last argument of NavBackStackEntry is null."
        }
    }
}