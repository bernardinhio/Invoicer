package com.example.invoicerapp.data

import android.util.Log
import com.example.invoicerapp.api.RetrofitApi
import com.example.invoicerapp.datamodel.Invoice
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoicerRepository @Inject constructor(
    private val retrofitApi: RetrofitApi
){

    val listOfInvoices = MutableStateFlow<BackendResult>(BackendNotCalledYet)

    suspend fun fetchListOfInvoices() {

        try {
            val response: Response<Array<Invoice>> =
                retrofitApi.getInvoices()

            // Simulate Late
            listOfInvoices.value = BackendLoading
            delay(5_000)

            if (response.code() == HttpURLConnection.HTTP_OK) {
                val arrayOfInvoices: Array<Invoice>? = response.body()

                if (!arrayOfInvoices.isNullOrEmpty()){
                    listOfInvoices.value = BackendSuccess(arrayOfInvoices)
                    Log.d("retrofitCall", arrayOfInvoices.toString())

                } else{
                    listOfInvoices.value = BackendFailure("Empty Array of Invoices!")
                    Log.d("retrofitCall", "Empty Array of Invoices!")
                }
            }

        } catch (e: IOException) {
            Log.d("retrofitCall", "No Internet!")
            listOfInvoices.value = BackendFailure("No Internet!")

        } catch (exception: HttpException) {
            Log.d("retrofitCall", "Server Broken!")
            listOfInvoices.value = BackendFailure("Server Broken!")
        }

    }
}

// Backend Status
sealed class BackendResult
data class BackendSuccess(val data: Array<Invoice>): BackendResult()
data class BackendFailure(val message: String): BackendResult()
object BackendNotCalledYet: BackendResult()
object BackendLoading: BackendResult()

