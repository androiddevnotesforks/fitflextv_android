package com.dreamsoftware.fitflextv.presentation.screens.training.training_entities

sealed class TrainingEntityPages {
    data object EntityDetails : TrainingEntityPages()
    data object EntityTabs : TrainingEntityPages()
}