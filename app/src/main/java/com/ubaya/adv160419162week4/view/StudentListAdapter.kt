package com.ubaya.adv160419162week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160419162week4.R
import com.ubaya.adv160419162week4.model.Student
import com.ubaya.adv160419162week4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class  StudentViewHolder(var view: View):
            RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        with (holder.view){
            txtId.text = student.id
            txtName.text = student.name
            btnDetail.setOnClickListener { 
                val action = StudentListFragmentDirections.actionStudentListFragmentToStudentDetailFragment(student.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imgStudentPhoto.loadImage(student.photoUrl, progressPhoto)
        }
    }

    override fun getItemCount() = studentList.size
    fun updateStudentList (newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

}