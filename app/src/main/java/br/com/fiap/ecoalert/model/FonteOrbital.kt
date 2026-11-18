package br.com.fiap.ecoalert.model

/** Satélites que alimentam a plataforma com dados orbitais. */
enum class FonteOrbital(val nome: String, val agencia: String) {
    AMAZONIA_1("Amazonia-1", "INPE"),
    CBERS_4A("CBERS-4A", "INPE / CAST"),
    SENTINEL_1("Sentinel-1", "ESA / Copernicus"),
    SENTINEL_2("Sentinel-2", "ESA / Copernicus"),
    GOES_16("GOES-16", "NOAA"),
    AQUA_MODIS("Aqua / MODIS", "NASA"),
    LANDSAT_9("Landsat 9", "NASA / USGS"),
}
