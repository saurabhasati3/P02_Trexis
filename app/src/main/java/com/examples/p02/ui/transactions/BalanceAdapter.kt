package com.examples.p02.transactions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examples.p02.data.model.Balance
import com.examples.p02.databinding.ItemAccountBinding
import com.examples.p02.utils.CustomClickListener


class BalanceAdapter(private val listener: CustomClickListener<Balance>) :
    RecyclerView.Adapter<BalanceViewHolder>() {

    private val items = ArrayList<Balance>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder {
        val binding: ItemAccountBinding =
            ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BalanceViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) =
        holder.bind(items[position])

    fun setItems(items: ArrayList<Balance>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class BalanceViewHolder(
    private val itemBinding: ItemAccountBinding,
    private val listener: CustomClickListener<Balance>
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var balance: Balance

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Balance) {
        this.balance = item
        itemBinding.txtViewName.text = item.title
        itemBinding.txtViewBal.text = "Balance : $ ${item.balance}"
    }

    override fun onClick(v: View?) {
        listener.onItemClick(balance)
    }
}