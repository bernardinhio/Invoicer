package com.example.invoicerapp.datamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Invoice(
    var id: String? = null,
    var createdAt: String? = null,
    var paymentDue: String? = null,
    var description: String? = null,
    var paymentTerms: String? = null,
    var clientName: String? = null,
    var clientEmail: String? = null,
    var status: String? = null,
    var clientAddress: Address? = null,
    var senderAddress: Address? = null,
    var items: Array<Items>? = null,
    var total: String? = null
): Parcelable

enum class InvoiceStatus (status: String?){
    PAID("Paid"), PENDING("Pending"), DRAFT("Draft")
}