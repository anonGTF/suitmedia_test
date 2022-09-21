package com.galih.suitmediatest.ui.second

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.galih.suitmediatest.base.BaseActivity
import com.galih.suitmediatest.databinding.ActivitySecondBinding
import com.galih.suitmediatest.ui.third.ThirdActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : BaseActivity<ActivitySecondBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivitySecondBinding = ActivitySecondBinding::inflate

    private val viewModel by viewModels<SecondViewModel>()

    override fun setup() {
        setupBackButton()
        setTitle("Second Screen")
        populateData()
        setupListener()
    }

    private fun populateData() {
        val selectedName = intent.getStringExtra(SELECTED_NAME)
        binding.tvName.text = viewModel.getName()
        selectedName?.let {
            binding.tvSelectedUser.text = selectedName
        }
    }

    private fun setupListener() {
        binding.btnChoose.setOnClickListener {
            ThirdActivity.startActivity(this)
        }
    }

    companion object {
        const val SELECTED_NAME = "selected_name"

        fun startActivity(ctx: Context, selectedName: String? = null) {
            val intent = Intent(ctx, SecondActivity::class.java)
            intent.putExtra(SELECTED_NAME, selectedName)
            ctx.startActivity(intent)
        }
    }
}