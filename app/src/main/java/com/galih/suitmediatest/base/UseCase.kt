package com.galih.suitmediatest.base

abstract class UseCase <T, in P> where T : Any {

    abstract suspend fun run(params: P): T

    suspend operator fun invoke(params: P): T = run(params)

    class None
}