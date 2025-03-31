package com.example.gotapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PersonsApiService {
    @GET("Characters")
    fun getPersons(): Call<List<Person>>
}

