package com.example.proyectomuseo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectomuseo.ui.theme.AppTheme
import com.example.proyectomuseo.ui.theme.ProyectoMuseoTheme

class ArtistaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoMuseoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtistaScreen(
                        onBackClick = { finish() },
                        onObraClick = {
                            startActivity(Intent(this, ObraActivity::class.java))
                        },
                        onVerMasObrasClick = {
                            startActivity(Intent(this, MasObrasActivity::class.java))
                        }
                    )
                }
            } //ProyectoMuseoTheme
        }
    }
}

@Composable
fun ArtistaScreen(
    onBackClick: () -> Unit,
    onObraClick: () -> Unit,
    onVerMasObrasClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // ------------------------------------------------------
        // Imagen de fondo ocupando TODA la pantalla
        // ------------------------------------------------------
        Image(
            painterResource(R.drawable.img1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // ------------------------------------------------------
        // Degradado superpuesto (scrim): claro arriba, oscuro abajo
        // ------------------------------------------------------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.75f)
                        )
                    )
                )
        )

        // ------------------------------------------------------
        // Íconos superiores (back / share)
        // ------------------------------------------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = AppTheme.spacing.regular, vertical = AppTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.cd_back),
                    tint = Color.White,
                    modifier = Modifier.size(AppTheme.sizes.iconMedium)
                )
            }
            IconButton(onClick = { /* compartir */ }) {
                Icon(
                    imageVector = Icons.Outlined.IosShare,
                    contentDescription = stringResource(R.string.cd_share),
                    tint = Color.White,
                    modifier = Modifier.size(AppTheme.sizes.iconMedium)
                )
            }
        }


        // ------------------------------------------------------
        // TÍTULO: separado, más arriba
        // ------------------------------------------------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(horizontal = AppTheme.spacing.large, vertical = AppTheme.spacing.extraLarge)
        ) {
            Text(
                text = stringResource(R.string.artist_van_gogh),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.exhibition_type_solo),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
        }

        // ------------------------------------------------------
        // RESTO DEL CONTENIDO: museo, fechas, fare, works (abajo)
        // ------------------------------------------------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .navigationBarsPadding()
                .padding(horizontal = AppTheme.spacing.large)
        ){
            Text(
                text = stringResource(R.string.museum_national_china),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))

            Text(
                text = stringResource(R.string.exhibition_time_label),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.small))

            Text(
                text = stringResource(R.string.van_gogh_exhibition_date),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.small))

            Text(
                text = stringResource(R.string.fare_label),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White.copy(alpha = 0.85f)
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.extraSmall))

            Text(
                text = stringResource(R.string.fare_student),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.extraSmall))
            Text(
                text = stringResource(R.string.fare_adult),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.85f)
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.large))

            Text(
                text = stringResource(R.string.exhibition_works_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            // Carrusel de obras
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
            ) {
                items(listOf(R.drawable.img4, R.drawable.img2, R.drawable.img3)) { imageRes ->
                    ObraThumbnail(
                        imageRes = imageRes,
                        onClick = onObraClick
                    )
                }
                item {
                    VerMasObrasCard(
                        onClick = onVerMasObrasClick
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))
        } //Column contenido
    } //Box raíz
}

@Composable
fun ObraThumbnail(
    imageRes: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 180.dp)
    ) {
        Image(
            painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(250.dp)
        )
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(AppTheme.sizes.iconContainerMedium)
                .background(Color.Black)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = stringResource(R.string.cd_artwork_next),
                tint = Color.White,
                modifier = Modifier.size(AppTheme.sizes.iconLarge)
            )
        }
    }
}

@Composable
fun VerMasObrasCard(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 180.dp)
            .background(Color.White.copy(alpha = 0.15f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(AppTheme.sizes.iconContainerMedium)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(AppTheme.sizes.iconLarge)
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.spacing.small))
            Text(
                text = stringResource(R.string.ver_mas_obras),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtistaScreenPreview() {
    ProyectoMuseoTheme {
        ArtistaScreen(
            onBackClick = {},
            onObraClick = {},
            onVerMasObrasClick = {}
        )
    }
}