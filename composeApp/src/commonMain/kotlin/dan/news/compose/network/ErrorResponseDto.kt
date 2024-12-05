package dan.news.compose.network

import kotlinx.serialization.SerialName

data class ErrorResponseDto(
    @SerialName("success")
    val success: Boolean,

    @SerialName("status_code")
    val statusCode: Int,

    @SerialName("status_message")
    val statusMessage: String
)

data class ErrorResponse(
    val success: Boolean,
    val statusCode: Int,
    val statusMessage: String
) : Exception()