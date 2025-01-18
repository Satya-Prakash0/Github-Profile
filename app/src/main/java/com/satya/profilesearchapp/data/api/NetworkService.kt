package com.satya.profilesearchapp.data.api

import com.satya.profilesearchapp.data.model.GHRepo
import com.satya.profilesearchapp.util.NetworkResult
import javax.inject.Inject

//class NetworkService @Inject constructor(private val api: GitHubApi) {
//    suspend fun fetchUserRepositories(): NetworkResult<GHRepo> {
//        return try {
//            val response = api.getUserRepository()
//            if (response.isSuccessful && response.body() != null) {
//                NetworkResult.success(response.body()!!)
//            } else {
//                NetworkResult.error(Exception("Failed to fetch repositories: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            NetworkResult.error(e)
//        }
//    }
//}