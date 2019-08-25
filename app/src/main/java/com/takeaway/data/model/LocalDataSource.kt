//package com.takeaway.data.model
//
//import android.database.Cursor
//
//import com.mert.where2eat.data.Where2EatPreferences
//import com.mert.where2eat.data.model.SortingValues
//
//import java.util.ArrayList
//import java.util.concurrent.Callable
//import java.util.concurrent.Executors
//
//import javax.inject.Inject
//
//import io.reactivex.Observable
//
///**
// * Created by mertk on 1/7/2018.
// */
//
//class LocalDataSource @Inject
//constructor(private val database: RestaurantDatabase, val preferences: Where2EatPreferences) {
//
//    private val restaurantDao: RestaurantDao
//
//    /**
//     * The reason of this query implemented manually instead of calling from
//     * [com.mert.where2eat.data.db.RestaurantDao] is that [com.mert.where2eat.data.model.SortingValues]
//     * can dynamically change during the app usage. And unfortunately room doesn't support
//     * dynamic ORDER BY column names
//     *
//     * @return Sorted restaurant list with following priority "Favourites, Opening state, Sorting Option"
//     * @see [Related StackOverflow Question](https://stackoverflow.com/a/48038439/3640576)
//     */
//    val restaurantsSorted: Observable<List<Restaurant>>
//        get() {
//            val sortingValueString = preferences.getDesiredSortingValueWithOrderType()
//            val sortQuery = "SELECT * FROM restaurants ORDER BY favourite DESC, status ASC, $sortingValueString"
//            val cursor = database.query(sortQuery, null)
//            val restaurants = ArrayList<Restaurant>()
//
//            if (cursor != null) {
//                if (cursor!!.moveToFirst()) {
//                    do {
//                        val name = cursor!!.getString(cursor!!.getColumnIndex("name"))
//                        val status = cursor!!.getInt(cursor!!.getColumnIndex("status"))
//                        val favourite = cursor!!.getInt(cursor!!.getColumnIndex("favourite")) == 1
//                        val minCost = cursor!!.getInt(cursor!!.getColumnIndex("min_cost"))
//                        val deliveryCosts = cursor!!.getInt(cursor!!.getColumnIndex("delivery_costs"))
//                        val averageProductPrice = cursor!!.getInt(cursor!!.getColumnIndex("avg_prod_price"))
//                        val popularity = cursor!!.getInt(cursor!!.getColumnIndex("popularity"))
//                        val distance = cursor!!.getInt(cursor!!.getColumnIndex("distance"))
//                        val ratingAverage = cursor!!.getFloat(cursor!!.getColumnIndex("rating_avg"))
//                        val newest = cursor!!.getInt(cursor!!.getColumnIndex("newest"))
//                        val bestMatch = cursor!!.getInt(cursor!!.getColumnIndex("best_match"))
//
//                        val sortingValues = SortingValues()
//                        sortingValues.setAverageProductPrice(averageProductPrice)
//                        sortingValues.setBestMatch(bestMatch)
//                        sortingValues.setDeliveryCosts(deliveryCosts)
//                        sortingValues.setDistance(distance)
//                        sortingValues.setMinCost(minCost)
//                        sortingValues.setRatingAverage(ratingAverage)
//                        sortingValues.setPopularity(popularity)
//                        sortingValues.setNewest(newest)
//                        val restaurant = Restaurant(name, StatusConverter.toStatus(status),
//                                sortingValues, favourite)
//                        restaurants.add(restaurant)
//                    } while (cursor!!.moveToNext())
//                }
//                cursor!!.close()
//            }
//            return Observable.just(restaurants)
//        }
//
//    init {
//        this.restaurantDao = database.restaurantDao()
//    }
//
//    fun getRestaurantsSorted(sortType: Where2EatPreferences.SortType): Observable<List<Restaurant>> {
//        preferences.setSortType(sortType)
//        return restaurantsSorted
//    }
//
//    fun save(restaurants: List<Restaurant>): Observable<List<Restaurant>> {
//        return Observable.fromCallable {
//            restaurantDao.insertAll(restaurants)
//            setFavourites()
//            restaurants
//        }
//    }
//
//    fun addToFavorite(name: String) {
//        Executors.newSingleThreadExecutor()
//                .execute {
//                    restaurantDao.addToFavorite(Favourite(name))
//                    val restaurant = restaurantDao.getByName(name)
//                    restaurant.favourite = true
//                    restaurantDao.update(restaurant)
//                }
//    }
//
//    fun removeFromFavourite(name: String) {
//        Executors.newSingleThreadExecutor()
//                .execute {
//                    restaurantDao.removeFromFavourite(Favourite(name))
//                    val restaurant = restaurantDao.getByName(name)
//                    restaurant.favourite = false
//                    restaurantDao.update(restaurant)
//                }
//    }
//
//    fun search(query: String): Observable<List<Restaurant>> {
//        return restaurantDao.searchByName(query).toObservable()
//    }
//
//    // This is done because of user's favourite's are note coming from rest api.
//    // we are adding
//    private fun setFavourites() {
//        val favourites = restaurantDao.getFavourites()
//        for (favourite in favourites) {
//            val restaurant = restaurantDao.getByName(favourite.restaurantName)
//            restaurant.favourite = true
//            restaurantDao.update(restaurant)
//        }
//    }
//}
