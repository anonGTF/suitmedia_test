package com.galih.suitmediatest.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galih.suitmediatest.domain.entities.User
import com.galih.suitmediatest.domain.repositories.UserRepository
import com.galih.suitmediatest.utills.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users : MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val users : LiveData<Resource<List<User>>>
    get() = _users

    fun getUsers(page: Int) = viewModelScope.launch {
        _users.postValue(Resource.Loading())
        try {
            val response = repository.getUsers(page)
            _users.postValue(Resource.Success(response))
        } catch (e : Exception) {
            _users.postValue(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }

    fun getPage() = repository.getPage()
    fun getTotalPage() = repository.getTotalPage()
}