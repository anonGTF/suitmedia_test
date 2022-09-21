package com.galih.suitmediatest.ui.second

import androidx.lifecycle.ViewModel
import com.galih.suitmediatest.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun getName() = repository.getName()
}