package dev.haqim.simpleapp.data.mechanism

import dev.haqim.simpleapp.data.remote.util.ResultCode

/**
 * Resource
 *
 * @param ResultType Represent data type for the expected result
 * @property data
 * @property message contains message representing the error
 * @property httpCode
 * @constructor Create empty Resource
 */
sealed class Resource<ResultType>(
    val data: ResultType? = null,
    val message: String? = null,
    val code: ResultCode? = null
) {
    class Success<ResultType>(data: ResultType): Resource<ResultType>(data)

    class Loading<ResultType>(data: ResultType? = null): Resource<ResultType>(data)

    class Error<ResultType>(
        message: String,
        data: ResultType? = null,
        code: ResultCode? = null
    ): Resource<ResultType>(data, message, code)

    class Idle<ResultType>: Resource<ResultType>()
}