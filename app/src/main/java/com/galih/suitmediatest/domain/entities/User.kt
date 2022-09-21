package com.galih.suitmediatest.domain.entities

import com.galih.suitmediatest.base.BaseModel

data class User(
    override val id : Int,
    val firstName : String,
    val lastName : String,
    val avatar : String,
    val email : String
) : BaseModel(id)
