package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.SatelliteAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.TextoSuave

/** Uma etapa do fluxo de inteligência. */
private data class Etapa(val icone: ImageVector, val titulo: String, val descricao: String)

private val etapas = listOf(
    Etapa(Icons.Outlined.SatelliteAlt, "Captação", "Satélites enviam anomalias"),
    Etapa(Icons.Outlined.Psychology, "Triagem", "Cruzamento com áreas de risco"),
    Etapa(Icons.Outlined.Campaign, "Ação", "Alerta para a Defesa Civil"),
)

/** Etapas do EcoAlert: captação → triagem → ação imediata. */
@Composable
fun FluxoInteligencia(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        etapas.forEachIndexed { indice, etapa ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(AzulEspacial, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(imageVector = etapa.icone, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = etapa.titulo, fontWeight = FontWeight.Bold)
                Text(
                    text = etapa.descricao,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
            }
            if (indice < etapas.lastIndex) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = TextoSuave,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }
        }
    }
}
