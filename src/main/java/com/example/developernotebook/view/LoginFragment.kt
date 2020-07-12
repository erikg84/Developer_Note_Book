package com.example.developernotebook.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.developernotebook.R
import com.example.developernotebook.databinding.FragmentLoginBinding
import com.example.developernotebook.viewmodel.DBViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val vm = DBViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.registrationTv.setOnClickListener {
            findNavController().navigate(R.id.next_registrationFragment)
        }
        binding.submitBt.setOnClickListener {
            vm.fetchUsers()
            vm.users.observe(viewLifecycleOwner, Observer { users ->
                val user = users.firstOrNull {
                    it.email == binding.userNameEdittext.text.toString() &&
                            it.password == binding.passwordEdittext.text.toString()
                }
                if(user != null){
                    findNavController().navigate(R.id.next_developerDashBoardActivity)
                } else {
                    Toast.makeText(activity,"Invalid email/password!!", Toast.LENGTH_LONG).show()
                }
            })
        }
        vm.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.submitBt.isEnabled = !isLoading
            if(isLoading){
                binding.progressView.visibility = View.VISIBLE
            } else {
                binding.progressView.visibility = View.GONE
            }
        })
        return binding.root
    }
}