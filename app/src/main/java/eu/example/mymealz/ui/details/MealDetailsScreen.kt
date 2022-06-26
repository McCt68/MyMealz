package eu.example.mymealz.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import eu.example.model.response.MealResponse
import kotlin.math.min

// Detail Screen - This is a view part of MVVM

@Composable
fun MealDetailsScreen(meal: MealResponse?) {

	val scrollState = rememberScrollState()
	val offSet = min(1f, 1-(scrollState.value / 600f))
	val size by animateDpAsState(targetValue = max(100.dp, 200.dp *offSet))

	Surface(color = MaterialTheme.colors.background) {
		Column() {
			Surface(elevation = 4.dp) {
				Row(modifier = Modifier.fillMaxWidth()) {
					Card(
						modifier = Modifier.padding(16.dp),
						shape = CircleShape,
						border = BorderStroke(
							width = 2.dp,
							color = Color.Green
						)
					) {
						Image(
							painter = rememberImagePainter(data = meal?.imageUrl,
								builder = {
									transformations(CircleCropTransformation())
								}),
							contentDescription = null,
							modifier = Modifier
								.size(size)
						)
					}
					Text(
						text = meal?.name ?: "default name",
						modifier = Modifier
							.padding(16.dp)
							.align(Alignment.CenterVertically)
					)
				}
			}

			val holiday = listOf<String>("Tenerife", "Thailand", "Berlin") 
			// Using the scrollState
			Column(modifier = Modifier.verticalScroll(scrollState)) {
				
				for (i in holiday){
					Text(text = "Holliday to $i", modifier = Modifier.padding(32.dp))
				}
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
				Text(text = "This is a Text element", modifier = Modifier.padding(32.dp))
			}

		}
	}


}

// Using this to define state for when picture is normal or expanded
enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
	Normal(Color.Magenta, size = 120.dp, borderWidth = 8.dp),
	Expanded(Color.Green, size = 200.dp, borderWidth = 24.dp)

}
