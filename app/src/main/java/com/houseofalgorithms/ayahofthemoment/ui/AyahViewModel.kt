package com.houseofalgorithms.ayahofthemoment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houseofalgorithms.ayahofthemoment.data.AyahRepository
import com.houseofalgorithms.ayahofthemoment.data.model.AyahResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AyahViewModel(private val repository: AyahRepository) : ViewModel() {
    private val _ayahState = MutableStateFlow<AyahResponse?>(null)
    val ayahState: StateFlow<AyahResponse?> = _ayahState

    fun fetchRandomAyah() {
        viewModelScope.launch {
            try {
                _ayahState.value = repository.getRandomAyah()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}