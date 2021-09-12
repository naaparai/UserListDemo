package com.example.userlistdemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AnalogousDataBindingAdapter<T, VB : ViewDataBinding>(
    protected val items: MutableList<T> = mutableListOf()
) :
    RecyclerView.Adapter<AnalogousDataBindingAdapter<T, VB>.BaseViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinder =
            DataBindingUtil.inflate<VB>(layoutInflater, getLayoutIdForViewType(), parent, false)
        return BaseViewHolder(viewBinder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(position)
    }

    protected abstract fun bindData(item: T, baseViewHolder: BaseViewHolder)

    open inner class BaseViewHolder(val viewBinder: VB) : RecyclerView.ViewHolder(viewBinder.root) {

        fun bind(position: Int) {
            val item = items[position]
            viewBinder.setVariable(getItemBindingId(position), item)
            bindData(item, this)
            viewBinder.executePendingBindings()
        }
    }

    @LayoutRes
    protected abstract fun getLayoutIdForViewType(): Int

    protected abstract fun getItemBindingId(position: Int): Int

    open fun updateItems(newItems: List<T>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}
