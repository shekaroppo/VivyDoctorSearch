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
import com.takeaway.data.TakeawayPreferences.SortType.*
import com.takeaway.databinding.FragmentRestaurantListBinding
import com.takeaway.ui.base.BaseFragment
import javax.inject.Inject


class RestaurantListFragment : BaseFragment<FragmentRestaurantListBinding, RestaurantListViewModel>() {

    @Inject
    lateinit var restaurantListAdapter: RestaurantListAdapter

    private lateinit var searchView: SearchView

    override fun layoutId() = R.layout.fragment_restaurant_list

    override fun getViewModelClass() = RestaurantListViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        subscribeToModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        initSearchFilter(menu)
        initSortFilter(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting_group) {
            if (!item.isChecked) {
                item.isChecked = true
                val sortType = when (item.itemId) {
                    R.id.delivery_costs -> DELIVERY_COSTS
                    R.id.rating_avg -> RATING_AVG
                    R.id.popularity -> POPULARITY
                    R.id.best_match -> BEST_MATCH
                    R.id.min_cost -> MIN_COST
                    R.id.newest -> NEWEST
                    R.id.distance -> DISTANCE
                    R.id.avg_prod_price -> AVG_PROD_PRICE
                    else -> MIN_COST
                }
                viewModel.sortRestaurants(sortType)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
        viewModel.restaurantsMutableLiveData.observe(this, Observer { restaurantListAdapter.updateData(it) })
    }


    private fun initSearchFilter(menu: Menu) {
        val searchActionMenuItem = menu.findItem(R.id.menu_search)
        searchView = searchActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(onQueryTextListener(searchActionMenuItem))
    }

    private fun initSortFilter(menu: Menu) {
        when (viewModel.getSortingValue()) {
            DELIVERY_COSTS -> menu.findItem(R.id.delivery_costs).isChecked = true
            RATING_AVG -> menu.findItem(R.id.rating_avg).isChecked = true
            POPULARITY -> menu.findItem(R.id.popularity).isChecked = true
            BEST_MATCH -> menu.findItem(R.id.best_match).isChecked = true
            MIN_COST -> menu.findItem(R.id.min_cost).isChecked = true
            NEWEST -> menu.findItem(R.id.newest).isChecked = true
            DISTANCE -> menu.findItem(R.id.distance).isChecked = true
            AVG_PROD_PRICE -> menu.findItem(R.id.avg_prod_price).isChecked = true
        }
    }

    private fun onQueryTextListener(searchActionMenuItem: MenuItem): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                searchRestaurantsByName(query)
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

    private fun searchRestaurantsByName(query: String) {
        binding.query = query
        viewModel.searchRestaurantsByName("*$query*")
    }

}
