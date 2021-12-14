package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.headsup.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adpter : HeadsUpRecyle
    lateinit var arrayCelebrate : ArrayList<Celebrities>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestAPI()

        binding.btFind.setOnClickListener {
            val name = binding.etName.text.trim().toString()
            if (name.isNotEmpty()){
                findCelebrity(name)
            }
        }

    }

    private fun findCelebrity(name: String) {
        for (cele in arrayCelebrate){
            if (cele.name.toLowerCase().trim().equals(name.toLowerCase())){
                val intent = Intent(this, UpdateDeleteItem :: class.java)
                intent.putExtra("pk",cele.pk)
                startActivity(intent)
            }
        }
    }

    private fun requestAPI() {
        val apiInterface = APIClient().gatAllCelebrity()?.create(APIInterface ::class.java)
        apiInterface?.getCelebrities()?.enqueue(object : Callback<ArrayList<Celebrities>>{
            override fun onResponse(
                call: Call<ArrayList<Celebrities>>,
                response: Response<ArrayList<Celebrities>>
            ) {
                arrayCelebrate = response.body()!!
                adpter = HeadsUpRecyle(arrayCelebrate)
                initRV()
            }

            override fun onFailure(call: Call<ArrayList<Celebrities>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }

        })
    }

    fun initRV(){
        binding.recyclerView.adapter = adpter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun addCelebrate(view: View) {
        startActivity(Intent(this , AddCelebrity::class.java))
    }
}