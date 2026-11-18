package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoalert.model.StatusAlerta

/** Selo colorido com a etapa atual do alerta. */
@Composable
fun StatusBadge(
    status: StatusAlerta,
    modifier: Modifier = Modifier,
    compacto: Boolean = false,
) {
    Row(
        modifier = modifier
            .background(status.cor.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
            .padding(
                horizontal = if (compacto) 8.dp else 10.dp,
                vertical = if (compacto) 3.dp else 5.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = status.icone,
            contentDescription = null,
            tint = status.cor,
            modifier = Modifier.size(if (compacto) 12.dp else 15.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = status.rotulo,
            color = status.cor,
            fontSize = if (compacto) 11.sp else 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
        )
    }
}
