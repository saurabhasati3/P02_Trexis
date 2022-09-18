package com.examples.p02.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.p02.data.RemoteDataSource
import com.examples.p02.data.Result
import com.examples.p02.data.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val accountsRepo: RemoteDataSource) :
    ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Error fetching data"
    }
    val text: LiveData<String> = _text

    val _accounts: MutableLiveData<ArrayList<Account>> = MutableLiveData()

    init {
        fetchAccounts()
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            val result = accountsRepo.fetchAccountsList()
            if (result is Result.Success) {
                _accounts.postValue(result.data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}