package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitapp.remote.PokemonEntry
import com.example.retrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonByID("280")

        retrofit.enqueue(object: Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {

                Log.d("retrofitresponse","res: ${response.body()}")
                Log.d("retrofitresponse","name: ${response.body()?.name}")
                Log.d("retrofitresponse","front_default: ${response.body()?.sprite?.front_default}")
                val stats = response.body()?.stats
                if(stats != null){
                    for (stat in stats){
                        Log.d("retrofitresponse", "${stat.stat.stat_value}: ${stat.base_stat}")
                    }
                }

                val typesArray = response.body()?.types
                if(typesArray != null){
                    Log.d("retrofitresponse","type: ${typesArray[0].type.name}")
                }
            }
            override fun onFailure(call: Call<PokemonEntry>, t: Throwable){
                Log.e("retrofitresponse", "error: ${t.message}")
            }
        })
    }
}