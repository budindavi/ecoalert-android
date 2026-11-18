package br.com.fiap.ecoalert.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.fiap.ecoalert.model.TipoRisco
import br.com.fiap.ecoalert.repository.AlertaRepository
import br.com.fiap.ecoalert.ui.screens.DetalheAlertaScreen
import br.com.fiap.ecoalert.ui.screens.HomeScreen
import br.com.fiap.ecoalert.ui.screens.SobreScreen

/** Telas do aplicativo. */
sealed interface Tela {
    data object Home : Tela
    data class Detalhe(val alertaId: Int) : Tela
    data object Sobre : Tela
}

/**
 * Raiz da interface: guarda a tela atual e o filtro selecionado com
 * `remember` + `mutableStateOf`. Como o filtro fica aqui, ele é mantido ao
 * voltar da tela de detalhes.
 */
@Composable
fun EcoAlertApp(repositorio: AlertaRepository) {
    var telaAtual by remember { mutableStateOf<Tela>(Tela.Home) }
    var tipoSelecionado by remember { mutableStateOf<TipoRisco?>(null) }

    BackHandler(enabled = telaAtual != Tela.Home) { telaAtual = Tela.Home }

    when (val tela = telaAtual) {
        Tela.Home -> HomeScreen(
            repositorio = repositorio,
            tipoSelecionado = tipoSelecionado,
            onSelecionarTipo = { tipoSelecionado = it },
            onAbrirAlerta = { telaAtual = Tela.Detalhe(it.id) },
            onAbrirSobre = { telaAtual = Tela.Sobre },
        )

        is Tela.Detalhe -> {
            val alerta = repositorio.obterPorId(tela.alertaId)
            if (alerta == null) {
                LaunchedEffect(tela) { telaAtual = Tela.Home }
            } else {
                DetalheAlertaScreen(
                    alerta = alerta,
                    onVoltar = { telaAtual = Tela.Home },
                    onAcionarEquipe = { repositorio.acionarEquipe(it.id) },
                )
            }
        }

        Tela.Sobre -> SobreScreen(onVoltar = { telaAtual = Tela.Home })
    }
}
