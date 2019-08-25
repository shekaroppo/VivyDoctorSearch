package com.takeaway.ui.restaurantlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.takeaway.R
import com.takeaway.data.model.Restaurant
import com.takeaway.data.model.RestaurantResponse
import com.takeaway.databinding.FragmentRestaurantListBinding
import com.takeaway.ui.base.BaseFragment
import com.takeaway.utils.DataWrapper
import javax.inject.Inject


class RestaurantListFragment : BaseFragment<FragmentRestaurantListBinding, RestaurantListViewModel>() {

    @Inject
    lateinit var restaurantListAdapter: RestaurantListAdapter

    @Inject
    lateinit var eventHandler: RestaurantEventHandler

    private lateinit var searchView: SearchView

    override fun layoutId() = R.layout.fragment_restaurant_list

    override fun getViewModelClass() = RestaurantListViewModel::class.java

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
            adapter = restaurantListAdapter
        }
    }

    private fun subscribeToModel() {
        binding.restaurantListViewModel = viewModel
        binding.eventHandler = eventHandler
        viewModel.restaurantsMutableLiveData.observe(this, Observer { restaurantListAdapter.updateData(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchActionMenuItem = menu.findItem(R.id.menu_search)
        searchView = searchActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(onQueryTextListener(searchActionMenuItem))
    }

    private fun onQueryTextListener(searchActionMenuItem: MenuItem): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                getRestaurant(query)
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

    private fun getRestaurant(query: String) {
        binding.query = query
        //  viewModel.getRestaurant(query)
    }

}
