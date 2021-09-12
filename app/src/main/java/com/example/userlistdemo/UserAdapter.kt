package com.example.userlistdemo

import com.example.userlistdemo.databinding.RowUserBinding
import com.example.userlistdemo.model.User
import com.example.userlistdemo.view.AnalogousDataBindingAdapter

class UserAdapter(user: MutableList<User> = mutableListOf()) :
    AnalogousDataBindingAdapter<User, RowUserBinding>(user) {

    override fun getLayoutIdForViewType(): Int = R.layout.row_user

    override fun getItemBindingId(position: Int): Int = BR.card
    override fun bindData(item: User, baseViewHolder: BaseViewHolder) {
        // nothing do
    }

}