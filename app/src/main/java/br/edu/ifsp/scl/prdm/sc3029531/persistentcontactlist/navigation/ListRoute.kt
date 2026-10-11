package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ContactViewModel
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.Operation
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ui.composable.screen.ListScreen

//vai chamar o ListScreen
@Composable
fun ListRoute(contactViewModel: ContactViewModel, modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    //tá pegando o contactList e observando o seu estado (que tá lá no ContactViewModel)
    val contactList by contactViewModel.contactList.collectAsStateWithLifecycle()

    //vai ser executada a cada vez que for necessário (alteração de valor de contato)
    ListScreen(
        modifier = modifier,
        contactList = contactList,
        onViewContact = { contact ->

            //usando o viewModel para atualizar o contato e a operação
            contactViewModel.updateCurrentContactAndOperation(
                contact = contact,
                operation = Operation.VIEW
            )

            onNavigate()
        },
        onEditContact = { contact ->
            contactViewModel.updateCurrentContactAndOperation(
                contact = contact,
                operation = Operation.NEW_OR_EDIT
            )
            onNavigate()
        },
        onRemoveContact = { contact ->
            contactViewModel.removeContact(contact)
        }

    )
}