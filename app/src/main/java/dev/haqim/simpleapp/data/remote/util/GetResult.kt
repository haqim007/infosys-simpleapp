package dev.haqim.simpleapp.data.remote.util

import com.google.gson.JsonElement
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

suspend fun <T> getResult(callback: suspend () -> T): Result<T> {
    return try {
        Result.success(callback())
    }
    catch (e: UnknownHostException){
        Result.failure(CustomThrowable(code = ResultCode.UNKNOWN_HOST, message = ResultCode.UNKNOWN_HOST.message))
    } catch (e: HttpException){
        Result.failure(CustomThrowable(code = ResultCode.HTTP_EXCEPTION, message = e.toErrorMessage()))
    } catch (e: ConnectException) {
        Result.failure(CustomThrowable(code = ResultCode.UNABLE_CONNECT, message = ResultCode.UNABLE_CONNECT.message))
    } catch (e: SocketTimeoutException) {
        Result.failure(CustomThrowable(code = ResultCode.UNABLE_CONNECT, message = ResultCode.UNABLE_CONNECT.message))
    }catch (e: SSLHandshakeException){
        Result.failure(CustomThrowable(code = ResultCode.UNABLE_CONNECT, message = ResultCode.UNABLE_CONNECT.message))
    } catch (e: Exception){
        Result.failure(
            CustomThrowable(
                code = ResultCode.UNKNOWN_ERROR,
                message = e.message
            )
        )
    }
}


class CustomThrowable(
    val code: ResultCode = ResultCode.SUCCESS,
    val dataJson: JsonElement? = null,
    override val message: String?
) : Throwable(message)