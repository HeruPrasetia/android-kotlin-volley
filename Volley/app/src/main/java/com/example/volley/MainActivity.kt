package com.example.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGet = findViewById(R.id.btnGet) as Button
        val btnPost = findViewById(R.id.btnPost) as Button

        btnGet.setOnClickListener {
            httpGet()
        }

        btnPost.setOnClickListener {
            httpPost()
        }
    }

    // Permintaan koneksi ke server menggunakan method GET
    private fun httpGet() {
        val queue = Volley.newRequestQueue(this)
        val edtUrl = findViewById(R.id.edtUrl) as TextView
        val url:String = edtUrl.text.toString()
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(this, "Permintaan Gagal", Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }

    // Permintaan ke server menggunakan Mthod POST
    private fun httpPost() {
        val queue = Volley.newRequestQueue(this)
        val edtUrl = findViewById(R.id.edtUrl) as TextView
        val edtData = findViewById(R.id.edtData) as TextView
        val url:String = edtUrl.text.toString()
        val data:String = edtData.text.toString()
        val postRequest = object :  StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                Toast.makeText(this, "Permintaan Gagal", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("data", data)
                return params
            }
        }
        queue.add(postRequest)
    }
}
