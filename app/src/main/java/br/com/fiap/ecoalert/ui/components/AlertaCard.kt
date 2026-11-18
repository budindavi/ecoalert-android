package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.SatelliteAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.util.Formatadores

/** Card de um alerta na lista principal (vertical). */
@Composable
fun AlertaCard(
    alerta: Alerta,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tipo = alerta.tipoPrincipal

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Box {
                AlertaImagem(alerta = alerta, modifier = Modifier.size(width = 92.dp, height = 100.dp))
                CriticidadeBadge(
                    alerta = alerta,
                    tamanho = 34.dp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 6.dp, y = 6.dp),
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = tipo.icone,
                        contentDescription = null,
                        tint = tipo.cor,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = tipo.rotulo,
                        color = tipo.cor,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                    )
                    StatusBadge(status = alerta.status, compacto = true)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = alerta.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(6.dp))
                InfoItem(icone = Icons.Outlined.Place, texto = alerta.municipio)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    InfoItem(
                        icone = Icons.Outlined.SatelliteAlt,
                        texto = "${alerta.fonte.nome} · ${Formatadores.tempoDecorrido(alerta.minutosDesdeDeteccao)}",
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    InfoItem(
                        icone = Icons.Outlined.Groups,
                        texto = Formatadores.numeroCompacto(alerta.pessoasEmRisco),
                    )
                }
            }
        }
    }
}
