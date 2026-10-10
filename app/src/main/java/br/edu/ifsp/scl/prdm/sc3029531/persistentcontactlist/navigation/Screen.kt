package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.navigation

sealed class Screen(val route: String) {

    data object List: Screen(route = "list_screen")
    data object Contact: Screen(route= "contact_screen")

}