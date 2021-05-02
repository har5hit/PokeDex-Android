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

import com.google.gson.Gson
import com.justadeveloper96.pokedex.core.api.model.AppServerError
import com.justadeveloper96.pokedex.helpers.extensions.fromJson
import retrofit2.Response

inline fun <reified T, reified E> execute(
    serviceCall: () -> Response<T>,
    transform: (T) -> E
): AppNetworkResult<E> {
    return try {
        val data = serviceCall()
        if (data.isSuccessful) {
            Success(data = transform(data.body()!!), code = data.code())
        } else {
            val code = data.code()
            return try {
                val errorString =
                    data.errorBody()?.string()
                        ?.let { Gson().fromJson<AppServerError>(it) }?.error
                        ?: ApiUtils.ERR_DEFAULT_MSG
                Unsuccessful(
                    error = errorString,
                    code = code,
                    message = errorString
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Unsuccessful(
                    error = ApiUtils.getErrorMessage(e),
                    code = code,
                    message = ApiUtils.getErrorMessage(e)
                )
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        NetworkException(message = ApiUtils.getErrorMessage(e), exception = e)
    }
}

inline fun <reified T> execute(serviceCall: () -> Response<T>): AppNetworkResult<T> {
    return execute(serviceCall, { it })
}
