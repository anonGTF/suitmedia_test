package com.galih.suitmediatest.data.mapper

import com.galih.suitmediatest.base.Mapper
import com.galih.suitmediatest.data.responses.UsersResponse
import com.galih.suitmediatest.domain.entities.User
import com.galih.suitmediatest.utills.orZero

class UserMapper : Mapper<List<UsersResponse>, List<User>> {
    override fun map(from: List<UsersResponse>): List<User> = from.map {
        User(
            id = it.id.orZero(),
            firstName = it.firstName.orEmpty(),
            lastName = it.lastName.orEmpty(),
            avatar = it.avatar.orEmpty(),
            email = it.email.orEmpty()
        )
    }
}