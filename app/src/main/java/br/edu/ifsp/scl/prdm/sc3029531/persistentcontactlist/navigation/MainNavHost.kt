package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ContactViewModel

//é ele quem cria o grafo de navegação pra gente
// ele que vai ser chamado na MainActivity, na parte central do scaffold
@Composable
fun MainNavHost(

    //vai vir da Main Activity pra gente
    navHostController: NavHostController,

    contactViewModel: ContactViewModel,
    modifier: Modifier = Modifier,
) {

    //não faz nada com o ViewModel, não precisa do ViewModel
    NavHost(
        navController = navHostController,
        startDestination = Screen.List.route,
        modifier = modifier,
    ) {

        composable(
            route = Screen.List.route
        ) {
            ListRoute(
                modifier = Modifier,
                contactViewModel = contactViewModel,
                onNavigate = { navHostController.navigate(Screen.Contact.route) }
            )
        }

        composable(
            route = Screen.Contact.route
        ) {
            ContactRoute(contactViewModel) {
                navHostController.popBackStack()
            }
        }
    }

}