package com.example.invoicerapp.datamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Items(
    var name: String? = null,
    var quantity: String? = null,
    var price: String? = null,
    var total: String? = null,
): Parcelable