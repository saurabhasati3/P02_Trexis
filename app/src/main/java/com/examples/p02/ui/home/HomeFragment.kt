package com.examples.p02.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.p02.R
import com.examples.p02.data.model.Account
import com.examples.p02.databinding.FragmentHomeBinding
import com.examples.p02.transactions.TransactionActivity
import com.examples.p02.utils.CustomClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),
    CustomClickListener<Account> {

    private lateinit var adapter: AccountsAdapter
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_home, null)
        _binding = DataBindingUtil.bind(view)
        _binding?.viewModel = viewModel
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        setupRecyclerView()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = AccountsAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMain.adapter = adapter
    }

    private fun setupObservers() {
        viewModel._accounts.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: Account) {
        val intent = Intent(context?.applicationContext, TransactionActivity::class.java)
        intent.putExtra("account", data)
        startActivity(intent)
    }
}