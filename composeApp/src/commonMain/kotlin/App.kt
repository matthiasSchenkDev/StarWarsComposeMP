import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import starship.presentation.screens.StarshipsScreen
import starship.presentation.viewmodel.StarshipViewModel

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        val starshipViewModel = koinInject<StarshipViewModel>()
        MaterialTheme {

            // Omitted: Navigation between different screens

            val starshipsState by starshipViewModel.starshipsState.collectAsState()
            StarshipsScreen(starshipsState)
        }
        starshipViewModel.getStarships()
    }
}