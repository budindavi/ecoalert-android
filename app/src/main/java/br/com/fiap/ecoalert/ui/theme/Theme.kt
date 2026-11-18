package br.com.fiap.ecoalert.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Espaçamentos padrão usados nos Modifiers. */
object Espaco {
    val pequeno = 8.dp
    val medio = 12.dp
    val padrao = 16.dp
}

private val esquemaDeCores = lightColorScheme(
    primary = AzulEspacial,
    onPrimary = Color.White,
    secondary = LaranjaAlerta,
    onSecondary = Color.White,
    background = Fundo,
    surface = Color.White,
    onBackground = TextoPrincipal,
    onSurface = TextoPrincipal,
    onSurfaceVariant = TextoSuave,
)

private val tipografia = Typography(
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontSize = 12.5.sp, color = TextoSuave),
)

@Composable
fun EcoAlertTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = esquemaDeCores,
        typography = tipografia,
        content = content,
    )
}
