package com.example.recyclerviewapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentsRecyclerAdapter(private val context: Context, private val students: List<Student>) :
    RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder>() {
    private val layoutInflator = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflator.inflate(R.layout.student_list_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.textName.text = student.name
        holder.textClassName.text = student.className
        holder.studentPosition = position
        holder.doneButton.isChecked = student.done

    }

    fun removeStudent(position : Int) {
        DataManager.students.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        val textClassName = itemView.findViewById<TextView>(R.id.text_class_name)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.delete_button)
        val doneButton = itemView.findViewById<CheckBox>(R.id.done_box)
        var studentPosition = 0

        init {
            itemView.setOnClickListener {
               val intent = Intent(context, AddAndCreateStudentActivity::class.java)
               intent.putExtra(STUDENT_POSITION_KEY, studentPosition)
               context.startActivity(intent)
            }

            doneButton.setOnClickListener { view ->
                DataManager.students[studentPosition].done = doneButton.isChecked
               // Snackbar.make(view, "done? ${DataManager.students[studentPosition].done}", Snackbar.LENGTH_SHORT).show()
            }

            deleteButton.setOnClickListener {view ->
                val dialogBuilder = AlertDialog.Builder(context)

                dialogBuilder.setTitle("Remove Student?")
                    .setMessage("Do you want to remove this student?")
                    //.setCancelable(false)
                    .setPositiveButton("Remove", DialogInterface.OnClickListener {
                        dialog, id ->
                        removeStudent(studentPosition)
                        Snackbar.make(view, "Student removed", Snackbar.LENGTH_SHORT).show()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                    })

                val alert = dialogBuilder.create()

                alert.show()
            }
        }
    }



}