package eu.example.mymealz.ui.meals

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.example.model.MealsRepository
import eu.example.model.response.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// This is the ViewModel part of MVVM

// It will get data from the Model -
// It will have representation logic - filter and so on
// And sent it out so any observer ( View part of MVVM (Screen) can use it to represent the UI
// The ViewModel it self does not know of any Views.

// The constructor takes a repository of type MealsRepository, and set the default value to MealsRepository
class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()): ViewModel() {


//	// we dont use this anymore, Instead we use viewModel.scope
//	// when doing that we dont need to call onCleared when the viewModel is destoryed
//	// Defining our own scope where we can launch suspended coroutines function in
//	private val mealsJobScope = Job()

	// Launches a coroutine scope when the viewModel is initiated
	init {

		Log.d("TAG_COROUTINES", "We are about to launch a viewModelScope Coroutine ") // 1 - Synchronous call
		// Launching a Coroutine scope
		// In order to call and use our suspend functions, we must launch a scope for them
		viewModelScope.launch(Dispatchers.IO) {
			Log.d("TAG_COROUTINES", "We have launched a viewModelScope Coroutine ") // 3 - Asynchronous call
				val meals = getMeals()
			Log.d("TAG_COROUTINES", "We have received the asynchronous data") // 4 - Asynchronous call
				mealsState.value = meals
		}
		Log.d("TAG_COROUTINES", "Other work ") // 2 - Synchronous call
	}

	val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())


	// WE DON'Thave to call the oncleared when using viewModelScope -
	// AS IT will CALL this automatecly when the viewModel is destroyed
//	// Cancel the Coroutine when the viewModel is destroyed
//	// If the user navigates away from the screen ?? ( Note the screen is not the viewModel ??)
//	override fun onCleared() {
//		super.onCleared()
//		mealsJobScope.cancel()
//	}

	// get meals from the repository ( Model in MVVM )
	// Be very carefull to understand this is not the same method / or way at getting data, as in the repository -
	// Here in the ViewModel we get the data from the Repository, and not from an outside source !
	// It is the Model (Repository that get the data from outside, being local or remote)
	private suspend fun getMeals(): List<MealResponse>{
		return repository.getMeals().categories
	}
}