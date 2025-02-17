package dev.haqim.simpleapp.data.remote.util

enum class ResultCode(val code: Int, val message: String) {
    UNKNOWN_HOST(-1, "unknown host"),
    UNABLE_CONNECT(-2, "unable to connect"),
    INVALID_JSON_RESPONSE(422, "invalid json response"),
    UNKNOWN_ERROR(0, "unknown error"),
    NOT_FOUND(404, "not found"),
    HTTP_EXCEPTION(400, "http exception"),
    SUCCESS(200, "success"),
}