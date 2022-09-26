package com.test.search.presentation.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.search.CoroutinesTestRule
import com.test.search.domain.entity.ProductEntity
import com.test.search.domain.use_cases.search.SearchUseCase
import com.test.search.domain.use_cases.search.SearchUseCases
import com.test.search.getOrAwaitValue
import com.test.search.presentation.networking.SearchResponse
import com.test.search.presentation.networking.helpers.Resource
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
@Suppress("UNCHECKED_CAST")
class SearchViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchUseCase : SearchUseCase

    private lateinit var searchUseCases: SearchUseCases

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup(){
        searchUseCases = SearchUseCases(searchUseCase)
        viewModel = SearchViewModel(searchUseCases)
    }

    @Test
    fun `check livedata values when success response`()= runBlocking{
        val query = "Creatina"
        val results = mutableListOf(ProductEntity(id = "MX1", title = "Creatina 1"), ProductEntity(id = "MX2", title = "Creatina 2"))
        Mockito.`when`(searchUseCases.searchUseCase.invoke(query)).thenReturn(
            Resource.Success(SearchResponse(query = query, results = results))
        )
        viewModel.search(query)
        val errorValue = viewModel.errorLiveData.getOrAwaitValue()
        val loadingValue = viewModel.loadingLiveData.getOrAwaitValue()
        val result = viewModel.resultsLiveData.getOrAwaitValue()
        TestCase.assertNull(errorValue)
        TestCase.assertFalse(loadingValue)
        TestCase.assertNotNull(result)
    }

    @Test
    fun `check livedata values when error response`()= runBlocking{
        val query = "Creatina"
        Mockito.`when`(searchUseCases.searchUseCase.invoke(query)).thenReturn(
            Resource.Error("An error")
        )
        viewModel.search(query)
        val errorValue = viewModel.errorLiveData.getOrAwaitValue()
        val loadingValue = viewModel.loadingLiveData.getOrAwaitValue()
        TestCase.assertNotNull(errorValue)
        TestCase.assertFalse(loadingValue)
    }

}