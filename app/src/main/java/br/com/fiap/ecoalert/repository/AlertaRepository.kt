package br.com.fiap.ecoalert.repository

import androidx.compose.runtime.mutableStateListOf
import br.com.fiap.ecoalert.R
import br.com.fiap.ecoalert.model.Alerta
import br.com.fiap.ecoalert.model.FonteOrbital
import br.com.fiap.ecoalert.model.StatusAlerta
import br.com.fiap.ecoalert.model.TipoRisco

/**
 * Fonte única dos alertas do aplicativo.
 *
 * Simula, em memória, a saída do motor de triagem do EcoAlert. As telas
 * consultam este repositório e nunca criam nem filtram dados por conta
 * própria. A lista é observável, então qualquer alteração (por exemplo,
 * acionar uma equipe) provoca recomposição nas telas que a utilizam.
 */
class AlertaRepository {

    private val alertas = mutableStateListOf(
        Alerta(
            id = 1,
            titulo = "Movimento de massa em encosta crítica",
            descricao = "O radar do Sentinel-1 identificou deslocamento do solo de 4 cm em 48 h, " +
                "somado a 180 mm de chuva acumulada. A encosta está cadastrada como área de " +
                "risco alto, com moradias na base.",
            imagem = R.drawable.img_deslizamento,
            municipio = "Petrópolis - RJ",
            areaVulneravel = "Encosta do Morro da Oficina",
            fonte = FonteOrbital.SENTINEL_1,
            minutosDesdeDeteccao = 12,
            criticidade = 10,
            pessoasEmRisco = 1850,
            tipos = listOf(TipoRisco.DESLIZAMENTO, TipoRisco.TEMPESTADE),
            status = StatusAlerta.EM_TRIAGEM,
            destaque = true,
        ),
        Alerta(
            id = 2,
            titulo = "Transbordamento iminente do Rio Taquari",
            descricao = "A leitura hidrológica indica alta de 2,3 m em 6 h no nível do rio. " +
                "O modelo projeta que a água chega às ruas da várzea em cerca de 5 horas.",
            imagem = R.drawable.img_alagamento,
            municipio = "Lajeado - RS",
            areaVulneravel = "Várzea do Bairro Centenário",
            fonte = FonteOrbital.SENTINEL_2,
            minutosDesdeDeteccao = 25,
            criticidade = 9,
            pessoasEmRisco = 12400,
            tipos = listOf(TipoRisco.ENCHENTE),
            status = StatusAlerta.DETECTADO,
            destaque = true,
        ),
        Alerta(
            id = 3,
            titulo = "Foco de queimada avançando sobre mata seca",
            descricao = "Anomalia térmica com 14 focos ativos em uma frente de 6 km. O vento leva " +
                "o fogo na direção de uma comunidade ribeirinha.",
            imagem = R.drawable.img_queimada,
            municipio = "Altamira - PA",
            areaVulneravel = "Mata seca da Volta Grande do Xingu",
            fonte = FonteOrbital.AQUA_MODIS,
            minutosDesdeDeteccao = 40,
            criticidade = 9,
            pessoasEmRisco = 3200,
            tipos = listOf(TipoRisco.QUEIMADA, TipoRisco.QUALIDADE_DO_AR),
            status = StatusAlerta.EQUIPE_ACIONADA,
            destaque = true,
        ),
        Alerta(
            id = 4,
            titulo = "Pluma de fumaça densa sobre área urbana",
            descricao = "Os dados atmosféricos mostram fumaça de incêndios no Pantanal cobrindo a " +
                "cidade. A projeção indica qualidade do ar péssima nas próximas 24 h.",
            imagem = R.drawable.img_fumaca,
            municipio = "Corumbá - MS",
            areaVulneravel = "Perímetro urbano e escolas",
            fonte = FonteOrbital.GOES_16,
            minutosDesdeDeteccao = 75,
            criticidade = 8,
            pessoasEmRisco = 9800,
            tipos = listOf(TipoRisco.QUALIDADE_DO_AR, TipoRisco.QUEIMADA),
            status = StatusAlerta.EM_TRIAGEM,
            destaque = true,
        ),
        Alerta(
            id = 5,
            titulo = "Célula de tempestade severa em formação",
            descricao = "O satélite geoestacionário mostra nuvens com topo a -70 °C e crescimento " +
                "rápido. Há risco de granizo, ventos acima de 90 km/h e alagamentos repentinos.",
            imagem = R.drawable.img_tempestade,
            municipio = "Curitiba - PR",
            areaVulneravel = "Bacia do Rio Belém",
            fonte = FonteOrbital.GOES_16,
            minutosDesdeDeteccao = 18,
            criticidade = 7,
            pessoasEmRisco = 5400,
            tipos = listOf(TipoRisco.TEMPESTADE, TipoRisco.ENCHENTE),
            status = StatusAlerta.DETECTADO,
        ),
        Alerta(
            id = 6,
            titulo = "Ilha de calor extremo no centro urbano",
            descricao = "O sensor térmico registrou superfície acima de 52 °C em bairros de pouca " +
                "arborização. Idosos e trabalhadores ao ar livre correm mais risco.",
            imagem = R.drawable.img_calor,
            municipio = "Cuiabá - MT",
            areaVulneravel = "Bairros Centro-Sul e Porto",
            fonte = FonteOrbital.LANDSAT_9,
            minutosDesdeDeteccao = 160,
            criticidade = 6,
            pessoasEmRisco = 48000,
            tipos = listOf(TipoRisco.ONDA_DE_CALOR),
            status = StatusAlerta.EM_TRIAGEM,
        ),
        Alerta(
            id = 7,
            titulo = "Seca severa isola comunidades ribeirinhas",
            descricao = "As imagens mostram o leito do rio 70% abaixo da média. Barcos de " +
                "abastecimento não conseguem chegar às comunidades.",
            imagem = R.drawable.img_seca,
            municipio = "Tefé - AM",
            areaVulneravel = "Lago de Tefé e comunidades do entorno",
            fonte = FonteOrbital.AMAZONIA_1,
            minutosDesdeDeteccao = 300,
            criticidade = 7,
            pessoasEmRisco = 2600,
            tipos = listOf(TipoRisco.SECA),
            status = StatusAlerta.EQUIPE_ACIONADA,
        ),
        Alerta(
            id = 8,
            titulo = "Cicatrizes de deslizamento após chuva",
            descricao = "A comparação entre imagens de antes e depois mostrou 3 novas cicatrizes " +
                "de deslizamento perto de áreas habitadas. A vistoria em campo já foi agendada.",
            imagem = R.drawable.img_deslizamento_satelite,
            municipio = "Petrópolis - RJ",
            areaVulneravel = "Encostas do Alto da Serra",
            fonte = FonteOrbital.SENTINEL_2,
            minutosDesdeDeteccao = 420,
            criticidade = 6,
            pessoasEmRisco = 450,
            tipos = listOf(TipoRisco.DESLIZAMENTO),
            status = StatusAlerta.EQUIPE_ACIONADA,
        ),
        Alerta(
            id = 9,
            titulo = "Anomalia térmica em zona de preservação",
            descricao = "Foco isolado detectado no parque nacional. A brigada confirmou que o " +
                "incêndio foi controlado e a área segue em monitoramento.",
            imagem = R.drawable.img_termica,
            municipio = "Alto Paraíso de Goiás - GO",
            areaVulneravel = "Parque Nacional da Chapada dos Veadeiros",
            fonte = FonteOrbital.CBERS_4A,
            minutosDesdeDeteccao = 900,
            criticidade = 5,
            pessoasEmRisco = 300,
            tipos = listOf(TipoRisco.QUEIMADA),
            status = StatusAlerta.CONTROLADO,
        ),
        Alerta(
            id = 10,
            titulo = "Queda de árvores após vendaval",
            descricao = "Uma rajada de vento de 85 km/h derrubou árvores e afetou a rede elétrica. " +
                "As vias foram liberadas pelas equipes de campo.",
            imagem = R.drawable.img_arvore,
            municipio = "São Paulo - SP",
            areaVulneravel = "Zona Sul - Santo Amaro",
            fonte = FonteOrbital.GOES_16,
            minutosDesdeDeteccao = 1300,
            criticidade = 4,
            pessoasEmRisco = 700,
            tipos = listOf(TipoRisco.TEMPESTADE),
            status = StatusAlerta.CONTROLADO,
        ),
    )

