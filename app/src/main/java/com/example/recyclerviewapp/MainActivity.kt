package com.example.recyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.studentsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StudentsRecyclerAdapter(this, DataManager.students)

        val fab = findViewById<View>(R.id.floatingActionButton)
        fab.setOnClickListener { view ->
            val intent = Intent(this, AddAndCreateStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

}
