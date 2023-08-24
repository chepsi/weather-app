package chepsi.weather.presentation.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import chepsi.weather.presentation.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val ralewayFont = GoogleFont("Raleway")

val ralewayFontFamily = FontFamily(
    Font(googleFont = ralewayFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = ralewayFont, fontProvider = provider, weight = FontWeight.SemiBold)
)
