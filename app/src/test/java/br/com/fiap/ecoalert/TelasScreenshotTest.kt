package br.com.fiap.ecoalert

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import br.com.fiap.ecoalert.model.TipoRisco
import br.com.fiap.ecoalert.repository.AlertaRepository
import br.com.fiap.ecoalert.ui.screens.DetalheAlertaScreen
import br.com.fiap.ecoalert.ui.screens.HomeScreen
import br.com.fiap.ecoalert.ui.screens.SobreScreen
import br.com.fiap.ecoalert.ui.theme.EcoAlertTheme
import org.junit.Rule
import org.junit.Test

/**
 * Renderiza as telas em imagens PNG (prints usados no README), sem precisar
 * de emulador. Gerar com: gradlew recordPaparazziDebug
 */
class TelasScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.Light.NoActionBar",
    )

    private val repositorio = AlertaRepository()

    @Test
    fun home() {
        paparazzi.snapshot {
            EcoAlertTheme {
                HomeScreen(
                    repositorio = repositorio,
                    tipoSelecionado = null,
                    onSelecionarTipo = {},
                    onAbrirAlerta = {},
                    onAbrirSobre = {},
                )
            }
        }
    }

    @Test
    fun filtroQueimada() {
        paparazzi.unsafeUpdateConfig(deviceConfig = DeviceConfig.PIXEL_5.copy(screenHeight = 3600))
        paparazzi.snapshot {
            EcoAlertTheme {
                HomeScreen(
                    repositorio = repositorio,
                    tipoSelecionado = TipoRisco.QUEIMADA,
                    onSelecionarTipo = {},
                    onAbrirAlerta = {},
                    onAbrirSobre = {},
                )
            }
        }
    }

    @Test
    fun detalhe() {
        val alerta = repositorio.obterCriticos().first()
        paparazzi.snapshot {
            EcoAlertTheme {
                DetalheAlertaScreen(alerta = alerta, onVoltar = {}, onAcionarEquipe = {})
            }
        }
    }

    @Test
    fun sobre() {
        paparazzi.snapshot {
            EcoAlertTheme { SobreScreen(onVoltar = {}) }
        }
    }
}
