package com.example.invoicerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.invoicerapp.data.InvoicerRepository
import com.example.invoicerapp.datamodel.Invoice
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    val repository: InvoicerRepository
): ViewModel() {

    val myInvoicesMutableLiveData= MutableLiveData<Array<Invoice>?>()

    fun fetchArrayOfInvoices(){
        viewModelScope.launch{
            repository.getMyInvoices().collect{  arrayOfInvoices ->
                myInvoicesMutableLiveData.value = arrayOfInvoices
            }
        }

    }
}