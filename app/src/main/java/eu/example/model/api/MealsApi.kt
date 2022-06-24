package eu.example.model.api

import eu.example.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// This class returns the actual retrofit response for our particular request
class MealsWebService {

	// make a var that is not initialized yet
	private lateinit var api: MealsApi
	// initialize the interface
	init {
		val retrofit = Retrofit.Builder()
			.baseUrl("https://www.themealdb.com/api/json/v1/1/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()

		api = retrofit.create(MealsApi::class.java)

	}

	// Using a coroutines suspend function
	suspend fun getMeals(): MealsCategoriesResponse {
		return api.getMeals()
	}

	//
	interface MealsApi {
		@GET("categories.php")
		suspend fun getMeals(): MealsCategoriesResponse //
	}
}