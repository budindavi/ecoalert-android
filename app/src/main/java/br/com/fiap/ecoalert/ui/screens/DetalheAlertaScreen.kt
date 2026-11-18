package br.com.fiap.ecoalert.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.SatelliteAlt
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Terrain
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.model.StatusAlerta
import br.com.fiap.ecoalert.ui.components.AlertaImagem
import br.com.fiap.ecoalert.ui.components.CriticidadeMedidor
import br.com.fiap.ecoalert.ui.components.InfoItem
import br.com.fiap.ecoalert.ui.components.StatusBadge
import br.com.fiap.ecoalert.ui.components.TipoRiscoChip
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.Espaco
import br.com.fiap.ecoalert.ui.theme.LaranjaAlerta
import br.com.fiap.ecoalert.util.Formatadores
import kotlinx.coroutines.launch

/** Detalhes de um alerta, com a ação de acionar a equipe de campo. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheAlertaScreen(
    alerta: Alerta,
    onVoltar: () -> Unit,
    onAcionarEquipe: (Alerta) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val escopo = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalhes do alerta") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulEspacial,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            Button(
                onClick = {
                    onAcionarEquipe(alerta)
                    escopo.launch {
                        snackbarHostState.showSnackbar("Equipe de campo acionada para ${alerta.municipio}.")
                    }
                },
                enabled = alerta.status.podeAcionarEquipe,
                colors = ButtonDefaults.buttonColors(containerColor = LaranjaAlerta),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = Espaco.padrao, vertical = Espaco.pequeno)
                    .height(52.dp),
            ) {
                Icon(
                    imageVector = if (alerta.status.podeAcionarEquipe) Icons.Outlined.Campaign else alerta.status.icone,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = rotuloBotao(alerta.status))
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno)
                .verticalScroll(rememberScrollState())
                .padding(Espaco.padrao),
        ) {
            AlertaImagem(
                alerta = alerta,
                raio = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
            )
            Spacer(modifier = Modifier.height(Espaco.padrao))
            Row {
                StatusBadge(status = alerta.status)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = Formatadores.tempoDecorrido(alerta.minutosDesdeDeteccao),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(Espaco.medio))
            Text(text = alerta.titulo, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(Espaco.padrao))
            CriticidadeMedidor(alerta = alerta)
            Spacer(modifier = Modifier.height(Espaco.padrao))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            ) {
                Column(
                    modifier = Modifier.padding(Espaco.padrao),
                    verticalArrangement = Arrangement.spacedBy(Espaco.pequeno),
                ) {
                    InfoItem(icone = Icons.Outlined.Place, texto = alerta.municipio)
                    InfoItem(icone = Icons.Outlined.Terrain, texto = alerta.areaVulneravel, maxLinhas = 2)
                    InfoItem(
                        icone = Icons.Outlined.SatelliteAlt,
                        texto = "${alerta.fonte.nome} (${alerta.fonte.agencia})",
                    )
                    InfoItem(
                        icone = Icons.Outlined.Schedule,
                        texto = "Detectado ${Formatadores.tempoDecorrido(alerta.minutosDesdeDeteccao)}",
                    )
                    InfoItem(
                        icone = Icons.Outlined.Groups,
                        texto = "${alerta.pessoasEmRisco} pessoas na área de risco",
                        cor = alerta.corCriticidade,
                    )
                }
            }
            Spacer(modifier = Modifier.height(Espaco.padrao))
            Text(text = "Tipos de risco", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Espaco.pequeno))
            Row(horizontalArrangement = Arrangement.spacedBy(Espaco.pequeno)) {
                alerta.tipos.forEach { tipo -> TipoRiscoChip(tipo = tipo) }
            }
            Spacer(modifier = Modifier.height(Espaco.padrao))
            Text(text = "Análise do motor de triagem", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Espaco.pequeno))
            Text(text = alerta.descricao, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

private fun rotuloBotao(status: StatusAlerta): String = when (status) {
    StatusAlerta.DETECTADO, StatusAlerta.EM_TRIAGEM -> "Acionar equipe de campo"
    StatusAlerta.EQUIPE_ACIONADA -> "Equipe a caminho"
    StatusAlerta.CONTROLADO -> "Ocorrência controlada"
}
