package com.justadeveloper96.pokedex.core.api

import com.justadeveloper96.pokedex.core.api.model.AppServerError
import com.justadeveloper96.pokedex.core.utils.loadJSONFromAsset
import com.justadeveloper96.pokedex.core.utils.loadModelFromAsset
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException

class ApiCallExtensionKtTest : StringSpec({

    "success case" {
        val body = "Abc"
        val code = 200
        execute { Response.success(code, body) } shouldBe Success(body, code)
    }

    "success case 2" {
        val body = "Abc"
        val code = 200

        val modifiedBody = mapOf("data" to body)
        execute(
            { Response.success(code, body) },
            { result -> mapOf("data" to result) }
        ) shouldBe Success(modifiedBody, code)
    }

    "unsuccessful case" {
        val errorJson =
            loadJSONFromAsset(ClassLoader.getSystemClassLoader(), "error/badRequest.json")
        val errorModel = loadModelFromAsset<AppServerError>(
            ClassLoader.getSystemClassLoader(),
            "error/badRequest.json"
        )
        val exception =
            HttpException(Response.error<String>(404, ResponseBody.create(null, errorJson)))
        val expectedError = Unsuccessful<String>(
            error = errorModel.error,
            code = 404,
            message = errorModel.error
        )
        execute<String, Map<String, String>>(
            { throw exception },
            { result -> mapOf("data" to result) }
        ) shouldBe expectedError
    }

    "error case" {
        val exception = SocketException()
        val expectedError = NetworkException<String>(
            message = ApiUtils.ERR_TIMEOUT,
            exception = exception
        )
        execute<String, Map<String, String>>(
            { throw exception },
            { result -> mapOf("data" to result) }
        ) shouldBe expectedError
    }
})
