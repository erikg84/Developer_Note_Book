package com.example.developernotebook.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.developernotebook.R
import com.example.developernotebook.databinding.FragmentRegistrationBinding
import com.example.developernotebook.model.User
import com.example.developernotebook.prefs
import com.example.developernotebook.util.value
import com.example.developernotebook.viewmodel.DBViewModel
import timber.log.Timber

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val vm = DBViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.registerBt.setOnClickListener {
            val user = User(
                firstName = binding.firstNameEdittext.text.toString(),
                lastName = binding.lastNameEdittext.text.toString(),
                email = binding.emailAddressEdittext.text.toString(),
                phoneNumber = binding.phoneNumberEdittext.text.toString(),
                address = binding.addressEdittext.text.toString(),
                password = binding.passwordEdittext.text.toString()
            )
            vm.addUser(user)
            vm.onAction(DBViewModel.Action.SetNewUser(user))
        }

        vm.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.registerBt.isEnabled = !isLoading
            if(isLoading){
                binding.progressView.visibility = View.VISIBLE
            } else {
                binding.progressView.visibility = View.GONE
            }
        })

        vm.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            Toast.makeText(activity,"registration succuess!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.next_loginFragment)
        })

        init()
        return binding.root
    }

    private fun init() {
        initTextWatchers()
    }

    private fun initTextWatchers() {
        binding.firstNameEdittext.doAfterTextChanged { vm.onAction(DBViewModel.Action.SetFirstName(it.toString())) }
        binding.lastNameEdittext.doAfterTextChanged { vm.onAction(DBViewModel.Action.SetLastName(it.toString())) }
        binding.emailAddressEdittext.doAfterTextChanged { vm.onAction(DBViewModel.Action.SetEmailAddress(it.toString())) }
        binding.phoneNumberEdittext.doAfterTextChanged { vm.onAction(DBViewModel.Action.SetPhoneNumber(it.toString())) }
        binding.addressEdittext.doAfterTextChanged { vm.onAction(DBViewModel.Action.SetAddress(it.toString())) }
        binding.passwordEdittext.doAfterTextChanged { vm.onAction(DBViewModel.Action.SetPassword(it.toString())) }
    }
}