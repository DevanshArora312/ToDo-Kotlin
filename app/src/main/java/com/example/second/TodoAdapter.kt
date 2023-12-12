package com.example.second

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todoList : MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tv1 : TextView = itemView.findViewById<TextView>(R.id.tvTodoItem)
        val cb : CheckBox = itemView.findViewById<CheckBox>(R.id.cbCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    private fun strikeThru(titleItem : TextView , isChecked : Boolean ) {
        if(isChecked){
            titleItem.paintFlags = titleItem.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            titleItem.paintFlags = titleItem.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo : Todo = todoList[position]
        holder.tv1.text = currTodo.title
        holder.cb.isChecked = currTodo.isChecked
        strikeThru(holder.tv1,currTodo.isChecked)
        holder.cb.setOnCheckedChangeListener { _, isChecked ->
            strikeThru(holder.tv1,holder.cb.isChecked)
            currTodo.isChecked = !currTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun addTodo(todo : Todo){
        todoList.add(todo)
        notifyItemInserted(todoList.size -1)
    }

    fun removeDone(){
        todoList.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
}