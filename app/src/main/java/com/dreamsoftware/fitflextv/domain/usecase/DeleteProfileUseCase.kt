package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class DeleteProfileUseCase(
    private val profileRepository: IProfilesRepository
): BaseUseCaseWithParams<DeleteProfileUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean =
        profileRepository.deleteProfile(params.profileId)

    data class Params(
        val profileId: String
    )
}