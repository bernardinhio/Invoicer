package com.example.invoicerapp.datamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(
    var street: String? = null,
    var city: String? = null,
    var postCode: String? = null,
    var country: String? = null,
): Parcelable