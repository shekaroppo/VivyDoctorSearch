//package com.takeaway.data.db
//
//import com.takeaway.data.TakeawayPreferences
//import com.takeaway.data.model.Favourite
//import com.takeaway.data.model.Restaurant
//import com.takeaway.data.model.RestaurantResponse
//import io.reactivex.Observable
//import io.reactivex.Single
//import java.util.concurrent.Executors
//
//import javax.inject.Inject
//
//class LocalDataSource(database: RestaurantDatabase, private val preferences: TakeawayPreferences) {
//
//    private val restaurantDao = database.restaurantDao()
//
//    //    public Observable<List<Restaurant>> getRestaurantsSorted() {
//    //        String sortingValueString = preferences.getDesiredSortingValueWithOrderType();
//    //        String sortQuery = "SELECT * FROM restaurants ORDER BY favourite DESC, status ASC, " + sortingValueString;
//    //        Cursor cursor = database.query(sortQuery, null);
//    //        List<Restaurant> restaurants = new ArrayList<>();
//    //
//    //        if (cursor != null) {
//    //            if (cursor.moveToFirst()) {
//    //                do {
//    //                    String name = cursor.getString(cursor.getColumnIndex("name"));
//    //                    int status = cursor.getInt(cursor.getColumnIndex("status"));
//    //                    boolean favourite = cursor.getInt(cursor.getColumnIndex("favourite")) == 1;
//    //                    int minCost = cursor.getInt(cursor.getColumnIndex("min_cost"));
//    //                    int deliveryCosts = cursor.getInt(cursor.getColumnIndex("delivery_costs"));
//    //                    int averageProductPrice = cursor.getInt(cursor.getColumnIndex("avg_prod_price"));
//    //                    int popularity = cursor.getInt(cursor.getColumnIndex("popularity"));
//    //                    int distance = cursor.getInt(cursor.getColumnIndex("distance"));
//    //                    float ratingAverage = cursor.getFloat(cursor.getColumnIndex("rating_avg"));
//    //                    int newest = cursor.getInt(cursor.getColumnIndex("newest"));
//    //                    int bestMatch = cursor.getInt(cursor.getColumnIndex("best_match"));
//    //
//    //                    SortingValues sortingValues = new SortingValues();
//    //                    sortingValues.setAverageProductPrice(averageProductPrice);
//    //                    sortingValues.setBestMatch(bestMatch);
//    //                    sortingValues.setDeliveryCosts(deliveryCosts);
//    //                    sortingValues.setDistance(distance);
//    //                    sortingValues.setMinCost(minCost);
//    //                    sortingValues.setRatingAverage(ratingAverage);
//    //                    sortingValues.setPopularity(popularity);
//    //                    sortingValues.setNewest(newest);
//    //                    Restaurant restaurant = new Restaurant(name, StatusConverter.toStatus(status),
//    //                            sortingValues, favourite);
//    //                    restaurants.add(restaurant);
//    //                } while (cursor.moveToNext());
//    //            }
//    //            cursor.close();
//    //        }
//    //        return Observable.just(restaurants);
//    //    }
//    //
//    //    public Observable<List<Restaurant>> getRestaurantsSorted(TakeawayPreferences.SortType sortType) {
//    //        preferences.setSortType(sortType);
//    //        return getRestaurantsSorted();
//    //    }
//
//    fun save(restaurantResponse: RestaurantResponse): Single<RestaurantResponse> {
//        return Single.fromCallable {
//            restaurantDao.insertRestaurants(restaurantResponse.restaurants)
//            setFavourites()
//            restaurantResponse
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
//    fun getRestaurantList(): List<Restaurant> {
//        return restaurantDao.getRestaurants()
//    }
//    //
//    //    public Observable<List<Restaurant>> search(String query) {
//    //        return restaurantDao.searchByName(query).toObservable();
//    //    }
//    //
//    //    public TakeawayPreferences getPreferences() {
//    //        return preferences;
//    //    }
//    //
//    //    // This is done because of user's favourite's are note coming from rest api.
//    //    // we are adding
//    //    private void setFavourites() {
//    //        List<Favourite> favourites = restaurantDao.getFavourites();
//    //        for (Favourite favourite : favourites) {
//    //            Restaurant restaurant = restaurantDao.getByName(favourite.getRestaurantName());
//    //            restaurant.setFavourite(true);
//    //            restaurantDao.update(restaurant);
//    //        }
//    //    }
//}
