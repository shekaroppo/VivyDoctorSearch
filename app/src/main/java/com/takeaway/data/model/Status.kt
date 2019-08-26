package com.takeaway.data.model

import com.google.gson.annotations.SerializedName

enum class Status constructor(var status: String) {
    @SerializedName("open")
    OPEN("open"),

    @SerializedName("order ahead")
    ORDER_AHEAD("order ahead"),

    @SerializedName("closed")
    CLOSED("closed");
}

