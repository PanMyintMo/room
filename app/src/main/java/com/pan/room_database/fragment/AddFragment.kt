package com.pan.room_database.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pan.room_database.R
import com.pan.room_database.databinding.FragmentAddBinding
import com.pan.room_database.model.User
import com.pan.room_database.viewModel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            insertDataToDB()
        }

        return binding.root
    }

    private fun insertDataToDB() {
        val firstName = binding.edName.text.toString()
        val lastName = binding.edSecondName.text.toString()
        val age = binding.edAge.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {
            val user = User(0, firstName, lastName, Integer.parseInt(age))
            userViewModel.addUser(user)
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successful Data Insert", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "Fill all field!", Toast.LENGTH_SHORT).show()
        }
    }
}