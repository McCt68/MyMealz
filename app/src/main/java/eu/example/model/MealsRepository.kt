package eu.example.model

import eu.example.model.api.MealsWebService
import eu.example.model.response.MealResponse
import eu.example.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// NOTE THIS PACKAGE MAY BE IN THE WRONG POSITION VIDEO 74 - 8,24
// IT looks like the package is at a wrong location to me ??

// This is the Model part of MVVM -
// This is where logic of data is handled
// Repository -
// Its a class that should obtain data from different sources -
// Locally or remotely

// This class is for creating an object that uses data from our Api
class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

	// Function to get meals from source (web)
	// Carefully understand that it is here in the Model (Repository) that we are -
	// obtaining the data from a source local or remotely.
	// The ViewModel then ask for that same data and get it from here ( The Model / Repository)
	// fun getMeals(): MealsCategoriesResponse = MealsCategoriesResponse(arrayListOf())
	suspend fun getMeals(): MealsCategoriesResponse {
		val response = webService.getMeals()
		cashedMeals = response.categories
		return response
	}

	private var cashedMeals = listOf<MealResponse>()

	fun getMeal(id: String): MealResponse? {
		return cashedMeals.firstOrNull {
			it.id == id
		}
	}

	/* I can access this object without instantiating it. Also called Static code.
	We can access it even if we don't have an object or state of an object.
	I think its for making some code that is related to a class available -
	to use it without creating object of that class
	 */
	companion object {
		@Volatile
		private var instance: MealsRepository? = null

		fun getInstance() = instance?: synchronized(this){
			instance?: MealsRepository().also { instance =  it}
		}
	}
}