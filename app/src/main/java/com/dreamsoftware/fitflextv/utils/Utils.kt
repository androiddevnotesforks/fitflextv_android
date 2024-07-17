package com.dreamsoftware.fitflextv.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val error: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> {
    Result.Success(it)
}.onStart {
    emit(Result.Loading)
}.catch {
    emit(Result.Error(it))
}

interface HasValue {
    val value: String
}

inline fun <reified T : Enum<T>> enumValueOfOrDefault(value: String, default: T): T {
    return enumValues<T>().find {
        (it as? HasValue)?.value == value
    } ?: default
}

inline fun <T1 : Any, T2 : Any, R> combinedLet(value1: T1?, value2: T2?, block: (T1, T2) -> R): R? =
    if (value1 != null && value2 != null) {
        block(value1, value2)
    } else {
        null
    }