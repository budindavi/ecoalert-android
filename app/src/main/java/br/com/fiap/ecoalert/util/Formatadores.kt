package br.com.fiap.ecoalert.util

import java.util.Locale

/** Funções de formatação reaproveitadas pelos componentes. */
object Formatadores {

    /** 12 -> "há 12 min"; 160 -> "há 2 h"; 1500 -> "há 1 d". */
    fun tempoDecorrido(minutos: Int): String = when {
        minutos < 1 -> "agora"
        minutos < 60 -> "há $minutos min"
        minutos < 60 * 24 -> "há ${minutos / 60} h"
        else -> "há ${minutos / (60 * 24)} d"
    }

    /** 12400 -> "12 mil"; 1850 -> "1,9 mil". */
    fun numeroCompacto(valor: Int): String {
        if (valor < 1000) return valor.toString()
        val milhares = valor / 1000.0
        val texto = if (milhares >= 10) {
            String.format(Locale.US, "%.0f", milhares)
        } else {
            String.format(Locale.US, "%.1f", milhares).replace('.', ',')
        }
        return "$texto mil"
    }
}
