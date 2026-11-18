package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.TipoRisco
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.TextoPrincipal

/**
 * Chip de tipo de risco, usado no filtro e na tela de detalhes.
 *
 * [tipo] nulo representa a opção "Todos". Quando [onClick] é nulo, o chip é
 * apenas informativo.
 */
@Composable
fun TipoRiscoChip(
    tipo: TipoRisco?,
    modifier: Modifier = Modifier,
    selecionado: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val rotulo = tipo?.rotulo ?: "Todos"
    val icone = tipo?.icone ?: Icons.Outlined.Public
    val cor = tipo?.cor ?: AzulEspacial

    val conteudo: @Composable () -> Unit = {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icone,
                contentDescription = null,
                tint = if (selecionado) Color.White else cor,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = rotulo,
                fontWeight = FontWeight.SemiBold,
                color = if (selecionado) Color.White else TextoPrincipal,
            )
        }
    }

    val forma = RoundedCornerShape(24.dp)
    val cores = if (selecionado) cor else Color.White
    val borda = BorderStroke(1.dp, if (selecionado) cor else cor.copy(alpha = 0.35f))
    val semantica = Modifier.semantics {
        selected = selecionado
        role = Role.Tab
    }

    if (onClick != null) {
        Surface(
            onClick = onClick,
            shape = forma,
            color = cores,
            border = borda,
            modifier = modifier.then(semantica),
            content = conteudo,
        )
    } else {
        Surface(shape = forma, color = cores, border = borda, modifier = modifier, content = conteudo)
    }
}
