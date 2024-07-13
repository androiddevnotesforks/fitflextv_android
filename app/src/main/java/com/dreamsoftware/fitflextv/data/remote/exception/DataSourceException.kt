package com.dreamsoftware.fitflextv.data.remote.exception

open class DataSourceException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Auth Data Source
class AuthException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class SignInException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class SignUpException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Routines
class FetchRemoteRoutinesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteRoutineByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteRoutineByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Series
class FetchRemoteSeriesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteSeriesByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteSeriesByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Categories
class FetchRemoteCategoriesException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteCategoryByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)

// Workout
class FetchRemoteWorkoutsException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteWorkoutByIdException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)
class FetchRemoteWorkoutByCategoryException(message: String? = null, cause: Throwable? = null): DataSourceException(message, cause)