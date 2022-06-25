package eu.example.mymealz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
// import androidx.navigation.navArgument
import androidx.navigation.navigation
import eu.example.mymealz.ui.details.MealDetailsScreen
import eu.example.mymealz.ui.details.MealDetailsViewModel
import eu.example.mymealz.ui.meals.MealsCategoriesScreen
import eu.example.mymealz.ui.theme.MyMealzTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyMealzTheme {
				// The minaActivity is routing us to the toplevel composable whcih has navigation
				FoodzApp()
			}
		}
	}
}

// In the top level composabel I have navigation
@Composable
private fun FoodzApp() {

	// Used to remember the state of the navController
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = "destination_meals_list") {
		composable(route = "destination_meals_list") {
			// Passing something along with the route with /$
			MealsCategoriesScreen { navigationMealId ->
				navController.navigate("destination_meal_details/$navigationMealId")
			}
		}
		composable(
			route = "destination_meal_details/{meal_category_id}",
			arguments = listOf(navArgument("meal_category_id") {
				type = NavType.StringType
			})
		)
		{
			val viewModel: MealDetailsViewModel = viewModel()
			MealDetailsScreen(meal = viewModel.mealState.value)

		}

	}

}