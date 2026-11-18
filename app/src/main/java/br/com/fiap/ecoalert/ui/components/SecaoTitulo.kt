package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.Espaco

/** Título padronizado para as seções da tela. */
@Composable
fun SecaoTitulo(
    titulo: String,
    icone: ImageVector,
    modifier: Modifier = Modifier,
    subtitulo: String? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Espaco.padrao, end = Espaco.padrao, top = Espaco.padrao, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = icone, contentDescription = null, tint = AzulEspacial)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f),
        )
        if (subtitulo != null) {
            Text(text = subtitulo, style = MaterialTheme.typography.bodySmall)
        }
    }
}
