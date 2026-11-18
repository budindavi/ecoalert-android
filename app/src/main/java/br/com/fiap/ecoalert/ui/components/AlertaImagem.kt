package br.com.fiap.ecoalert.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoalert.model.Alerta

/** Imagem de um alerta, recortada com cantos arredondados. */
@Composable
fun AlertaImagem(
    alerta: Alerta,
    modifier: Modifier = Modifier,
    raio: Dp = 12.dp,
) {
    Image(
        painter = painterResource(id = alerta.imagem),
        contentDescription = "Imagem do alerta de ${alerta.tipoPrincipal.rotulo}",
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(raio)),
    )
}
