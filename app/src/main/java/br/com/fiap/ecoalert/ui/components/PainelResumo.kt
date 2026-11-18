package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.SatelliteAlt
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.Espaco
import br.com.fiap.ecoalert.ui.theme.VerdeOnline
import br.com.fiap.ecoalert.util.Formatadores

/** Cabeçalho da central com os indicadores gerais de monitoramento. */
@Composable
fun PainelResumo(
    ativos: Int,
    criticos: Int,
    pessoasEmRisco: Int,
    satelites: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AzulEspacial, RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .padding(start = Espaco.padrao, end = Espaco.padrao, top = 4.dp, bottom = Espaco.padrao),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .background(VerdeOnline, CircleShape),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Monitoramento orbital ativo", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Central de alertas climáticos",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ResumoCard(
                valor = ativos.toString(),
                rotulo = "Ativos",
                icone = Icons.Outlined.NotificationsActive,
                modifier = Modifier.weight(1f),
            )
            ResumoCard(
                valor = criticos.toString(),
                rotulo = "Críticos",
                icone = Icons.Outlined.WarningAmber,
                modifier = Modifier.weight(1f),
            )
            ResumoCard(
                valor = Formatadores.numeroCompacto(pessoasEmRisco),
                rotulo = "Em risco",
                icone = Icons.Outlined.Groups,
                modifier = Modifier.weight(1.35f),
            )
            ResumoCard(
                valor = satelites.toString(),
                rotulo = "Satélites",
                icone = Icons.Outlined.SatelliteAlt,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

/** Indicador numérico compacto do painel. */
@Composable
fun ResumoCard(
    valor: String,
    rotulo: String,
    icone: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.14f), RoundedCornerShape(14.dp))
            .padding(10.dp),
    ) {
        Icon(imageVector = icone, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = valor,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            softWrap = false,
        )
        Text(
            text = rotulo,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 11.sp,
            maxLines = 1,
            softWrap = false,
        )
    }
}
