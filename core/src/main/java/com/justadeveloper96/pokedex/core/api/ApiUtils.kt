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

import java.net.SocketException
import java.net.UnknownHostException

object ApiUtils {

    const val ERR_DEFAULT_MSG = "Something went wrong! Please try again later."
    const val ERR_NO_INTERNET = "No Internet Connection!"
    const val ERR_TIMEOUT = "Connection Timeout"

    @JvmStatic
    fun getErrorMessage(t: Exception): String {
        if (t is UnknownHostException) {
            return ERR_NO_INTERNET
        }

        if (t is SocketException) {
            return ERR_TIMEOUT
        }

        return ERR_DEFAULT_MSG
    }
}
