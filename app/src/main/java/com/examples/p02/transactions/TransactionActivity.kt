package com.examples.p02.transactions

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.p02.R
import com.examples.p02.data.model.Account
import com.examples.p02.data.model.Balance
import com.examples.p02.databinding.ActivityTransactionBinding
import com.examples.p02.utils.CustomClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity(),
    CustomClickListener<Balance> {
    private lateinit var adapter: BalanceAdapter
    private lateinit var binding: ActivityTransactionBinding
    private lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Transactions"
        viewModel = ViewModelProvider(this)[TransactionsViewModel::class.java]
        setupRecyclerView()
        setupObservers()
        handleIncomingData(intent)
    }

    private fun handleIncomingData(intent: Intent?) {
        if (intent?.hasExtra("account") == true) {
            val account = intent.getSerializableExtra("account") as Account
            viewModel.fetchAccounts(account.id)
        }
    }

    private fun setupRecyclerView() {
        adapter = BalanceAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
    }

    private fun setupObservers() {
        viewModel._accounts.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    override fun onItemClick(data: Balance) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}