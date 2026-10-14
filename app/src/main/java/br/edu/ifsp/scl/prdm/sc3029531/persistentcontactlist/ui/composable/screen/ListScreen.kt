package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ui.composable.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity.Contact
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.ui.theme.PersistentContactListTheme
import kotlin.collections.listOf

@Composable
fun ListScreen(
    contactList: List<Contact>,
    onViewContact: (Contact) -> Unit,
    onEditContact: (Contact) -> Unit,
    onRemoveContact: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier.fillMaxWidth()) {

        //cria um escopo e itera sobre uma coleção passada à ele
        //é como se fosse um "for each" com escopo de composable
        items(items = contactList, key = { it.id  } ) { contact ->

            var expanded by remember{ mutableStateOf(value = false) }

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {
                                onViewContact(contact)
                            },
                            onLongClick = { expanded = true }
                        )
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = contact.name, fontSize = 24.sp)
                        Text(text = contact.phone)
                    }
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {

                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = {
                        onEditContact(contact)
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Remove") },
                    onClick = {
                        onRemoveContact(contact)
                        expanded = false
                    }
                )

            }

        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ListScreenPreview() {
    PersistentContactListTheme {
        Surface {
            ListScreen(
                contactList = listOf(),
                onViewContact = {},
                onEditContact = {},
                onRemoveContact = {}
            )
        }
    }
}