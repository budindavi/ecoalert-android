package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.ui.theme.Espaco

/** Lista horizontal (LazyRow) com os alertas críticos em destaque. */
@Composable
fun AlertasCriticosRow(
    alertas: List<Alerta>,
    onSelecionar: (Alerta) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Espaco.padrao, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(Espaco.medio),
    ) {
        items(alertas, key = { it.id }) { alerta ->
            AlertaCriticoCard(alerta = alerta, onClick = { onSelecionar(alerta) })
        }
    }
}
