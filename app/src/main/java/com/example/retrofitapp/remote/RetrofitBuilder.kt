package com.example.retrofitapp.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBuilder {
    @GET("pokemon/{id}")
    fun getPokemonByID(@Path("id") id:String): Call<PokemonEntry>

    companion object{
        private val BASE_URL = "https://pokeapi.co/api/v2"

        fun create() : RetrofitBuilder{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitBuilder::class.java)
        }
    }
}