package eu.example.mymealz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.example.mymealz.ui.meals.MealsCategoriesScreen
import eu.example.mymealz.ui.theme.MyMealzTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyMealzTheme {
					MealsCategoriesScreen()
			}
		}
	}
}

// Navigation
@Composable
private fun FoodzApp(){

	// Used to remember the state of the navController
	val navController = rememberNavController()
	
	NavHost(navController = navController, startDestination = ""){
		composable(route = ""){
			MealsCategoriesScreen()
		}

	}

}