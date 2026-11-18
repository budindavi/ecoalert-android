package br.com.fiap.ecoalert

import br.com.fiap.ecoalert.model.StatusAlerta
import br.com.fiap.ecoalert.model.TipoRisco
import br.com.fiap.ecoalert.repository.AlertaRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AlertaRepositoryTest {

    private val repositorio = AlertaRepository()

    @Test
    fun `possui ao menos 5 alertas ordenados por criticidade`() {
        val alertas = repositorio.obterTodos()
        assertTrue(alertas.size >= 5)
        assertEquals(alertas.sortedByDescending { it.criticidade }, alertas)
    }

    @Test
    fun `alertas criticos vem do atributo destaque`() {
        val criticos = repositorio.obterCriticos()
        assertTrue(criticos.size >= 3)
        assertTrue(criticos.all { it.destaque })
    }

    @Test
    fun `filtra por tipo de risco usando o enum`() {
        TipoRisco.entries.forEach { tipo ->
            val filtrados = repositorio.obterPorTipo(tipo)
            assertTrue("Sem alertas de $tipo", filtrados.isNotEmpty())
            assertTrue(filtrados.all { tipo in it.tipos })
        }
        assertEquals(repositorio.obterTodos().size, repositorio.obterPorTipo(null).size)
    }

    @Test
    fun `um alerta pode ter mais de um tipo de risco`() {
        assertTrue(repositorio.obterTodos().any { it.tipos.size > 1 })
    }

    @Test
    fun `acionar equipe muda o status do alerta`() {
        val alerta = repositorio.obterTodos().first { it.status == StatusAlerta.DETECTADO }
        repositorio.acionarEquipe(alerta.id)
        assertEquals(StatusAlerta.EQUIPE_ACIONADA, repositorio.obterPorId(alerta.id)?.status)
    }
}
