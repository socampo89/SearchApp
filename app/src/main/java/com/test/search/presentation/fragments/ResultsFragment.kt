package com.test.search.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.search.R
import com.test.search.databinding.FragmentResultsBinding
import com.test.search.domain.entity.ProductEntity
import com.test.search.presentation.viewModels.SearchViewModel
import com.test.search.presentation.views.adapters.ResultsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var resultsAdapter: ResultsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews(){
        setupResultsRecyclerView()
        setEmptyView(true)
    }

    private fun setupResultsRecyclerView() {
        resultsAdapter = ResultsAdapter(mutableListOf()){
            navigateToDetail(it)
        }
        binding.recyclerViewResults.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = resultsAdapter
        }
    }

    private fun updateResultsRecyclerView(list : MutableList<ProductEntity>){
        resultsAdapter.update(list)
    }

    private fun initViewModel() {
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            setErrorView(it)
        }
        viewModel.resultsLiveData.observe(viewLifecycleOwner){
            if(it?.results?.isNotEmpty() == true){
                setEmptyView(false)
                updateResultsRecyclerView(it.results)
            } else {
                setEmptyView(true)
            }
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){ show ->
          setLoadingView(show)
        }
    }

    private fun setErrorView(message: String?) {
         if(message == null){
             binding.errorView.hide()
         } else {
             binding.errorView.setError(message)
             binding.errorView.show()
         }
    }

    private fun setEmptyView(show :Boolean){
        if(show){
            binding.emptyView.show()
        } else {
            binding.emptyView.hide()
        }
    }

    private fun setLoadingView(show: Boolean) {
        if(show){
            binding.loadingView.show()
        } else {
            binding.loadingView.hide()
        }
    }

    private fun navigateToDetail(productEntity: ProductEntity) {
        val navController = findNavController()
        val args = Bundle()
        args.putParcelable(DetailFragment.PRODUCT_ENTITY_ARG,productEntity)
        navController.navigate(R.id.action_Results_to_DetailFragment, args = args)
    }

    fun search(query: String?) {
        viewModel.search(query)
    }
}