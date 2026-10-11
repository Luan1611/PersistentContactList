package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ContactViewModel
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ui.composable.screen.ContactScreen

//responsavel por recuperar o objeto do ViewModel e passar para a screen
@Composable
fun ContactRoute(contactViewModel: ContactViewModel, modifier: Modifier = Modifier, onDone: () -> Unit) {

    //vai vir do ContactViewModel
    //contato sem ser atualizado
    val contact by contactViewModel.currentContact.collectAsStateWithLifecycle()

    //vai vir do ContactViewModel
    val operation by contactViewModel.operation.collectAsStateWithLifecycle()

    ContactScreen(
        contact = contact,
        modifier = modifier,
        operation = operation,

        //newOrEditedContact vem do arquivo ContactScreen.kt, passado pra função onSaveAndQuit
        onSaveAndQuit = { newOrEditedContact ->
            //passo o contact que vem da minha contactscreen

            //o "it" é o contato que tá sendo passado para dentro deste lambda
            contactViewModel.saveContact(newOrEditedContact)
            onDone()
        }
    )
}