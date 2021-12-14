package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.headsup.databinding.ActivityAddCelebriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCelebrity : AppCompatActivity() {
    lateinit var binding: ActivityAddCelebriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCelebriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun addCelebrity(view: View) {
        val name = binding.etName.text.toString()
        val too1 = binding.etToo1.text.toString()
        val too2 = binding.etToo2.text.toString()
        val too3 = binding.etToo3.text.toString()
        if (name.isNotEmpty()&& too1.isNotEmpty() && too2.isNotEmpty() && too3.isNotEmpty()){
            val apiInterface = APIClient().gatAllCelebrity()?.create(APIInterface ::class.java)
            apiInterface?.addCelebrity(Celebrities(name,null,too1,too2,too3))?.enqueue(object : Callback<Celebrities>{
                override fun onResponse(call: Call<Celebrities>, response: Response<Celebrities>) {
                    Toast.makeText(applicationContext, "add successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@AddCelebrity, MainActivity::class.java))
                }

                override fun onFailure(call: Call<Celebrities>, t: Throwable) {
                    Toast.makeText(applicationContext, "add failed",Toast.LENGTH_LONG).show()
                }

            })
        }
        else
            Toast.makeText(this, "make sure you fill all fields",Toast.LENGTH_LONG).show()
    }
}