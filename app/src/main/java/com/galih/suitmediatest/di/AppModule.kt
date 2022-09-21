package com.galih.suitmediatest.di

import com.galih.suitmediatest.base.Mapper
import com.galih.suitmediatest.base.RetrofitInstance.Companion.retrofit
import com.galih.suitmediatest.data.mapper.UserMapper
import com.galih.suitmediatest.data.preferences.Preferences
import com.galih.suitmediatest.data.remote.UserApi
import com.galih.suitmediatest.data.repoimpl.UserRepoImpl
import com.galih.suitmediatest.data.responses.UsersResponse
import com.galih.suitmediatest.domain.entities.User
import com.galih.suitmediatest.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = retrofit

    @Provides
    @Singleton
    fun providePreferences() : Preferences = Preferences.instance

    @Provides
    @Singleton
    fun provideUserMapper() : Mapper<List<UsersResponse>, List<User>> = UserMapper()

    @Provides
    @Singleton
    fun provideUserApi() : UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserApi,
        pref: Preferences,
        mapper: Mapper<List<UsersResponse>, List<User>>
    ): UserRepository = UserRepoImpl(api, pref, mapper)
}