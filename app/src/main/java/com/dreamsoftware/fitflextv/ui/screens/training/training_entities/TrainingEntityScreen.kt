package com.dreamsoftware.fitflextv.ui.screens.training.training_entities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.screens.training.training_entities.composables.ChallengeTabs
import com.dreamsoftware.fitflextv.ui.screens.training.training_entities.composables.RoundedGradientImage
import com.dreamsoftware.fitflextv.ui.screens.training.training_entities.composables.TrainingEntityDetails

@Composable
fun TrainingEntityScreen(
    viewModel: TrainingEntityViewModel = hiltViewModel(),
    onClickStart: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    TrainingEntityContent(
        state = state, onClickStart = onClickStart
    )

}

@Composable
private fun TrainingEntityContent(
    state: TrainingEntityUiState,
    onClickStart: () -> Unit,
) {
    var isChallengeTabsVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = !isChallengeTabsVisible) {
            Box(contentAlignment = Alignment.BottomStart) {
                RoundedGradientImage(imageUrl = state.imageUrl)
                TrainingEntityDetails(
                    state = state,
                    onClickStart = onClickStart,
                    onClickSecondaryButton = {},
                    onClickChallengesPlan = { isChallengeTabsVisible = true },
                    onClickRoutineFavourite = {}
                )
            }
        }
        AnimatedVisibility(visible = isChallengeTabsVisible) {
            ChallengeTabs(
                state = state,
                onClickBackChallenge = { isChallengeTabsVisible = false },
                onClickCard = {}
            )
        }

    }
}