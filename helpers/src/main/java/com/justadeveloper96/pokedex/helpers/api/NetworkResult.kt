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

sealed class NetworkResult<T> {
    fun <E> modify(map: (T) -> E): NetworkResult<E> {
        when (this) {
            is Success -> {
                return Success(map(this.data))
            }
            is Error -> {
                return Error(this.message)
            }
        }
    }
}

data class Success<T>(val data: T) : NetworkResult<T>()

data class Error<T>(val message: String? = null) : NetworkResult<T>()