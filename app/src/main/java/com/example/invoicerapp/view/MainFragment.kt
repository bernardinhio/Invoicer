package com.example.invoicerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicerapp.adapter.InvoicesOverviewAdapter
import com.example.invoicerapp.databinding.FragmentMainBinding
import com.example.invoicerapp.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    lateinit var viewBinding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var invoicesOverviewAdapter: InvoicesOverviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater)

        invoicesOverviewAdapter = InvoicesOverviewAdapter(this.context)
        setupRecyclerView()

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.myInvoicesMutableLiveData.observe(
            viewLifecycleOwner,
            { arrayInvoices ->
                val listInvoices = arrayInvoices?.toList()
                viewBinding.tvResults.text = "You got Backend result below...\n"
                listInvoices?.forEach {
                    viewBinding.tvResults.text = viewBinding.tvResults.text.toString().plus(" * ${it.clientName}")
                }
                invoicesOverviewAdapter.submitList(listInvoices)
            }
        )
        viewModel.fetchArrayOfInvoices()
    }

    private fun setupRecyclerView() {
        with(viewBinding.rvPreviewInvoices){
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(false)
            adapter = invoicesOverviewAdapter
        }
    }
}