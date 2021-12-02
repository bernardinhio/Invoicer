package com.example.invoicerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.invoicerapp.databinding.FragmentMainBinding
import com.example.invoicerapp.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    lateinit var viewBinding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.myInvoicesMutableLiveData.observe(
            viewLifecycleOwner,
            { arrayInvoices ->
                viewBinding.tvResults.text = "Retrofit result to fill RecyclerView below: "
                arrayInvoices?.forEach {
                    viewBinding.tvResults.text = viewBinding.tvResults.text.toString().plus(" * ${it.clientName}")
                }
            }
        )
        viewModel.fetchArrayOfInvoices()
    }
}