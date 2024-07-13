package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class WorkoutRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val workoutMapper: IOneSideMapper<Map<String, Any?>, WorkoutDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IWorkoutRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "workouts"
        const val CATEGORY_FIELD = "category"
    }

    @Throws(FetchRemoteWorkoutsException::class)
    override suspend fun getWorkouts(): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutsException("An error occurred when trying to fetch workouts", ex)
    }

    @Throws(FetchRemoteWorkoutByIdException::class)
    override suspend fun getWorkoutById(id: String): WorkoutDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).document(id).get() },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutByIdException("An error occurred when trying to fetch the workout with ID $id", ex)
    }

    @Throws(FetchRemoteWorkoutByCategoryException::class)
    override suspend fun getWorkoutByCategory(id: String): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(CATEGORY_FIELD, id)
                    .get()
            },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutByCategoryException("An error occurred when trying to fetch workouts by category", ex)
    }
}