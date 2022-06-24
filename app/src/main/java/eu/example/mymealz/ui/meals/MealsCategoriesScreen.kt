package eu.example.mymealz.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.* // also import get & set ??
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import eu.example.model.response.MealResponse
import eu.example.mymealz.ui.theme.MyMealzTheme

/* Implementing MVVM - Model - View - ViewModel */


// This is the View in MVVM representing the MainScreen of the App
// This is what is show to the user. Columns, Rows, Text, Cards, Images etc..
// In here we should only have UI related things -
// We should not have any logic in this @composable, besides UI logic -
// which we should also seek to put into the ViewModel
@Composable
fun MealsCategoriesScreen() {

	// Instantiating a viewModel object from the ViewModel class
	// So it now holds a reference to the viewModel
	// We bind the ViewModel (MealsCategoriesViewModel) to the composable (The View) -
	// So the viewModel only lives as long as this composable view does.
	val viewModel: MealsCategoriesViewModel = viewModel()
	val meals = viewModel.mealsState.value


	// This was used for running the Coroutines directly in the composable screen
	// that is bad practise - its better to run it in the viewModel
//	// Launching a Coroutine scope
//	// In order to call and use our suspend functions, we must launch a scope for them
//	// Using a launchedEffect to prevent the coroutine to be executed everytime the Composable function updates its states
//	// The LaunchedEffect Coroutine will be called only once ( The first time the composable executes)
//	val coroutineScope = rememberCoroutineScope()
//	LaunchedEffect(key1 = "GET_MEALS"){
//		coroutineScope.launch(Dispatchers.IO) {
//			val meals = viewModel.getMeals()
//			rememberedMeals.value = meals
//		}
//	}

	LazyColumn(contentPadding = PaddingValues(16.dp)) {
		items(meals) { meal ->
			// recomposes when the state value change
			MealCategory(meal)
		}
	}
}

@Composable
fun MealCategory(meal: MealResponse) {

	// At default the details will not be expanded.
	var isExpanded by remember {
		mutableStateOf(false)
	}

	Card(
		shape = RoundedCornerShape(8.dp),
		elevation = 2.dp,
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 16.dp)
	) {
		Row(modifier = Modifier.animateContentSize()) {

			// Basic use of Coil library
			Image(
				painter = rememberImagePainter(meal.imageUrl),
				contentDescription = null,
				modifier = Modifier
					.size(88.dp)
					.padding(4.dp)
					.align(Alignment.CenterVertically)
			)

			Column(
				modifier = Modifier
					.align(Alignment.CenterVertically)
					.fillMaxWidth(0.8f) // fill 80 % so there is room for the expandable Icon
					.padding(16.dp)
			) {
				Text(
					text = meal.name,
					style = MaterialTheme.typography.h6
				)

				// Used to change transparency. Maybe could also be used for other things ??
				CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
					Text(
						text = meal.description,
						textAlign = TextAlign.Start,
						style = MaterialTheme.typography.subtitle2,
						overflow = TextOverflow.Ellipsis,
						maxLines = if (isExpanded) 40 else 4 // unfold details when isExpanded is true
					)
				}
			}
			// Fills the last 20 %
			Icon(
				imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
				contentDescription = "Expand row icon",
				modifier = Modifier
					.padding(19.dp)
					.align(if (isExpanded) Alignment.Bottom else Alignment.CenterVertically)
					.clickable { isExpanded = !isExpanded } // unfold details when clicked
			)
		}
	}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	MyMealzTheme {
		MealsCategoriesScreen()
	}
}