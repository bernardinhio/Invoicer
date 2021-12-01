package com.example.invoicerapp.api

import com.example.invoicerapp.datamodel.Invoice
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
        const val END_POINT = "v3/cd78c7eb-9ee8-4578-8591-c2a49f90fe38"
    }

    @GET(END_POINT)
    suspend fun getInvoices(): Response<Array<Invoice>>

}