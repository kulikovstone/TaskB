package com.mkulikov.taskb.data

import com.mkulikov.taskb.data.model.Post
import com.mkulikov.taskb.network.TestApiService

interface ITestNetworkRepository {
    suspend fun loadPosts(): Result<List<Post>>
}

class TestNetworkRepository(
    private val testApiService: TestApiService
) : ITestNetworkRepository {

    override suspend fun loadPosts(): Result<List<Post>> {
        return testApiService.getPosts()
    }
}