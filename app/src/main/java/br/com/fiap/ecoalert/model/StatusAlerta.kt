package br.com.fiap.ecoalert.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.SatelliteAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/** Etapas do fluxo de inteligência: captação, triagem e ação imediata. */
enum class StatusAlerta(
    val rotulo: String,
    val icone: ImageVector,
    val cor: Color,
) {
    DETECTADO("Detectado", Icons.Outlined.SatelliteAlt, Color(0xFFD32F2F)),
    EM_TRIAGEM("Em triagem", Icons.Outlined.Search, Color(0xFFEF6C00)),
    EQUIPE_ACIONADA("Equipe acionada", Icons.Outlined.Campaign, Color(0xFF1565C0)),
    CONTROLADO("Controlado", Icons.Outlined.Verified, Color(0xFF2E7D32));

    val ativo: Boolean get() = this != CONTROLADO

    val podeAcionarEquipe: Boolean get() = this == DETECTADO || this == EM_TRIAGEM
}
