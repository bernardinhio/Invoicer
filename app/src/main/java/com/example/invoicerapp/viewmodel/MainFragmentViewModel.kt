package com.example.invoicerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import com.example.invoicerapp.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.invoicerapp.datamodel.Invoice
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: InvoicerRepository
): ViewModel() {

    val listOfInvoices = MutableLiveData<Array<Invoice>>()
    val backendErrorMessage = MutableLiveData<String>("")
    val isBackendLoading = MutableLiveData<Boolean>(false)

    fun fetchArrayOfInvoices() {
        viewModelScope.launch {

            repository.fetchListOfInvoices()

            repository.listOfInvoices.collect { backendResult ->
                when(backendResult){
                    is BackendSuccess -> {
                        isBackendLoading.value = false
                        backendErrorMessage.value = ""
                        listOfInvoices.value = backendResult.data
                    }
                    is BackendFailure -> {
                        isBackendLoading.value = false
                        backendErrorMessage.value = backendResult.message
                        listOfInvoices.value = emptyArray()
                    }
                    is BackendLoading -> isBackendLoading.value = true
                }
            }

        }
    }

}