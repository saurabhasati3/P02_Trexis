package com.examples.p02.data.model

import java.io.Serializable

data class Account(val id: Int, val name: String, val balance: Long) : Serializable
