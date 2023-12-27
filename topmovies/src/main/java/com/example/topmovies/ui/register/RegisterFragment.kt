package com.example.topmovies.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentRegisterBinding
import com.example.topmovies.models.register.BodyRegister
import com.example.topmovies.utils.StoreUserData
import com.example.topmovies.utils.goneWidget
import com.example.topmovies.utils.hideWidget
import com.example.topmovies.utils.showShortSnackBar
import com.example.topmovies.utils.showWidget
import com.example.topmovies.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    @Inject lateinit var storeUserData: StoreUserData
    private val viewModel : RegisterViewModel by viewModels()
    @Inject lateinit var body : BodyRegister

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            submitBtn.setOnClickListener {
                val name = nameEdt.text.toString()
                val email = emailEdt.text.toString()
                val password = passwordEdt.text.toString()
                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() ){
                    body.name = name
                    body.email = email
                    body.password = password
                }else{
                    binding.root.showShortSnackBar(getString(R.string.fillAllFields))
                }
                // call api and send data
                viewModel.doRegisterUser(body)
                // loading
                viewModel.loading.observe(viewLifecycleOwner){
                    if (it) {
                        context?.showWidget(submitLoading)
                        context?.hideWidget(submitBtn)
                    }else{
                        context?.showWidget(submitBtn)
                        context?.hideWidget(submitLoading)
                    }
                }
                viewModel.responseRegisterData.observe(viewLifecycleOwner){
                    lifecycle.coroutineScope.launchWhenCreated {
                        storeUserData.saveUserToken(it.name.toString())
                    }
                }
            }
        }
    }
}