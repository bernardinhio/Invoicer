package com.example.invoicerapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicerapp.R
import com.example.invoicerapp.adapter.InvoicesAdapter
import com.example.invoicerapp.databinding.FragmentMainBinding
import com.example.invoicerapp.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    lateinit var viewBinding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var invoicesAdapter: InvoicesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater)

        invoicesAdapter = InvoicesAdapter(this.context)
        setupRecyclerView()

        setHasOptionsMenu(true)

        return viewBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.fetchData  -> {
                viewBinding.progressBar.visibility = View.VISIBLE
                viewModel.fetchArrayOfInvoices()
                true
            }
            R.id.reset -> {
                viewBinding.progressBar.visibility = View.GONE
                viewBinding.tvResults.visibility = View.GONE
                invoicesAdapter.submitList(emptyList())
                true
            }
            else -> false
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listOfInvoices.observe(
            viewLifecycleOwner,
            { arrayInvoices ->
                val listInvoices = arrayInvoices?.toList()
                invoicesAdapter.submitList(listInvoices)
            }
        )

        viewModel.backendErrorMessage.observe(
            viewLifecycleOwner,
            { errorMessage ->
                viewBinding.tvResults.visibility = if (errorMessage.isEmpty()) View.GONE else View.VISIBLE
                viewBinding.tvResults.text = errorMessage
            }
        )

        viewModel.isBackendLoading.observe(
            viewLifecycleOwner,
            { isLoading ->
                viewBinding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
            }
        )

    }

    private fun setupRecyclerView() {
        with(viewBinding.rvPreviewInvoices){
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(false)
            adapter = invoicesAdapter
        }
    }
}