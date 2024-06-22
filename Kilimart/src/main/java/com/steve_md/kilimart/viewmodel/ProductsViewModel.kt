package com.steve_md.kilimart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.kilimart.data.repository.ProductsRepository
import com.steve_md.kilimart.model.ProductsItem
import com.steve_md.kilimart.util.ApiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    init {
        getProducts()
    }

    private val _products = MutableSharedFlow<ProductsState>()
    val products: SharedFlow<ProductsState> = _products

    fun getProducts(searchQuery: String = "") {
        viewModelScope.launch {
            _products.emit(
                ProductsState(
                    isLoading = true,
                    error = null,
                    products = emptyList()
                )
            )

            productsRepository.getAllProducts().collectLatest { result ->
                when (result) {
                    is ApiStates.Error -> {
                        _products.emit(
                            ProductsState(
                                isLoading = false,
                                error = result.error?.message ?: "Unknown Error Occurred"
                            )
                        )

                        Timber.e("Get all products: ${result.error?.message ?: "Unknown Error Occurred"}")
                    }
                    is ApiStates.Success -> {
                        _products.emit(
                            ProductsState(
                                isLoading = false,
                                products = if (searchQuery != "") {
                                    result.data!!.filter {
                                        it.title.contains(searchQuery, ignoreCase = true)
                                    }
                                } else {
                                    result.data ?: emptyList()
                                }
                            )
                        )

                        Timber.e("Get all products: ${result.data}")
                    }
                    else -> {}
                }
            }
        }
    }
}

data class ProductsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val products: List<ProductsItem> = emptyList()
)
