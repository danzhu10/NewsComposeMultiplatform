package dan.news.compose.network

import dan.news.compose.common.Constants
import dan.news.compose.httpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object BASEURL {
    val API_HOST = "https://newsapi.org/v2/"
}

abstract class KtorService {
    companion object {
        val client = httpClient {
            engine {
                pipelining = true
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
            }
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {

                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

            }
            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status.value == 500) {
                        throw Exception("Internal Server Error")
                    }
                }
            }
            defaultRequest {
                // Automatically add the Authorization header to all requests
                header(HttpHeaders.Authorization, Constants.API_KEY)
            }
        }
    }
}

class CustomHttpLogger : Logger {
    override fun log(message: String) {
        co.touchlab.kermit.Logger.d("loggerTag $message") // Or whatever logging system you want here
    }
}