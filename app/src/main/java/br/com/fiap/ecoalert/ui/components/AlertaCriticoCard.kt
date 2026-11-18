package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Place
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.ui.theme.LaranjaAlerta
import br.com.fiap.ecoalert.util.Formatadores

/** Card de alerta crítico exibido na lista horizontal de destaques. */
@Composable
fun AlertaCriticoCard(
    alerta: Alerta,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    largura: Dp = 240.dp,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(largura)
            .height(218.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Box {
            AlertaImagem(
                alerta = alerta,
                raio = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .background(LaranjaAlerta, RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = alerta.tipoPrincipal.icone,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(13.dp),
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = alerta.tipoPrincipal.rotulo,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            CriticidadeBadge(
                alerta = alerta,
                tamanho = 36.dp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = alerta.titulo,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                InfoItem(
                    icone = Icons.Outlined.Place,
                    texto = alerta.municipio,
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(6.dp))
                InfoItem(
                    icone = Icons.Outlined.Groups,
                    texto = Formatadores.numeroCompacto(alerta.pessoasEmRisco),
                    cor = alerta.corCriticidade,
                )
            }
        }
    }
}
