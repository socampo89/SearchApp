package com.test.search.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.search.domain.entity.SearchEntity
import com.test.search.domain.use_cases.search.SearchUseCases
import com.test.search.presentation.networking.helpers.Resource
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCases: SearchUseCases) : ViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String?>()
    val resultsLiveData = MutableLiveData<SearchEntity?>()
    fun search(query: String?) {
        viewModelScope.launch {
            errorLiveData.postValue(null)
            loadingLiveData.postValue(true)
            when(val response = searchUseCases.searchUseCase.invoke(query)){
                is Resource.Error -> errorLiveData.postValue(response.message)
                is Resource.Success -> resultsLiveData.postValue(response.data)
            }
            loadingLiveData.postValue(false)
        }
    }
}