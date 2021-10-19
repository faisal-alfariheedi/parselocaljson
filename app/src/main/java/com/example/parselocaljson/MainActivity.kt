package com.example.parselocaljson

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myJson = this.jsonToClass<cid>(R.raw.dat)
        var im=arrayListOf<image>()
        var rv =findViewById<RecyclerView>(R.id.rv)
        rv.adapter=RVAdapter(im,this)
        rv.layoutManager= LinearLayoutManager(this)
        for (i in myJson.arc){
            im.add(image(i.title,i.url))
        }
    }
    inline fun <reified T> Context.jsonToClass(@RawRes resourceId: Int): T =
        Gson().fromJson(resources.openRawResource(resourceId).bufferedReader().use { it.readText() }, T::class.java)
}