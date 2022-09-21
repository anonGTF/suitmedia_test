package com.galih.suitmediatest.domain.usecases

import com.galih.suitmediatest.base.UseCase
import com.galih.suitmediatest.domain.entities.User
import com.galih.suitmediatest.domain.repositories.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCase<List<User>, Int>() {
    override suspend fun run(params: Int): List<User> = repository.getUsers(page = params)
}