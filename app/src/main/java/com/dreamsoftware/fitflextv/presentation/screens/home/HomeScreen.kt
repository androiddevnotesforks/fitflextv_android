package com.dreamsoftware.fitflextv.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.presentation.screens.home.components.Categories
import com.dreamsoftware.fitflextv.presentation.screens.home.components.Sessions
import com.dreamsoftware.fitflextv.presentation.screens.home.components.TrainingsRecommended

@OptIn(ExperimentalTvMaterial3Api::class)
val carouselSaver =
    Saver<CarouselState, Int>(save = { it.activeItemIndex }, restore = { CarouselState(it) })

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartSessionCLick: (id: String) -> Unit,
    onCardClick: (id: String) -> Unit,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val carouselState = rememberSaveable(saver = carouselSaver) { CarouselState(0) }

    HomeContent(
        state = state,
        carouselState = carouselState,
        onClick = { id ->
            onStartSessionCLick(id)
        },
        onCardClick = { id ->
            onCardClick(id)

        }

    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun HomeContent(
    state: HomeUiState,
    onClick: (id: String) -> Unit,
    carouselState: CarouselState,
    onCardClick: (id: String) -> Unit,
) {
    TvLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .semantics { contentDescription = "Home Screen" },
        verticalArrangement = Arrangement.spacedBy(40.dp),
        contentPadding = PaddingValues(vertical = 40.dp)
    ) {
        item {
            Sessions(
                sessions = state.sessions,
                padding = PaddingValues(horizontal = 32.dp),
                onStartSessionCLick = onClick,
                carouselState = carouselState,
                modifier = Modifier
                    .height(340.dp)
                    .fillMaxWidth()
            )
        }
        item {
            Categories(
                categories = state.categories,
                onClick = onCardClick
            )
        }
        item {
            TrainingsRecommended(
                state = state.recommended,
                onClick = onCardClick
            )
        }
    }
}
