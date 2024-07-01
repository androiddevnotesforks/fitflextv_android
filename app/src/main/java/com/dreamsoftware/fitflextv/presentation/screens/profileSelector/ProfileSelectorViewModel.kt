package com.dreamsoftware.fitflextv.presentation.screens.profileSelector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSelectorViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ProfileSelectorUiState> by lazy {
        MutableStateFlow(ProfileSelectorUiState())
    }
    val state = _state.asStateFlow()

    init {
        getUserProfiles()
    }

    private fun getUserProfiles() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = false,
                    profiles = userRepository.getUserProfiles().map {
                        it.toProfileUiState()
                    },
                )
            }
        }
    }

    private fun com.dreamsoftware.fitflextv.data.entities.Profile.toProfileUiState() = ProfileUiState(
        id = id,
        name = name,
        avatar = avatar
    )
}