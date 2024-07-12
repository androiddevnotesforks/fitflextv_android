package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingByIdUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingByIdUseCase.Params, ITrainingProgramBO>() {

    override suspend fun onExecuted(params: Params): ITrainingProgramBO = with(params) {
        trainingRepository.getTrainingById(id = id, type = type)
    }

    data class Params(
        val id: String,
        val type: TrainingTypeEnum
    )
}