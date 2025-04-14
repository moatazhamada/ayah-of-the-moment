package com.houseofalgorithms.ayahofthemoment.di

import android.content.Context
import com.houseofalgorithms.ayahofthemoment.data.AyahRepository
import com.houseofalgorithms.ayahofthemoment.ui.AyahViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repository
    single { AyahRepository() }
    
    // ViewModel
    viewModel { (context: Context) -> AyahViewModel(get(), context) }
} 