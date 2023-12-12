package com.example.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter : TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTodoList = findViewById<RecyclerView>(R.id.rvTodoList)
        val bAddBtn = findViewById<Button>(R.id.bAddBtn)
        val bDeleteBtn = findViewById<Button>(R.id.bDeleteBtn)
        val etTodoTitle = findViewById<EditText>(R.id.etTodoTitle)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoList.adapter = todoAdapter
        rvTodoList.layoutManager = LinearLayoutManager(this)

        bAddBtn.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        bDeleteBtn.setOnClickListener {
            todoAdapter.removeDone()
        }
    }

}