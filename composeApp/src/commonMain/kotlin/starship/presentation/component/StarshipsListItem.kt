package starship.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import starship.domain.model.Starship
import util.Constants
import util.Strings

@Composable
fun StarshipListItem(
    starship: Starship,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Constants.MARGIN_MEDIUM.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Constants.MARGIN_SMALL.dp)
        ) {
            Text(text = "${Strings.STARSHIP_NAME}${starship.name}")
            Text(
                modifier = Modifier.padding(top = Constants.MARGIN_SMALL.dp),
                text = "${Strings.STARSHIP_MODEL}${starship.model}"
            )
            starship.crew?.let {
                Text(
                    modifier = Modifier.padding(top = Constants.MARGIN_SMALL.dp),
                    text = "${Strings.STARSHIP_CREW}${starship.crew}"
                )
            }
        }
    }
}
