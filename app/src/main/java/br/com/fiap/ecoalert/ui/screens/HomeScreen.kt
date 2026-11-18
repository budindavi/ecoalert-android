package br.com.fiap.ecoalert.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.model.TipoRisco
import br.com.fiap.ecoalert.repository.AlertaRepository
import br.com.fiap.ecoalert.ui.components.AlertaCard
import br.com.fiap.ecoalert.ui.components.AlertasCriticosRow
import br.com.fiap.ecoalert.ui.components.PainelResumo
import br.com.fiap.ecoalert.ui.components.SecaoTitulo
import br.com.fiap.ecoalert.ui.components.TiposRiscoRow
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.Espaco
import br.com.fiap.ecoalert.ui.theme.TextoSuave

/**
 * Tela principal: indicadores, alertas críticos, filtro por tipo de risco e
 * lista de alertas.
 *
 * O estado do filtro é recebido de fora ([tipoSelecionado]) e a filtragem é
 * feita pelo repositório, nunca pelos componentes de apresentação.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    repositorio: AlertaRepository,
    tipoSelecionado: TipoRisco?,
    onSelecionarTipo: (TipoRisco?) -> Unit,
    onAbrirAlerta: (Alerta) -> Unit,
    onAbrirSobre: () -> Unit,
) {
    val alertas = repositorio.obterPorTipo(tipoSelecionado)
    val criticos = repositorio.obterCriticos()
    val rotuloFiltro = tipoSelecionado?.rotulo ?: "Todos"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Public, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "EcoAlert", fontWeight = FontWeight.ExtraBold)
                    }
                },
                actions = {
                    IconButton(onClick = onAbrirSobre) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "Sobre o EcoAlert")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulEspacial,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingInterno ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            item {
                PainelResumo(
                    ativos = repositorio.totalAtivos,
                    criticos = repositorio.totalCriticos,
                    pessoasEmRisco = repositorio.pessoasEmRisco,
                    satelites = repositorio.totalSatelites,
                )
            }
            item { SecaoTitulo(titulo = "Alertas críticos", icone = Icons.Outlined.WarningAmber) }
            item { AlertasCriticosRow(alertas = criticos, onSelecionar = onAbrirAlerta) }
            item { SecaoTitulo(titulo = "Tipos de risco", icone = Icons.Outlined.Tune) }
            item { TiposRiscoRow(selecionado = tipoSelecionado, onSelecionar = onSelecionarTipo) }
            item {
                SecaoTitulo(
                    titulo = "Alertas",
                    icone = Icons.AutoMirrored.Outlined.List,
                    subtitulo = "$rotuloFiltro · ${alertas.size}",
                    modifier = Modifier.padding(bottom = Espaco.pequeno),
                )
            }
            if (alertas.isEmpty()) {
                item { ListaVazia() }
            } else {
                items(alertas, key = { it.id }) { alerta ->
                    AlertaCard(
                        alerta = alerta,
                        onClick = { onAbrirAlerta(alerta) },
                        modifier = Modifier.padding(
                            start = Espaco.padrao,
                            end = Espaco.padrao,
                            bottom = Espaco.medio,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
private fun ListaVazia() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Outlined.Inbox,
            contentDescription = null,
            tint = TextoSuave,
            modifier = Modifier.size(48.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Nenhum alerta ativo para este tipo de risco.",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
