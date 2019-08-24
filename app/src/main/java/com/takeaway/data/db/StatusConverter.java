//package com.takeaway.data.db;
//
//import androidx.room.TypeConverter;
//
//public class StatusConverter {
//
//    @TypeConverter
//    public static Status toStatus(int status) {
//        switch (status) {
//            case 0:
//                return OPEN;
//            case 1:
//                return ORDER_AHEAD;
//            case 2:
//                return CLOSED;
//        }
//        throw new IllegalArgumentException("Could not recognize status");
//    }
//
//    @TypeConverter
//    public static int toInteger(Status status) {
//        return status.ordinal();
//    }
//}