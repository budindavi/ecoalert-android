package br.com.fiap.ecoalert.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

/**
 * Alerta gerado a partir de uma anomalia captada por satélite e cruzada com o
 * cadastro de áreas vulneráveis.
 */
data class Alerta(
    val id: Int,
    val titulo: String,
    val descricao: String,
    @DrawableRes val imagem: Int,
    val municipio: String,
    /** Área do cadastro geográfico afetada (encosta, mata seca, várzea...). */
    val areaVulneravel: String,
    val fonte: FonteOrbital,
    /** Minutos desde a detecção pelo satélite. */
    val minutosDesdeDeteccao: Int,
    /** Nota de criticidade atribuída pelo motor de triagem (1 a 10). */
    val criticidade: Int,
    val pessoasEmRisco: Int,
    /** Um alerta pode envolver mais de um tipo de risco. */
    val tipos: List<TipoRisco>,
    val status: StatusAlerta,
    /** Alertas críticos exibidos na seção de destaques. */
    val destaque: Boolean = false,
) {
    init {
        require(tipos.isNotEmpty()) { "O alerta precisa de ao menos um tipo de risco." }
        require(criticidade in 1..10) { "A criticidade vai de 1 a 10." }
    }

    val tipoPrincipal: TipoRisco get() = tipos.first()

    fun envolve(tipo: TipoRisco): Boolean = tipo in tipos

    val nivel: String
        get() = when {
            criticidade >= 9 -> "Extremo"
            criticidade >= 7 -> "Alto"
            criticidade >= 4 -> "Moderado"
            else -> "Baixo"
        }

    val corCriticidade: Color
        get() = when {
            criticidade >= 9 -> Color(0xFFB71C1C)
            criticidade >= 7 -> Color(0xFFE65100)
            criticidade >= 4 -> Color(0xFFF9A825)
            else -> Color(0xFF2E7D32)
        }
}
