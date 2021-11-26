package com.example.bambainsidekotlin.adapters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImplPlanDescription(val section: String?, val body: List<String>?) : Parcelable