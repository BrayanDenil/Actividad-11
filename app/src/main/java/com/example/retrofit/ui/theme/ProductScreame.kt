package com.example.retrofit.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofit.viewModel.ProductViewModel

@Composable
fun ProductsScreen(vm: ProductViewModel = viewModel()) {

    val state by vm.uiState.collectAsStateWithLifecycle()
    Column(Modifier.padding(16.dp)){

}



}