    /** Todos os alertas, do mais crítico para o menos crítico. */
    fun obterTodos(): List<Alerta> = alertas.sortedByDescending { it.criticidade }

    /** Alertas de um tipo de risco. `null` representa a opção "Todos". */
    fun obterPorTipo(tipo: TipoRisco?): List<Alerta> =
        if (tipo == null) obterTodos() else obterTodos().filter { it.envolve(tipo) }

    /** Alertas marcados como destaque (críticos). */
    fun obterCriticos(): List<Alerta> = obterTodos().filter { it.destaque }

    fun obterPorId(id: Int): Alerta? = alertas.firstOrNull { it.id == id }

    val totalAtivos: Int get() = alertas.count { it.status.ativo }

    val totalCriticos: Int get() = alertas.count { it.destaque }

    val pessoasEmRisco: Int get() = alertas.filter { it.status.ativo }.sumOf { it.pessoasEmRisco }

    val totalSatelites: Int get() = alertas.map { it.fonte }.toSet().size

    /** Ação imediata: aciona a equipe de campo para o alerta. */
    fun acionarEquipe(id: Int) {
        val indice = alertas.indexOfFirst { it.id == id }
        if (indice >= 0 && alertas[indice].status.podeAcionarEquipe) {
            alertas[indice] = alertas[indice].copy(status = StatusAlerta.EQUIPE_ACIONADA)
        }
    }
}
