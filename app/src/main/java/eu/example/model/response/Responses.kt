package eu.example.model.response

import com.google.gson.annotations.SerializedName

// Gson deserialization means-
// transform Json to a data class

// Our MealCategoriesResponse response contains a list of categories
data class MealsCategoriesResponse(val categories: List<MealResponse>)

// And each Category has 4 fields
// we use @SerializedName to map the Json key we got from the server-
// to the val name we give our own vals.
data class MealResponse(
	@SerializedName("idCategory") val id: String,
	@SerializedName("strCategory") val name: String,
	@SerializedName("strCategoryDescription") val description: String,
	@SerializedName("strCategoryThumb") val imageUrl: String)

