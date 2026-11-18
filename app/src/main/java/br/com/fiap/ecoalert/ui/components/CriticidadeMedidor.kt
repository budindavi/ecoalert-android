package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.ui.theme.TextoSuave

/** Barra com 10 segmentos que representa a nota de criticidade do alerta. */
@Composable
fun CriticidadeMedidor(alerta: Alerta, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Criticidade",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "${alerta.criticidade}/10 · ${alerta.nivel}",
                color = alerta.corCriticidade,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            for (nivel in 1..10) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = if (nivel == 10) 0.dp else 3.dp)
                        .height(10.dp)
                        .background(
                            color = if (nivel <= alerta.criticidade) {
                                alerta.corCriticidade
                            } else {
                                TextoSuave.copy(alpha = 0.18f)
                            },
                            shape = RoundedCornerShape(4.dp),
                        ),
                )
            }
        }
    }
}
