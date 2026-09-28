package com.jogaai.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jogaai.theme.Spacing
import com.jogaai.ui.navigation.Section


@Composable
fun AppScaffold(
    selectedSection: Section,
    onSectionSelected: (Section)->Unit,
    nomeUsuario: String,
    papelUsuario: String,
    onLogout: () -> Unit,
    content: @Composable () -> Unit
) {

    BoxWithConstraints {
        val sizeClass = WindowSizeClass.fromWidth(maxWidth.value.toInt())

        when (sizeClass) {
            WindowSizeClass.COMPACT -> {
                Column {
                    Box(modifier = Modifier.weight(1f)){
                        content()
                    }
                    NavigationBar {
                        Section.principais.forEach {
                                section ->
                            NavigationBarItem(
                                selected = section == selectedSection,
                                onClick = {onSectionSelected(section)},
                                icon = { Icon(section.icon, contentDescription = section.label)},
                                label = { Text(section.label )}
                            )
                        }
                }
            } }
            WindowSizeClass.MEDIUM -> {
                Row {
                    NavigationRail(modifier = Modifier.width(96.dp)) {
                        Box(
                            modifier = Modifier.padding(vertical = Spacing.md)
                                .size(34.dp)
                                .clip(RoundedCornerShape(20))
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("J", color = MaterialTheme.colorScheme.onPrimary)
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Section.entries.forEach { section ->
                                NavigationRailItem(
                                    selected = section == selectedSection,
                                    onClick = { onSectionSelected(section) },
                                    icon = { Icon(section.icon, contentDescription = section.label) },
                                    label = { Text(section.label) }
                                )
                            }
                        }
                        NavigationRailItem(
                            selected = false,
                            onClick = onLogout,
                            icon = { Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null) },
                            label = { Text("Sair")},
                            modifier = Modifier.padding(bottom = Spacing.md)
                        )

                    }
                    Box(modifier = Modifier.weight(1f)){
                        content()
                    }

                }
            }
            WindowSizeClass.EXPANDED -> {
                PermanentNavigationDrawer(
                    drawerContent = {

                        PermanentDrawerSheet(
                            modifier = Modifier.width(240.dp)) {
                            Row(
                                modifier = Modifier.padding(horizontal = Spacing.lg, vertical = Spacing.lg),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
                            ){
                                Box(
                                    modifier = Modifier.size(34.dp)
                                        .clip(RoundedCornerShape(20))
                                        .background(MaterialTheme.colorScheme.primary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("J", color = MaterialTheme.colorScheme.onPrimary)
                                }
                                Column {
                                    Text("JogaAí", style = MaterialTheme.typography.titleMedium)
                                    Text("Acervo compartilhado", style = MaterialTheme.typography.labelSmall)
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f)
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.spacedBy(Spacing.xs),
                            ) {
                                Section.entries.forEach { section ->
                                    NavigationDrawerItem(
                                        selected = section == selectedSection,
                                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                        onClick = { onSectionSelected(section) },
                                        icon = { Icon(section.icon, contentDescription = section.label) },
                                        label = { Text(section.label) }
                                    )
                                }
                            }
                            HorizontalDivider()

                            AvatarAndDetails(nome = nomeUsuario,
                                detalhe = papelUsuario,
                                modifier = Modifier.padding(Spacing.md))

                            NavigationDrawerItem(
                                selected = false,
                                onClick = onLogout,
                                icon = { Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null) },
                                label = { Text("Sair") },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }

                ) {
                    content()
                }
            }
        }

    }
}