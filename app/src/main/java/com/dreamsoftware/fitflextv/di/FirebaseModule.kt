package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.RoutineDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.SeriesDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.mapper.RoutineMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.SeriesMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.UserAuthenticatedMapper
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

// Dagger module for providing Firebase-related dependencies
@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    /**
     * Provides a singleton instance of UserAuthenticatedMapper.
     * @return a new instance of UserAuthenticatedMapper.
     */
    @Provides
    @Singleton
    fun provideUserAuthenticatedMapper(): IOneSideMapper<FirebaseUser, AuthUserDTO> = UserAuthenticatedMapper()

    /**
     * Provides a singleton instance of RoutineMapper.
     * @return a new instance of RoutineMapper.
     */
    @Provides
    @Singleton
    fun provideRoutineMapper(): IOneSideMapper<Map<String, Any?>, RoutineDTO> = RoutineMapper()

    /**
     * Provides a singleton instance of SeriesMapper.
     * @return a new instance of SeriesMapper.
     */
    @Provides
    @Singleton
    fun provideSeriesMapper(): IOneSideMapper<Map<String, Any?>, SeriesDTO> = SeriesMapper()

    /**
     * Provides a singleton instance of FirebaseAuth.
     * @return the default instance of FirebaseAuth.
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()


    /**
     * Provide Firebase Store
     */
    @Provides
    @Singleton
    fun provideFirebaseStore() = Firebase.firestore

    /**
     * Provides a singleton instance of IAuthDataSource.
     * @param userAuthenticatedMapper the IOneSideMapper<FirebaseUser, AuthUserDTO> instance.
     * @param firebaseAuth the FirebaseAuth instance.
     * @return a new instance of AuthDataSourceImpl implementing IAuthDataSource.
     */
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        userAuthenticatedMapper: IOneSideMapper<FirebaseUser, AuthUserDTO>,
        firebaseAuth: FirebaseAuth
    ): IAuthRemoteDataSource = AuthRemoteDataSourceImpl(
        userAuthenticatedMapper,
        firebaseAuth
    )


    @Provides
    @Singleton
    fun provideRoutineRemoteDataSource(
        routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IRoutineDataSource = RoutineDataSourceImpl(
        firestore,
        routineMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideSeriesRemoteDataSource(
        seriesMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISeriesDataSource = SeriesDataSourceImpl(
        firestore,
        seriesMapper,
        dispatcher
    )
}