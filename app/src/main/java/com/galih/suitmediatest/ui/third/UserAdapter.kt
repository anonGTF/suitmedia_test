package com.galih.suitmediatest.ui.third

import android.view.LayoutInflater
import android.view.ViewGroup
import com.galih.suitmediatest.base.BaseAdapter
import com.galih.suitmediatest.databinding.ItemUserBinding
import com.galih.suitmediatest.domain.entities.User
import com.squareup.picasso.Picasso

class UserAdapter : BaseAdapter<ItemUserBinding, User>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemUserBinding =
        ItemUserBinding::inflate

    override fun bind(binding: ItemUserBinding, item: User) {
        with(binding) {
            val fullName = "${item.firstName} ${item.lastName}"
            tvName.text = fullName
            tvEmail.text = item.email
            Picasso.get().load(item.avatar).into(imgAvatar)
        }
    }
}