package com.igluesmik.sopt.di

import androidx.room.Room
import com.igluesmik.sopt.data.local.ProfileDatabase
import com.igluesmik.sopt.data.repository.ProfileRepoImpl
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferenceModule = module {

}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), ProfileDatabase::class.java, "profile.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<ProfileDatabase>().profileDao() }
}

val repositoryModule = module {
    single { ProfileRepoImpl(get()) }
}

val viewModelModule = module{
    viewModel { ProfileViewModel(get()) }
}

val DiModule = listOf(preferenceModule, databaseModule, repositoryModule, viewModelModule)
