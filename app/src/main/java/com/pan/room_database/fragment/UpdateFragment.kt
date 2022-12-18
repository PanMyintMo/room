package com.pan.room_database.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pan.room_database.R
import com.pan.room_database.databinding.FragmentUpdateBinding
import com.pan.room_database.model.User
import com.pan.room_database.viewModel.UserViewModel


class UpdateFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //Add menu item here
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete -> {
                        deleteUser()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUserItem(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully remove : ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(
                R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No") { _, _ ->


        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.edUpdateName.setText(args.currentUser.firstName).toString()
        binding.edUpdateSecondName.setText(args.currentUser.lastName).toString()
        binding.edUpdateAge.setText(args.currentUser.age.toString()).toString()

        binding.btnUpdate.setOnClickListener {
            updateUserItem()
        }
        return binding.root
    }

    private fun updateUserItem() {
        val firstName = binding.edUpdateName.text.toString()
        val lastName = binding.edUpdateSecondName.text.toString()
        val age = binding.edUpdateAge.text.toString()


        if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {
            val updateUserItem =
                User(args.currentUser.id, firstName, lastName, Integer.parseInt(age))
            //Update current user items
            userViewModel.updateUserItems(updateUserItem)
            Toast.makeText(requireContext(), "Update Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Update failed!", Toast.LENGTH_SHORT).show()
        }
    }

}