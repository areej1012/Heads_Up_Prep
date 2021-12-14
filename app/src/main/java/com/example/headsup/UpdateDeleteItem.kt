package com.example.headsup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.headsup.databinding.ActivityUpdateDeleteItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDeleteItem : AppCompatActivity() {
    lateinit var binding: ActivityUpdateDeleteItemBinding
    var pk: Int = 0
    val api = APIClient().gatAllCelebrity()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pk = intent.extras!!.getInt("pk", 0)
        requestAPI()

        binding.btBack.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }

    private fun requestAPI() {

        api?.getCelebrity(pk)?.enqueue(object : Callback<Celebrities> {
            override fun onResponse(call: Call<Celebrities>, response: Response<Celebrities>) {
                binding.etName.setText(response.body()!!.name)
                binding.etToo1.setText(response.body()!!.taboo1)
                binding.etToo2.setText(response.body()!!.taboo2)
                binding.etToo3.setText(response.body()!!.taboo3)
            }

            override fun onFailure(call: Call<Celebrities>, t: Throwable) {
                Log.e("onFailure UpDe", t.localizedMessage)
            }

        })
    }

    fun updateCelebrity(view: View) {
        val name = binding.etName.text.toString()
        val too1 = binding.etToo1.text.toString()
        val too2 = binding.etToo2.text.toString()
        val too3 = binding.etToo3.text.toString()
        Log.e("pk","$pk")
        if (name.isNotEmpty() && too1.isNotEmpty() && too2.isNotEmpty() && too3.isNotEmpty()) {
            api?.updateCelebrity(pk, Celebrities(name, pk, too1, too2, too3))?.enqueue(object : Callback<Celebrities> {
                override fun onResponse(call: Call<Celebrities>, response: Response<Celebrities>) {
                    Toast.makeText(applicationContext, "Update successfully", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Celebrities>, t: Throwable) {
                    Log.e(" onFailure UpDe", t.localizedMessage)
                }

            })
        } else
            Toast.makeText(this, "make sure you fill all fields", Toast.LENGTH_LONG).show()
    }

    fun deleteCelebrity(view: View) {
        api?.deleteCelebrity(pk)?.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(applicationContext, "Delete successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(" onFailure UpDe", t.localizedMessage)
            }

        })
    }
}