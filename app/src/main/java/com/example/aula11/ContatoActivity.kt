package com.example.aula11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula11.ui.theme.Aula11Theme

class ContatoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutContato()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutContato(){

    var nome by remember {
        mutableStateOf("")
    }

    var fone by remember {
        mutableStateOf("")
    }

    var listaContatos by remember {
        mutableStateOf(listOf<Contato>())
    }

    var focusManager = LocalFocusManager.current

    var context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        TextField(value = nome,
            onValueChange = {nome = it},
            label = {Text(text = "Nome do contato")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = fone,
            onValueChange = {fone= it},
            label = {Text(text = "Telefone")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {

               if( nome.isNotBlank() && fone.isNotBlank()){
                   // lista de contatos e adciona um novo contato
                   listaContatos += Contato(nome, fone)
                   nome = "" // limpa o campo
                   fone = "" // limpa o campo
                   focusManager.clearFocus() // limpa o foco ativo da UI
               }

        },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Salvar contato")
        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(Modifier.fillMaxWidth()){
            items(listaContatos){
                contato -> ContatoItem(contato = contato,
                onDeleteClick = {
                    // Aplicando filtro para apagar lista de contatos, os contatos seram readicionados na lista.
                    // Execeto o contato que você clicou X
                    listaContatos = listaContatos.filter {
                        it != contato
                    }
                })
            }

        }
    }
}

@Composable
fun ContatoItem(contato : Contato, onDeleteClick: () -> Unit){

    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)){

        Text(text = "${contato.nome} (${contato.fone})",
            fontSize = 20.sp)

        Button(onClick = { onDeleteClick }) {
            Text(text = "X")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewContato(){

}