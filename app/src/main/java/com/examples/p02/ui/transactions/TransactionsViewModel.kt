package com.examples.p02.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.p02.data.RemoteDataSource
import com.examples.p02.data.Result
import com.examples.p02.data.model.Balance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val apiService: RemoteDataSource) :
    ViewModel() {
    val _accounts: MutableLiveData<ArrayList<Balance>> = MutableLiveData()

    fun fetchAccounts(accountId: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { apiService.fetchTransactions(accountId) }
            if (result is Result.Success) {
                _accounts.postValue(result.data)
            }
        }
    }
}