package eu.example.mymealz.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import eu.example.model.response.MealResponse

// Detail Screen - This is a view part of MVVM

@Composable
fun MealDetailsScreen(meal: MealResponse) {
	Column() {
		Row() {
			Card() {
				Image(
					painter = rememberImagePainter(data = meal.imageUrl,
					builder = {
						transformations(CircleCropTransformation())
					}),
					contentDescription = null,
					modifier = Modifier.size(200.dp)
				)
			}
			Text(text = meal.name)
		}
		Button(onClick = { /*TODO*/ }) {
			Text(text = "Change state of meal profile picture")
		}
	}
}
