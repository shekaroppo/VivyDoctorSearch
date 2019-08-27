//package com.takeaway.restaurantlist
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.takeaway.MainCoroutineRule
//import com.takeaway.data.repository.TakeawayRepository
//import com.takeaway.data.services.ApiService
//import com.takeaway.ui.restaurantlist.RestaurantListViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import okhttp3.mockwebserver.MockWebServer
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.io.IOException
//
//@ExperimentalCoroutinesApi
//class RestaurantListViewModelTest {
//
//    private lateinit var restaurantListViewModel: RestaurantListViewModel
//    private lateinit var takeawayRepository: TakeawayRepository
//    private lateinit var service: ApiService
//    private lateinit var mockWebServer: MockWebServer
//
//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()
//
//    // Executes each task synchronously using Architecture Components.
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//
//    @Throws(IOException::class)
//    @Before
//    fun createService() {
//        mockWebServer = MockWebServer()
//        service = Retrofit.Builder()
//                .baseUrl(mockWebServer.url("/"))
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//        takeawayRepository = TakeawayRepository(service)
//        restaurantListViewModel = TaskDetailViewModel(tasksRepository)
//    }
//
//    @Throws(IOException::class)
//    @After
//    fun stopService() {
//        mockWebServer.shutdown()
//    }
//
//    @Test
//    fun completeTask() {
//        taskDetailViewModel.start(task.id)
//
//        // Verify that the task was active initially
//        assertThat(tasksRepository.tasksServiceData[task.id]?.isCompleted).isFalse()
//
//        // When the ViewModel is asked to complete the task
//        taskDetailViewModel.setCompleted(true)
//
//        // Then the task is completed and the snackbar shows the correct message
//        assertThat(tasksRepository.tasksServiceData[task.id]?.isCompleted).isTrue()
//        assertSnackbarMessage(taskDetailViewModel.snackbarText, R.string.task_marked_complete)
//    }
//
//
//}
