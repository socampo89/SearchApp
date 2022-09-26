package com.test.search.domain.use_cases.search

import com.test.search.domain.entity.ProductEntity
import com.test.search.domain.repository.SearchRepository
import com.test.search.presentation.networking.SearchResponse
import com.test.search.presentation.networking.helpers.Resource
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
@Suppress("UNCHECKED_CAST")
class SearchUseCaseTest {

    @Mock
    private lateinit var repository: SearchRepository

    private lateinit var useCase : SearchUseCase

    @Before
    fun setup(){
        useCase = SearchUseCase(repository)
    }

    @Test
    fun `get results not empty`()= runBlocking{
        val query = "Creatina"
        val results = mutableListOf(ProductEntity(id = "MX1", title = "Creatina 1"), ProductEntity(id = "MX2", title = "Creatina 2"))
        Mockito.`when`(repository.search(query)).thenReturn(
            Resource.Success(SearchResponse(query = query, results = results))
        )
        val response = useCase.invoke(query)
        TestCase.assertEquals(response is Resource.Success, true)
        TestCase.assertNotNull(response.data)
        TestCase.assertEquals(response.data?.results?.isNotEmpty(), true)
    }

    @Test
    fun `get results empty`()= runBlocking{
        val query = "Creatina"
        val results = mutableListOf<ProductEntity>()
        Mockito.`when`(repository.search(query)).thenReturn(
            Resource.Success(SearchResponse(query = query, results = results))
        )
        val response = useCase.invoke(query)
        TestCase.assertEquals(response is Resource.Success, true)
        TestCase.assertNotNull(response.data)
        TestCase.assertEquals(response.data?.results?.isEmpty(), true)
    }

    @Test
    fun `error message not null`()= runBlocking{
        val query = "Creatina"
        Mockito.`when`(repository.search(query)).thenReturn(
            Resource.Error(errorMessage = "An error")
        )
        val response = useCase.invoke(query)
        TestCase.assertEquals(response is Resource.Error, true)
        TestCase.assertNotNull(response.message)
    }
}