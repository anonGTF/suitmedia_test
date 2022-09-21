package com.galih.suitmediatest.ui.first

import androidx.lifecycle.ViewModel
import com.galih.suitmediatest.domain.repositories.UserRepository
import com.galih.suitmediatest.utills.removeWhitespaces
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun isPalindrome(input: String): Boolean {
        val inputFiltered = input.removeWhitespaces()
        val sb = StringBuilder(inputFiltered)
        val reverseStr = sb.reverse().toString()
        return inputFiltered.equals(reverseStr, ignoreCase = true)
    }

    fun saveName(name: String) = repository.saveName(name)
}