package com.galih.suitmediatest.ui.first

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.galih.suitmediatest.R
import com.galih.suitmediatest.base.BaseActivity
import com.galih.suitmediatest.databinding.ActivityFirstBinding
import com.galih.suitmediatest.ui.second.SecondActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstActivity : BaseActivity<ActivityFirstBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityFirstBinding =
        ActivityFirstBinding::inflate

    private val viewModel by viewModels<FirstViewModel>()

    override fun setup() {
        with(binding) {
            btnCheck.setOnClickListener {
                val result = viewModel.isPalindrome(etPalindrome.text.toString())
                showResultDialog(result)
            }

            btnNext.setOnClickListener {
                val name = etName.text.toString()
                viewModel.saveName(name)
                SecondActivity.startActivity(this@FirstActivity)
            }
        }
    }

    private fun showResultDialog(isPalindrome: Boolean) {
        val message =
            if (isPalindrome) getString(R.string.is_palindrome)
            else getString(R.string.not_palindrome)
        showToast(message)
    }
}