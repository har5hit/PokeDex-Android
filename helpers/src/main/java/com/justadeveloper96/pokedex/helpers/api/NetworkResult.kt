/*
 * Copyright 2020 Harshith Shetty (justadeveloper96@gmail.com)
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

package com.justadeveloper96.pokedex.helpers.api

sealed class NetworkResult<T>(open val data: T?) {
    fun <E> modify(data: E): NetworkResult<E> {
        return when (this) {
            is Success -> {
                Success(data)
            }
            is Loading -> {
                Loading(data)
            }
            is Error -> {
                Error(data)
            }
        }
    }
}

data class Success<T>(override val data: T) : NetworkResult<T>(data)
data class Error<T>(override val data: T? = null, val message: String? = null) :
    NetworkResult<T>(data)

data class Loading<T>(override val data: T? = null) : NetworkResult<T>(data)