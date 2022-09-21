package com.galih.suitmediatest.base

interface Mapper<F, T> {
    fun map(from: F): T
}