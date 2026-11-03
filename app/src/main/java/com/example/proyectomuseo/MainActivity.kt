package com.example.proyectomuseo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectomuseo.ui.theme.AppBackground
import com.example.proyectomuseo.ui.theme.AppSecondary
import com.example.proyectomuseo.ui.theme.AppSurface
import com.example.proyectomuseo.ui.theme.AppTheme
import com.example.proyectomuseo.ui.theme.ProyectoMuseoTheme
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.Image


sealed interface AppRoute{
    data object Inicio: AppRoute
    data object Obras: AppRoute
    data object Mas: AppRoute
    data object Perfil: AppRoute
}

data class BottomNavItem(
    val route: AppRoute,
    val labelRes: Int,
    val icon: ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(com.example.proyectomuseo.AppRoute.Inicio, R.string.bottom_nav_home, Icons.Filled.Home),
    BottomNavItem(com.example.proyectomuseo.AppRoute.Obras, R.string.bottom_nav_image, Icons.Outlined.Image),
    BottomNavItem(com.example.proyectomuseo.AppRoute.Mas,R.string.bottom_nav_more, Icons.Outlined.Brush),
    BottomNavItem(com.example.proyectomuseo.AppRoute.Perfil, R.string.bottom_nav_person, Icons.Outlined.Person)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoMuseoTheme {
                MainScreen(
                    onArtworkClick = {
                        startActivity(Intent(this, ArtistaActivity::class.java))
                    },
                    onNavigate = { route ->
                        when (route) {
                            AppRoute.Inicio -> { }
                            AppRoute.Obras -> {startActivity(Intent(this, MasObrasActivity::class.java )) }
                            AppRoute.Mas -> {startActivity(Intent(this, ArtistaActivity::class.java ))}
                            AppRoute.Perfil -> { }
                        }
                    }
                )
            }//ProyMuseo
        }
    }
}

// ============================================================
// MODELOS
// ============================================================

data class ExhibitionCategory(
    @StringRes val label: Int,
    val isSelected: Boolean = false
)

data class FeaturedExhibition(
    @DrawableRes val image: Int,
    @StringRes val artist: Int,
    @StringRes val type: Int,
    @StringRes val museum: Int,
    val closingDays: Int,
    val views: Int,
    val likes: Int
)

data class TicketExhibition(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val dateRange: Int,
    @StringRes val location: Int,
    val rating: Float,
    val reviewCount: Int,
    @StringRes val price: Int
)

// ============================================================
// PANTALLA PRINCIPAL (envuelve ExhibitionScreen + barra inferior)
// ============================================================
@Composable
fun MainScreen(
    onArtworkClick: () -> Unit = {},
    onNavigate: (AppRoute) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            DashboardBottomNavigation(
                selectedRoute = AppRoute.Inicio, // esta pantalla siempre es Inicio
                onNavigate = onNavigate
            )
        }
    ) { innerPadding ->
        ExhibitionScreen(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            onArtworkClick = onArtworkClick
        )
    }
}

// ============================================================
// TOP BAR
// ============================================================
// ============================================================
// BOTTOM BAR
// ============================================================
// ============================================================
// BOTTOM BAR
// ============================================================
@Composable
fun DashboardBottomNavigation(
    modifier: Modifier = Modifier,
    selectedRoute: AppRoute = AppRoute.Inicio,
    onNavigate: (AppRoute) -> Unit
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        // Fondo semitransparente, sin línea divisoria arriba
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f),
        tonalElevation = AppTheme.spacing.none
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = item.route == selectedRoute

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(item.labelRes),
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1
                        )
                        // Rayita bajo el ítem seleccionado (estilo "icon underline")
                        if (isSelected) {
                            Spacer(Modifier.height(2.dp))
                            Box(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(2.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        shape = CircleShape
                                    )
                            )
                        }
                    }
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    // Seleccionado: oscuro; sin fondo detrás del ícono
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = Color.Transparent,
                    // No seleccionado: gris suave
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
@Composable
fun ExhibitionTopBar(
    city: String,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.regular, vertical = AppTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = AppSecondary,
                modifier = Modifier.size(AppTheme.sizes.iconSmall)
            )
            Spacer(Modifier.width(AppTheme.spacing.extraSmall))
            Text(
                text = city,
                style = MaterialTheme.typography.bodySmall,
                color = AppSecondary
            )
        }
    }
}

// ============================================================
// TABS VERTICALES (Hot, Nearby, Thematic, Solo exhibition)
// ============================================================

@Composable
fun VerticalCategoryTabs(
    categories: List<ExhibitionCategory>,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = AppTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.extraLarge)
    ) {
        categories.forEachIndexed { index, category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onCategoryClick(index) }
            ) {
                Text(
                    text = stringResource(category.label),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = if (category.isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (category.isSelected)
                        MaterialTheme.colorScheme.onSurface
                    else
                        AppSecondary,
                    modifier = Modifier.graphicsLayer { rotationZ = -90f }
                )
                if (category.isSelected) {
                    Spacer(Modifier.height(AppTheme.spacing.extraSmall))
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
        }
    }
}

// ============================================================
// TARJETA DE EXHIBICIÓN DESTACADA
// ============================================================

