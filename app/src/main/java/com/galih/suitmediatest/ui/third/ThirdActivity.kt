package com.galih.suitmediatest.ui.third

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galih.suitmediatest.base.BaseActivity
import com.galih.suitmediatest.databinding.ActivityThirdBinding
import com.galih.suitmediatest.domain.entities.User
import com.galih.suitmediatest.ui.second.SecondActivity
import com.galih.suitmediatest.utills.orFalse
import com.galih.suitmediatest.utills.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdActivity : BaseActivity<ActivityThirdBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityThirdBinding = ActivityThirdBinding::inflate

    private lateinit var userAdapter: UserAdapter
    private val viewModel by viewModels<ThirdViewModel>()
    private var page = 1
    private var totalPages = 0
    private var isLoading = false

    override fun setup() {
        setupBackButton()
        setTitle("Third Screen")
        setupSwiper()
        setupRecyclerView()
        fetchData()
    }

    private fun fetchData() {
        viewModel.getUsers(page)

        viewModel.users.observe(this, setupUserObserver())
    }

    private fun setupUserObserver() = setObserver<List<User>>(
        onSuccess = { response ->
            if (response.data?.isEmpty().orFalse()) {
                handleEmptyState()
                return@setObserver
            }

            if (page == 1) userAdapter.differ.submitList(response.data)
            else {
                val temp = userAdapter.differ.currentList.toMutableList()
                temp.addAll(response.data ?: listOf())
                userAdapter.differ.submitList(temp.distinctBy { it.id })
                isLoading = false
            }
            page = viewModel.getPage()
            totalPages = viewModel.getTotalPage()
        },
        onError = { response ->
            showToast(response.message.orEmpty())
        },
        loadingItem = binding.progressBar,
        swiperItem = binding.swiper
    )

    private fun handleEmptyState() {
        binding.tvEmpty.visible()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter()
        with(binding.rvUser) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                    val manager = this@with.layoutManager as LinearLayoutManager
                    val max = rv.adapter?.itemCount?.minus(1)
                    if (
                        page != totalPages
                        && (manager.findLastCompletelyVisibleItemPosition() == max)
                        && !isLoading
                    ) {
                        isLoading = true
                        page++
                        fetchData()
                    }
                }
            })
        }

        userAdapter.setOnItemClickListener {
            SecondActivity.startActivity(this, "${it.firstName} ${it.lastName}")
        }
    }

    private fun setupSwiper() {
        binding.swiper.setOnRefreshListener {
            page = 1
            isLoading = false
            fetchData()
        }
    }

    companion object {

        fun startActivity(ctx: Context) {
            val intent = Intent(ctx, ThirdActivity::class.java)
            ctx.startActivity(intent)
        }
    }
}