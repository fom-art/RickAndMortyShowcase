package com.fomart.rms.shared.feature.catalog.all.root.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fomart.rms.shared.feature.catalog.all.browse.presentation.BrowseViewModel
import com.fomart.rms.shared.feature.catalog.all.search.presentation.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CatalogRoute(
    modifier: Modifier = Modifier,
    browseViewModel: BrowseViewModel = koinViewModel(),
    searchViewModel: SearchViewModel = koinViewModel()
) {

}