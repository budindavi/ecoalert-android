package br.com.fiap.ecoalert.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Flood
import androidx.compose.material.icons.outlined.Landslide
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/** Origem da anomalia captada pelos satélites. */
enum class TipoAnomalia { TERMICA, ATMOSFERICA, HIDROLOGICA }

/**
 * Categorias de risco climático monitoradas pelo EcoAlert.
 *
 * Cada valor carrega rótulo, ícone, cor e o tipo de anomalia detectada.
 * As regras de filtragem usam o próprio enum, nunca strings.
 */
enum class TipoRisco(
    val rotulo: String,
    val icone: ImageVector,
    val cor: Color,
    val anomalia: TipoAnomalia,
) {
    QUEIMADA("Queimada", Icons.Outlined.LocalFireDepartment, Color(0xFFE65100), TipoAnomalia.TERMICA),
    ENCHENTE("Enchente", Icons.Outlined.Flood, Color(0xFF1565C0), TipoAnomalia.HIDROLOGICA),
    DESLIZAMENTO("Deslizamento", Icons.Outlined.Landslide, Color(0xFF6D4C41), TipoAnomalia.HIDROLOGICA),
    TEMPESTADE("Tempestade", Icons.Outlined.Thunderstorm, Color(0xFF5E35B1), TipoAnomalia.ATMOSFERICA),
    ONDA_DE_CALOR("Onda de calor", Icons.Outlined.Thermostat, Color(0xFFD32F2F), TipoAnomalia.TERMICA),
    SECA("Seca", Icons.Outlined.WaterDrop, Color(0xFFF9A825), TipoAnomalia.HIDROLOGICA),
    QUALIDADE_DO_AR("Qualidade do ar", Icons.Outlined.Air, Color(0xFF546E7A), TipoAnomalia.ATMOSFERICA),
}
