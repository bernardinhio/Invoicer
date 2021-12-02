package com.example.invoicerapp.data

import android.util.Log
import com.example.invoicerapp.api.RetrofitApi
import com.example.invoicerapp.datamodel.Invoice
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoicerRepository @Inject constructor(
    val retrofitApi: RetrofitApi
){

    suspend fun getMyInvoices(): MutableStateFlow<Array<Invoice>?>{

        try {
            val response: Response<Array<Invoice>> =
                retrofitApi.getInvoices()

            if (response.code() == HttpURLConnection.HTTP_OK) {
                val arrayOfInvoices: Array<Invoice>? = response.body()
                return if (!arrayOfInvoices.isNullOrEmpty()){
                    Log.d("retrofitCall", arrayOfInvoices.toString())
                    MutableStateFlow<Array<Invoice>?>(arrayOfInvoices)
                } else{
                    Log.d("retrofitCall", "Empty Array of Invoices")
                    return MutableStateFlow<Array<Invoice>?>(null)
                }
            } else if(response.code() == HttpURLConnection.HTTP_NO_CONTENT){
                Log.d("retrofitCall", "Empty no results from Server")
                return MutableStateFlow<Array<Invoice>?>(null)
            } else {
                Log.d("retrofitCall", "Server Broken")
                return MutableStateFlow<Array<Invoice>?>(null)
            }

        } catch (e: IOException) {
            Log.d("retrofitCall", "No Internet")
            return MutableStateFlow<Array<Invoice>?>(null)

        } catch (exception: HttpException) {
            Log.d("retrofitCall", "Server Broken")
            return MutableStateFlow<Array<Invoice>?>(null)
        }

    }
}