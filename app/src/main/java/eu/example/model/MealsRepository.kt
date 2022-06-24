package eu.example.model

import eu.example.model.api.MealsWebService
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
class MealsRepository(private val webService: MealsWebService = MealsWebService()){

	// Function to get meals from source (web)
	// Carefully understand that it is here in the Model (Repository) that we are -
	// obtaining the data from a source local or remotely.
	// The ViewModel then ask for that same data and get it from here ( The Model / Repository)
	// fun getMeals(): MealsCategoriesResponse = MealsCategoriesResponse(arrayListOf())
	suspend fun getMeals(): MealsCategoriesResponse{
		return webService.getMeals()
	}
}