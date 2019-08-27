//package com.takeaway.restaurantlist
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.takeaway.MainCoroutineRule
//import com.takeaway.data.repository.TakeawayRepository
//import com.takeaway.ui.restaurantlist.RestaurantListViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//@ExperimentalCoroutinesApi
//class RestaurantListViewModelTest {
//
//    private lateinit var restaurantListViewModel: RestaurantListViewModel
//    private lateinit var takeawayRepository: TakeawayRepository
//
//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()
//
//    // Executes each task synchronously using Architecture Components.
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    val task = Task("Title1", "Description1")
//
//    @Before
//    fun setupViewModel() {
//        takeawayRepository = FakeRepository()
//        restaurantListViewModel = TaskDetailViewModel(tasksRepository)
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
