package com.ubaya.adv160419162week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.ubaya.adv160419162week4.R
import com.ubaya.adv160419162week4.util.loadImage
import com.ubaya.adv160419162week4.viewmodel.DetailViewModel
import com.ubaya.adv160419162week4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*


class StudentDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id = ""
        arguments?.let {
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)
        observeViewModel()
    }
    private fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner) {
            val student = viewModel.studentsLD.value
            student?.let {
                editID.setText(it.id)
                editName.setText(it.name)
                editDOB.setText(it.dob)
                editPhone.setText(it.phone)
                imgStudentDetail.loadImage(it.photoUrl,progressStudentDetail)
            }
        }

    }

}