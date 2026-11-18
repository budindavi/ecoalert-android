package br.com.fiap.ecoalert.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoalert.R
import br.com.fiap.ecoalert.ui.components.FluxoInteligencia
import br.com.fiap.ecoalert.ui.theme.AzulEspacial
import br.com.fiap.ecoalert.ui.theme.Espaco

/** Apresenta a proposta do EcoAlert, o fluxo de inteligência e os ODS. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(onVoltar: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sobre o EcoAlert") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulEspacial,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno)
                .verticalScroll(rememberScrollState())
                .padding(Espaco.padrao),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_satelite),
                contentDescription = "Satélite de observação da Terra em órbita",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(modifier = Modifier.height(Espaco.padrao))
            Text(text = "Tecnologia espacial a serviço da vida", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(Espaco.pequeno))
            Text(
                text = "O EcoAlert conecta dados de satélites de agências globais à Defesa Civil e " +
                    "às equipes de campo. A plataforma reduz o tempo de resposta a eventos " +
                    "climáticos extremos em áreas urbanas vulneráveis e em zonas de preservação.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Fluxo de inteligência", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Espaco.medio))
            FluxoInteligencia()
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Objetivos de Desenvolvimento Sustentável", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Espaco.pequeno))
            OdsItem(
                numero = 11,
                titulo = "Cidades e comunidades sustentáveis",
                descricao = "Cidades mais seguras e preparadas contra desastres.",
                cor = Color(0xFFF99D26),
            )
            Spacer(modifier = Modifier.height(Espaco.pequeno))
            OdsItem(
                numero = 13,
                titulo = "Ação contra a mudança global do clima",
                descricao = "Resposta rápida a riscos climáticos com inteligência de dados.",
                cor = Color(0xFF3F7E44),
            )
        }
    }
}

@Composable
private fun OdsItem(numero: Int, titulo: String, descricao: String, cor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(modifier = Modifier.padding(Espaco.medio), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(cor, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "ODS\n$numero",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp,
                )
            }
            Spacer(modifier = Modifier.width(Espaco.medio))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = titulo, fontWeight = FontWeight.Bold)
                Text(text = descricao, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
