package com.example.retrofit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.Model.Product
import com.example.retrofit.Model.ProductRequest
import com.example.retrofit.Reposity.ProductReposity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductReposity = ProductReposity()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asSharedFlow()

    fun loadAll() {
        viewModelScope.launch{
            runCatching {
                _uiState.value = _uiState.value.copy(loading = true)
                repository.listAll()
            }.onSuccess { list ->
                _uiState.value = _uiState.value.copy(items = list, loading = false)
            }.onFailure {
                _uiState.value = _uiState.value.copy(message = it.message, loading = false)
            }
        }
    }

    fun searchByName(name: String) { /* similar */ }
    fun create(p: ProductRequest) { /* similar */ }
    fun update(p: Product) { /* +1.00 al precio */ }
    fun delete(id: Long) { /* similar */ }
}
