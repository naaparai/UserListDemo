package com.example.userlistdemo.util

import java.net.InetAddress
import java.net.UnknownHostException

fun isNetworkActive(): Boolean {
    return try {
        val address: InetAddress = InetAddress.getByName("www.google.com")
        !address.equals("")
    } catch (e: UnknownHostException) {
        false
    }
}