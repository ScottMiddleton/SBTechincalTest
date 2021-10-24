package com.example.sbtechincaltest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by inject()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        binding.emailEt.doAfterTextChanged {
            viewModel.validateEmail()
        }

        binding.passwordEt.doAfterTextChanged {
            viewModel.validatePassword()
        }
    }

    private fun setupObservers() {
        viewModel.emailValidLD.observe(viewLifecycleOwner, {
            if (!it) {
                binding.emailTil.error = getString(R.string.requred_field_error)
            } else {
                binding.emailTil.error = null
            }
        })

        viewModel.passwordValidLD.observe(viewLifecycleOwner, {
            if (!it) {
                binding.passwordTil.error = getString(R.string.requred_field_error)
            } else {
                binding.passwordTil.error = null
            }
        })

        viewModel.loginSuccessLD.observe(viewLifecycleOwner, {
            if (it) {
                val action = LoginFragmentDirections.actionLoginFragmentToPhotosFragment()
                findNavController().navigate(action)
            }
        })
    }
}