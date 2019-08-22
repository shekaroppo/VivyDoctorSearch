package com.vivy.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivy.R
import com.vivy.data.model.RestaurantResponse
import com.vivy.databinding.FragmentSearchBinding
import com.vivy.ui.base.BaseFragment
import com.vivy.utils.DataWrapper
import javax.inject.Inject


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    @Inject
    lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var eventHandler: SearchEventHandler

    private lateinit var searchView: SearchView

    private var lastKey: String? = ""

    override fun layoutId() = R.layout.fragment_search

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        subscribeToModel()
    }

    private fun initializeUI() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchAdapter
        }
    }

    private fun subscribeToModel() {
        binding.searchViewModel = viewModel
        binding.eventHandler = eventHandler
        viewModel.searchMutableLiveData.observe(this, subscribersObserver())
    }

    private fun subscribersObserver(): Observer<DataWrapper<RestaurantResponse>> {
        return Observer {
            viewModel.displayLoader(false)
            it?.let {
                if (!it.isError) {
                    searchAdapter.updateData(it.data!!.restaurants)
                    viewModel.bottomProgressBar.value = false
                } else {
                    viewModel.setErrorMessage(it.isError, it.errorMessage)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchActionMenuItem = menu.findItem(R.id.menu_search)
        searchView = searchActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(onQueryTextListener(searchActionMenuItem))
        searchActionMenuItem.expandActionView()
    }

    private fun onQueryTextListener(searchActionMenuItem: MenuItem): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                searchDoctors(query)
                binding.recyclerView.scrollToPosition(0)
                binding.toolbar.title = query
                searchActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        }
    }

    private fun searchDoctors(query: String) {
        searchAdapter.clear()
        binding.query = query
        //  viewModel.searchDoctors(query)
    }

}
