package com.galih.suitmediatest.data.repoimpl

import com.galih.suitmediatest.base.Mapper
import com.galih.suitmediatest.data.preferences.Preferences
import com.galih.suitmediatest.data.remote.UserApi
import com.galih.suitmediatest.data.responses.UsersResponse
import com.galih.suitmediatest.domain.entities.User
import com.galih.suitmediatest.domain.repositories.UserRepository
import com.galih.suitmediatest.utills.orZero
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val userApi: UserApi,
    private val preferences: Preferences,
    private val userMapper: Mapper<List<UsersResponse>, List<User>>
) : UserRepository {
    override suspend fun getUsers(page: Int): List<User> {
        val response = userApi.getUsers(page)
        preferences.savePage(response.page.orZero())
        preferences.saveTotalPage(response.totalPages.orZero())
        return userMapper.map(response.results)
    }

    override fun getName(): String = preferences.name
    override fun getPage(): Int = preferences.page
    override fun getTotalPage(): Int = preferences.totalPage

    override fun saveName(name: String) {
        preferences.saveName(name)
    }
}