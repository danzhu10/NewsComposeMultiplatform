package dan.news.compose.network

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): ResultState<T> {
    return try {
        ResultState.Loading
        ResultState.Success(apiCall.invoke())
    } catch (e: RedirectResponseException) {
        val error = parseNetworkError(e.response.body())
        ResultState.Failure(exception = error)
    } catch (e: ClientRequestException) {
        val error = parseNetworkError(e.response.body())
        ResultState.Failure(exception = error)
    } catch (e: ServerResponseException) {
        val error = parseNetworkError(e.response.body())
        ResultState.Failure(exception = error)
    } catch (e: UnresolvedAddressException) {
        val error = parseNetworkError(exception = e)
        ResultState.Failure(exception = error)
    } catch (e: Exception) {
        val error = parseNetworkError(exception = e)
        ResultState.Failure(exception = error)
    }
}

internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    throw errorResponse?.body<ErrorResponseDto>()?.toDomain() ?: ErrorResponse(
        success = false,
        statusCode = 0,
        statusMessage = exception?.message ?: "Error"
    )
}

fun ErrorResponseDto.toDomain(): ErrorResponse {
    return ErrorResponse(
        success = this.success,
        statusCode = this.statusCode,
        statusMessage = this.statusMessage
    )
}