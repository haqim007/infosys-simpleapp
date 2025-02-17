package dev.haqim.simpleapp.data.remote.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.haqim.simpleapp.data.remote.response.ApiError
import retrofit2.HttpException

internal fun HttpException.toErrorMessage(): String {
    return if (this.code() == 404) {
        ResultCode.NOT_FOUND.message
    } else if (!this.response()?.errorBody()?.contentType().toString().contains("application/json")){
        ResultCode.INVALID_JSON_RESPONSE.message
    } else {
        try {
            val errorJson = this.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorJson, ApiError::class.java)
            errorResponse?.message ?: "Error"
        } catch (e: JsonSyntaxException) {
            ResultCode.UNKNOWN_ERROR.message
        }
    }
}