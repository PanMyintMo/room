package com.pan.room_database.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pan.room_database.R
import com.pan.room_database.adapter.ListAdapter
import com.pan.room_database.databinding.FragmentListBinding
import com.pan.room_database.viewModel.UserViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private lateinit var userViewModel: UserViewModel
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete -> {
                        deleteAllUserItem()
                        true
                    }

                    else -> {
                        false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun deleteAllUserItem() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setPositiveButton("Yes") { _, _ ->

            userViewModel.deleteAllUserItem()
            Toast.makeText(
                requireContext(),
                "Successfully delete everything!",
                Toast.LENGTH_SHORT
            ).show()

        }
        dialogBuilder.setNegativeButton("No"){_,_ ->

        }
        dialogBuilder.setTitle("Delete everything?")
        dialogBuilder.setMessage("Are u sure you want to delete everything?")
        dialogBuilder.create().show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)

        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter


        //UserViewModel
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }

        _binding!!.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }



        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}