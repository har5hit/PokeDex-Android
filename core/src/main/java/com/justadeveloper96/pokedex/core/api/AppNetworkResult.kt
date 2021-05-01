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
    override val exception: Exception? = null,
    override val error: String? = null,
    override val state: State
) : NetworkResult<T, String>(
    data, code, exception, error, state
) {
    fun <T> modify(data: T): AppNetworkResult<T> {
        return when (this) {
            is Success -> {
                Success(data, code)
            }
            is Loading -> {
                Loading(data)
            }
            is Unsuccessful -> {
                Unsuccessful(data, code, error)
            }
            is NetworkException -> {
                NetworkException(data, exception, error)
            }
        }
    }
}

data class Success<T>(override val data: T, override val code: Int) :
    AppNetworkResult<T>(data, code = code, state = State.SUCCESS)

data class NetworkException<T>(
    override val data: T? = null,
    override val exception: Exception,
    override val error: String
) : AppNetworkResult<T>(data, exception = exception, error = error, state = State.ERROR)

data class Unsuccessful<T>(
    override val data: T? = null,
    override val code: Int,
    override val error: String
) : AppNetworkResult<T>(data, error = error, state = State.UNSUCCESSFUL)

data class Loading<T>(override val data: T? = null) :
    AppNetworkResult<T>(data, state = State.LOADING)
