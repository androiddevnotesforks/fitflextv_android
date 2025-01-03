package com.dreamsoftware.fitflextv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.fitflextv.ui.screens.category.CategoryDetailScreen
import com.dreamsoftware.fitflextv.ui.screens.favorites.FavoritesScreen
import com.dreamsoftware.fitflextv.ui.screens.home.HomeScreen
import com.dreamsoftware.fitflextv.ui.screens.instructordetail.InstructorDetailScreen
import com.dreamsoftware.fitflextv.ui.screens.moreoptions.MoreOptionsScreen
import com.dreamsoftware.fitflextv.ui.screens.player.audio.AudioPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.player.video.VideoPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.settings.SettingsScreen
import com.dreamsoftware.fitflextv.ui.screens.subscription.SubscriptionScreen
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingScreen
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailScreen

@Composable
fun DashboardNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            with(navController) {
                HomeScreen(
                    onOpenTrainingCategory = { id ->
                        navigate(Screen.CategoryDetail.buildRoute(id))
                    },
                    onGoToSubscriptions = {
                        navigate(Screen.Subscription.route)
                    },
                    onOpenTrainingProgram = {  id, type ->
                        navigate(Screen.TrainingDetail.buildRoute(id, type))
                    }
                )
            }
        }
        composable(Screen.Training.route) {
            TrainingScreen(
                onGoToTrainingProgramDetail = { id, type ->
                    navController.navigate(Screen.TrainingDetail.buildRoute(id, type))
                }
            )
        }
        composable(Screen.Favorite.route) {
            with(navController) {
                FavoritesScreen(
                    onBackPressed = {
                        popBackStack()
                    },
                    onOpenTrainingProgramDetail = { id, type ->
                        navigate(Screen.TrainingDetail.buildRoute(id, type))
                    }
                )
            }
        }

        composable(Screen.CategoryDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.CategoryDetail::parseArgs)?.let { args ->
                with(navController) {
                    CategoryDetailScreen(
                        args = args,
                        onOpenTrainingProgramDetail = {id, type ->
                            navigate(Screen.TrainingDetail.buildRoute(id, type))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }

        composable(Screen.VideoPlayer.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.VideoPlayer::parseArgs)?.let { args ->
                with(navController) {
                    VideoPlayerScreen(args = args) {
                        popBackStack()
                    }
                }
            }
        }
        composable(Screen.AudioPlayer.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.AudioPlayer::parseArgs)?.let { args ->
                with(navController) {
                    AudioPlayerScreen(args = args) {
                        popBackStack()
                    }
                }
            }
        }
        composable(Screen.Settings.route) {
            with(navController) {
                SettingsScreen(
                    onGoToSubscriptions = {
                        navigate(Screen.Subscription.route)
                    },
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }
        composable(Screen.TrainingDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.TrainingDetail::parseArgs)?.let { args ->
                with(navController) {
                    TrainingDetailScreen(
                        args = args,
                        onPlayingTrainingProgram = { id, type ->
                            navigate(Screen.VideoPlayer.buildRoute(id, type))
                        },
                        onOpenMoreDetails = { id, type ->
                            navigate(Screen.MoreOptions.buildRoute(id, type))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }
        composable(Screen.MoreOptions.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.MoreOptions::parseArgs)?.let { args ->
                with(navController) {
                    MoreOptionsScreen(
                        args = args,
                        onPlayTrainingProgram = { id, type ->
                            navigate(Screen.VideoPlayer.buildRoute(id, type))
                        },
                        onPlayTrainingSong = {
                            navigate(Screen.AudioPlayer.buildRoute(it))
                        },
                        onOpenInstructorDetail = {
                            navigate(Screen.InstructorDetail.buildRoute(it))
                        },
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }

        composable(Screen.InstructorDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.InstructorDetail::parseArgs)?.let { args ->
                with(navController) {
                    InstructorDetailScreen(
                        args = args,
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }
        composable(Screen.Subscription.route) {
            with(navController) {
                SubscriptionScreen(
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }
    }
}