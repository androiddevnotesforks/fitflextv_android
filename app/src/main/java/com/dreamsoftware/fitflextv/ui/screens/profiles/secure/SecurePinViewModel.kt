package com.dreamsoftware.fitflextv.ui.screens.profiles.secure

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.VerifyPinUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fitflextv.utils.combinedLet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecurePinViewModel @Inject constructor(
    private val verifyPinUseCase: VerifyPinUseCase,
    private val selectProfileUseCase: SelectProfileUseCase,
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): BaseViewModel<SecurePinUiState, SecurePinSideEffects>() {


    override fun onGetDefaultState(): SecurePinUiState = SecurePinUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onVerifyPin() {
        with(uiState.value) {
            combinedLet(profileLocked, unlockPin.toIntOrNull()) { profile, pin ->
                executeUseCaseWithParams(
                    useCase = verifyPinUseCase,
                    params = VerifyPinUseCase.Params(profileId = profile.uuid, pin = pin),
                    onSuccess = {
                        onVerifyPinSuccessfully(profile)
                    }
                )
            }
        }
    }

    fun onUnlockPinChanged(unlockPin: String) {
        updateState {
            it.copy(unlockPin = unlockPin)
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profileLocked = profileBO)
        }
    }

    private fun onVerifyPinSuccessfully(profile: ProfileBO) {
        executeUseCaseWithParams(
            useCase = selectProfileUseCase,
            params = SelectProfileUseCase.Params(profile),
            onSuccess = {
                onProfileSelected()
            }
        )
    }

    private fun onProfileSelected() {
        launchSideEffect(SecurePinSideEffects.ProfileUnlockedSuccessfully)
    }
}

data class SecurePinUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val unlockPin: String = String.EMPTY,
    val profileLocked: ProfileBO? = null
): UiState<SecurePinUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SecurePinUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SecurePinSideEffects: SideEffect {
    data object ProfileUnlockedSuccessfully: SecurePinSideEffects
}