@Composable
fun FeaturedExhibitionCard(
    exhibition: FeaturedExhibition,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(exhibition.artist),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )

        Spacer(Modifier.height(AppTheme.spacing.extraSmall))

        Column(
            modifier = Modifier
                .width(300.dp)
                .background(AppSurface)

        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .clickable { onClick() }
            ) {
                Image(
                    painter = painterResource(exhibition.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = stringResource(exhibition.type),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier.padding(
                    horizontal = AppTheme.spacing.small,
                    vertical = AppTheme.spacing.regular
                )
            ) {
                Text(
                    text = stringResource(exhibition.museum),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.closing_after_days, exhibition.closingDays),
                    style = MaterialTheme.typography.bodySmall,
                    color = AppSecondary
                )
                Spacer(Modifier.height(AppTheme.spacing.extraSmall))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                        tint = AppSecondary,
                        modifier = Modifier.size(AppTheme.sizes.iconMedium)
                    )
                    Spacer(Modifier.width(AppTheme.spacing.micro))
                    Text(
                        text = "${exhibition.views}",
                        style = MaterialTheme.typography.labelSmall,
                        color = AppSecondary
                    )
                    Spacer(Modifier.width(AppTheme.spacing.small))
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = AppSecondary,
                        modifier = Modifier.size(AppTheme.sizes.iconMedium)
                    )
                    Spacer(Modifier.width(AppTheme.spacing.micro))
                    Text(
                        text = "${exhibition.likes}",
                        style = MaterialTheme.typography.labelSmall,
                        color = AppSecondary
                    )
                }
            }
        }
    }
}

// ============================================================
// TARJETA "BUY TICKETS"
// ============================================================

@Composable
fun TicketCard(
    exhibition: TicketExhibition,
    onBuyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.spacing.micro)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(exhibition.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(130.dp)
            )

            Spacer(Modifier.width(AppTheme.spacing.medium))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(exhibition.title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Text(
                    text = stringResource(exhibition.dateRange),
                    style = MaterialTheme.typography.labelSmall,
                    color = AppSecondary
                )
                Text(
                    text = stringResource(exhibition.location),
                    style = MaterialTheme.typography.labelSmall,
                    color = AppSecondary
                )
                Spacer(Modifier.height(AppTheme.spacing.micro))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(AppTheme.sizes.iconTiny)
                    )
                    Spacer(Modifier.width(AppTheme.spacing.micro))
                    Text(
                        text = "${exhibition.rating} (${exhibition.reviewCount})",
                        style = MaterialTheme.typography.labelSmall,
                        color = AppSecondary
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(exhibition.price),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Button(
                        onClick = onBuyClick,
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSurface
                        ),
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.spacing.regular,
                            vertical = AppTheme.spacing.extraSmall
                        )
                    )  {
                        Text(
                            text = stringResource(R.string.buy_tickets),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
// ============================================================
// PANTALLA COMPLETA
// ============================================================

@Composable
fun ExhibitionScreen(
    modifier: Modifier = Modifier,
    onArtworkClick: () -> Unit = {}
) {
    val categories = remember {
        listOf(
            ExhibitionCategory(R.string.tab_hot, isSelected = true),
            ExhibitionCategory(R.string.tab_nearby),
            ExhibitionCategory(R.string.tab_thematic),
            ExhibitionCategory(R.string.tab_solo_exhibition)
        )
    }

    val featuredExhibitions = remember {
        listOf(
            FeaturedExhibition(
                image = R.drawable.img1,
                artist = R.string.artist_van_gogh,
                type = R.string.exhibition_type_solo,
                museum = R.string.museum_national_china,
                closingDays = 18,
                views = 2213,
                likes = 85
            ),
            FeaturedExhibition(
                image = R.drawable.img7,
                artist = R.string.artist_frida_kahlo,
                type = R.string.exhibition_type_solo,
                museum = R.string.museum_louvre_paris,
                closingDays = 12,
                views = 1520,
                likes = 64
            )
        )
    }

    val ticketExhibitions = remember {
        listOf(
            TicketExhibition(
                image = R.drawable.img5,
                title = R.string.ticket_davinci_title,
                dateRange = R.string.ticket_davinci_date,
                location = R.string.museum_louvre_paris,
                rating = 4.5f,
                reviewCount = 2312,
                price = R.string.ticket_davinci_price
            ),
            TicketExhibition(
                image = R.drawable.img6,
                title = R.string.ticket_monet_title,
                dateRange = R.string.ticket_monet_date,
                location = R.string.ticket_monet_location,
                rating = 4.7f,
                reviewCount = 1890,
                price = R.string.ticket_monet_price
            )
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppBackground)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())   // <-- AGREGAR
    )  {
        ExhibitionTopBar(city = stringResource(R.string.city_paris), onMenuClick = { })

        Text(
            text = stringResource(R.string.exhibition_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = AppTheme.spacing.regular)
        )

        Spacer(Modifier.height(AppTheme.spacing.small))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            VerticalCategoryTabs(
                categories = categories,
                onCategoryClick = { },
                modifier = Modifier.width(40.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                contentPadding = PaddingValues(end = AppTheme.spacing.regular)
            ) {
                items(featuredExhibitions) { exhibition ->
                    FeaturedExhibitionCard(
                        exhibition = exhibition,
                        onClick = onArtworkClick
                    )
                }
            }
        }

        Spacer(Modifier.height(AppTheme.spacing.extraLarge))

        Text(
            text = stringResource(R.string.buy_tickets),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = AppTheme.spacing.regular)
        )

        Spacer(Modifier.height(AppTheme.spacing.small))

        Column(
            modifier = Modifier
                .padding(horizontal = AppTheme.spacing.regular, vertical = AppTheme.spacing.extraSmall),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
        ) {
            ticketExhibitions.forEach { exhibition ->
                TicketCard(exhibition = exhibition, onBuyClick = { })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExhibitionScreenPreview() {
    ProyectoMuseoTheme {
        ExhibitionScreen()
    }
}