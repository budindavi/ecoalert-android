package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.TipoRisco
import br.com.fiap.ecoalert.ui.theme.Espaco

/** Opções do filtro: "Todos" (null) seguido de todos os valores do enum. */
private val opcoes: List<TipoRisco?> = listOf(null) + TipoRisco.entries

/** Lista horizontal (LazyRow) para selecionar o tipo de risco. */
@Composable
fun TiposRiscoRow(
    selecionado: TipoRisco?,
    onSelecionar: (TipoRisco?) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Espaco.padrao, vertical = Espaco.pequeno),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(opcoes, key = { it?.name ?: "TODOS" }) { tipo ->
            TipoRiscoChip(
                tipo = tipo,
                selecionado = tipo == selecionado,
                onClick = { onSelecionar(tipo) },
            )
        }
    }
}
