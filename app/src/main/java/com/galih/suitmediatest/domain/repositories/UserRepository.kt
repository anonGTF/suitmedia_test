package com.galih.suitmediatest.domain.repositories

import com.galih.suitmediatest.domain.entities.User

interface UserRepository {
    suspend fun getUsers(page : Int) : List<User>
    fun getName() : String
    fun saveName(name : String)
    fun getPage() : Int
    fun getTotalPage() : Int
}