package com.example.invoicerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.invoicerapp.data.InvoicerRepository
import com.example.invoicerapp.datamodel.Invoice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: InvoicerRepository
): ViewModel() {

    val myInvoicesMutableLiveData= MutableLiveData<Array<Invoice>?>()

    fun fetchArrayOfInvoices(){
        viewModelScope{
            repository.getMyInvoices().collect{
                myInvoicesMutableLiveData.value = it
            }
        }

    }
}