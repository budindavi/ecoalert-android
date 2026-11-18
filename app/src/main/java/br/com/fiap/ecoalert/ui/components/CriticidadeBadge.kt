package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoalert.model.Alerta

/** Selo circular com a nota de criticidade (1 a 10) e a cor do nível de risco. */
@Composable
fun CriticidadeBadge(
    alerta: Alerta,
    modifier: Modifier = Modifier,
    tamanho: Dp = 40.dp,
) {
    Box(
        modifier = modifier
            .size(tamanho)
            .background(alerta.corCriticidade, CircleShape)
            .border(2.dp, Color.White, CircleShape)
            .semantics {
                contentDescription = "Criticidade ${alerta.criticidade} de 10, nível ${alerta.nivel}"
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = alerta.criticidade.toString(),
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = (tamanho.value * 0.42f).sp,
        )
    }
}
