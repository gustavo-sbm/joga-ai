package com.jogaai.ui.jogo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jogaai.domain.model.Categoria
import com.jogaai.theme.Spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriaFilterRow(
    categorias: List<Categoria>,
    selecionada: Categoria?,
    onSelecionar: (Categoria) -> Unit,
    modifier: Modifier = Modifier,
    ) {
    FlowRow(
        modifier = modifier.padding(horizontal = Spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        verticalArrangement = Arrangement.spacedBy(Spacing.xs),
    ) {
        categorias.forEach {
            categoria ->
            FilterChip(
                selected = categoria == selecionada,
                onClick = { onSelecionar(categoria) },
                label = { Text(categoria.nome) },
                shape = RoundedCornerShape(50),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    selectedContainerColor =  MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }

}