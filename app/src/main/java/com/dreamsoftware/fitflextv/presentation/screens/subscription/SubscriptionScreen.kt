package com.dreamsoftware.fitflextv.presentation.screens.subscription

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.dreamsoftware.fitflextv.data.entities.Subscription
import com.dreamsoftware.fitflextv.presentation.common.JFFilledButton
import com.dreamsoftware.fitflextv.presentation.common.JFOutlineButton
import com.dreamsoftware.fitflextv.presentation.screens.subscription.composable.SubscriptionHeadline
import com.dreamsoftware.fitflextv.presentation.screens.subscription.composable.SubscriptionOptions
import com.google.jetfit.R

@Composable
fun SubscriptionScreen(
    viewModel: SubscriptionViewModel = hiltViewModel(),
    onSubscribeClick: () -> Unit,
    onRestorePurchasesClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val instructorImage by viewModel.instructorImageState.collectAsState()
    val selectedSubscription by viewModel.selectedSubscriptionOption.collectAsState()

    SubscriptionContent(
        state = state,
        instructorImage = instructorImage,
        selectedSubscription = selectedSubscription,
        updateSubscriptionOption = viewModel::updateSelectedSubscriptionOption,
        onSubscribeClick = onSubscribeClick,
        onRestorePurchasesClick = onRestorePurchasesClick,
    )
}

@Composable
private fun SubscriptionContent(
    state: SubscriptionUiState,
    instructorImage: String,
    selectedSubscription: Subscription,
    updateSubscriptionOption: (Subscription) -> Unit,
    onSubscribeClick: () -> Unit,
    onRestorePurchasesClick: () -> Unit
) {

    when (state) {
        SubscriptionUiState.Error -> Log.d("Tarek", "Error")
        SubscriptionUiState.Loading -> Log.d("Tarek", "Loading")
        is SubscriptionUiState.Ready -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "Subscription Screen" },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 324.dp, height = 452.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.White.copy(0.07f)),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp)),
                    painter = rememberAsyncImagePainter(model = instructorImage),
                    contentDescription = "training photo",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(28.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    SubscriptionHeadline()
                    SubscriptionOptions(
                        subscriptionOptions = state.subscriptionOptions,
                        formatPeriodTime = state::formatPeriodTime,
                        formatPeriodTimeAndPrice = state::formatPeriodTimeAndPrice,
                        selectedSubscription = selectedSubscription,
                        updateSelectedSubscriptionOption = updateSubscriptionOption
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        JFFilledButton(
                            modifier = Modifier.width(184.dp),
                            buttonText = stringResource(R.string.subscribe_now),
                            onClick = { onSubscribeClick() }
                        )
                        JFOutlineButton(
                            modifier = Modifier.width(184.dp),
                            buttonText = stringResource(R.string.restore_purchases),
                            onClick = { onRestorePurchasesClick() }
                        )
                    }
                }
            }
        }
    }
}
