package br.com.fiap.ecoalert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.ecoalert.repository.AlertaRepository
import br.com.fiap.ecoalert.ui.EcoAlertApp
import br.com.fiap.ecoalert.ui.theme.EcoAlertTheme

class MainActivity : ComponentActivity() {

    /** Repositório único, compartilhado por todas as telas. */
    private val repositorio = AlertaRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoAlertTheme {
                EcoAlertApp(repositorio = repositorio)
            }
        }
    }
}
