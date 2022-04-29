/*
 * Copyright 2021 Harshith Shetty (justadeveloper96@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.justadeveloper96.pokedex.core.api

import com.justadeveloper96.pokedex.helpers.api.NetworkResult
import com.justadeveloper96.pokedex.helpers.api.State

sealed class AppNetworkResult<T>(
    override val data: T? = null,
    override val code: Int? = null,
    override val message: String? = null,
    override val error: String? = null,
    override val exception: Exception? = null,
    override val state: State
) : NetworkResult<T, String>(
    data, code, message, exception, error, state
) {

    fun <R> map(transform: (T) -> R): AppNetworkResult<R> {
        return when (this) {
            is Success -> {
                Success(transform(data), code, message)
            }
            is Loading -> {
                Loading(data?.let { transform(it) })
            }
            is Unsuccessful -> {
                Unsuccessful(data?.let { transform(it) }, code, error, message)
            }
            is NetworkException -> {
                NetworkException(data?.let { transform(it) }, exception, message)
            }
        }
    }

    fun <T> modify(data: T): AppNetworkResult<T> {
        return when (this) {
            is Success -> {
                Success(data, code, message)
            }
            is Loading -> {
                Loading(data)
            }
            is Unsuccessful -> {
                Unsuccessful(data, code, error, message)
            }
            is NetworkException -> {
                NetworkException(data, exception, message)
            }
        }
    }
}

data class Success<T>(
    override val data: T,
    override val code: Int,
    override val message: String? = null
) :
    AppNetworkResult<T>(data, code = code, message = message, state = State.SUCCESS)

data class Unsuccessful<T>(
    override val data: T? = null,
    override val code: Int,
    override val error: String,
    override val message: String
) : AppNetworkResult<T>(
    data,
    code = code,
    error = error,
    message = message,
    state = State.UNSUCCESSFUL
)

data class NetworkException<T>(
    override val data: T? = null,
    override val exception: Exception,
    override val message: String
) : AppNetworkResult<T>(data, exception = exception, message = message, state = State.ERROR)

data class Loading<T>(override val data: T? = null) :
    AppNetworkResult<T>(data, state = State.LOADING)
