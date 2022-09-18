package com.examples.p02.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examples.p02.data.model.Account
import com.examples.p02.databinding.ItemAccountBinding
import com.examples.p02.utils.CustomClickListener


class AccountsAdapter(private val listener: CustomClickListener<Account>) :
    RecyclerView.Adapter<AccountsViewHolder>() {

    private val items = ArrayList<Account>()

    fun setItems(items: ArrayList<Account>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val binding: ItemAccountBinding =
            ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) =
        holder.bind(items[position])
}

class AccountsViewHolder(
    private val itemBinding: ItemAccountBinding,
    private val listener: CustomClickListener<Account>
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var account: Account

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Account) {
        this.account = item
        itemBinding.txtViewName.text = item.name
        itemBinding.txtViewBal.text = "Balance : $${item.balance}"
    }

    override fun onClick(v: View?) {
        listener.onItemClick(account)
    }
}