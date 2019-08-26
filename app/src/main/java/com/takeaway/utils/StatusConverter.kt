package com.takeaway.utils

import androidx.room.TypeConverter

class StatusConverter {

    @TypeConverter
    fun toStatus(status: Int): Status {
        when (status) {
            0 -> return Status.OPEN
            1 -> return Status.ORDER_AHEAD
            2 -> return Status.CLOSED
        }
        throw IllegalArgumentException("Could not recognize status")
    }

    @TypeConverter
    fun toInteger(status: Status): Int {
        return status.ordinal
    }
}
