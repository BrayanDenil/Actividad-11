package com.example.retrofit.viewModel

import com.example.retrofit.Model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val items: List<Product> = emptyList(),
    val message: String? = null





)