package eu.example.mymealz.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import eu.example.model.MealsRepository
import eu.example.model.response.MealResponse

class MealDetailsViewModel(
	private val savedStateHandle: SavedStateHandle,

) : ViewModel() {
	private val repository: MealsRepository = MealsRepository.getInstance() // not sure if it is the right repository ??
	var mealState = mutableStateOf<MealResponse?>(null)

	init {
		val mealId = savedStateHandle.get<String>("meal_category_id") ?: ""
		mealState.value = repository.getMeal(mealId)
	}
}