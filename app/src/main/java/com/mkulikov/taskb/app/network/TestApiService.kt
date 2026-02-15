package com.mkulikov.taskb.app.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.serialization.SerializationException

class TestApiService(
    private val client: HttpClient
) {

//    suspend fun getPosts(): Result<List<Post>> {
//        return try {
//            val response = client.get {
//                url("https://jsonplaceholder.typicode.com/posts")
//            }
//            Result.success(response.body<List<Post>>())
//        } catch (e: SerializationException) {
//            Result.failure(Exception("Ошибка сериализации: ${e.message}"))
//        } catch (e: Exception) {
//            Result.failure(Exception("Ошибка сети: ${e.message}"))
//        }
//    }
}