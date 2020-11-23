package com.igluesmik.sopt.di

import androidx.room.Room
import com.igluesmik.sopt.data.local.profile.ProfileDatabase
import com.igluesmik.sopt.data.remote.UserService
import com.igluesmik.sopt.data.repository.ProfileRepoImpl
import com.igluesmik.sopt.data.repository.UserRepoImpl
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel
import com.igluesmik.sopt.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val databaseModule = module {
    single {
        Room.databaseBuilder(get(), ProfileDatabase::class.java, "profile.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<ProfileDatabase>().profileDao() }
}

val remoteDataSourceModule = module {
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://15.164.83.210:3000")
            .build()
    }
    single { get<Retrofit>().create(UserService::class.java) }
}

val repositoryModule = module {
    single { ProfileRepoImpl(get()) }
    single { UserRepoImpl(get()) }
}

val viewModelModule = module{
    viewModel { ProfileViewModel(get()) }
    viewModel { UserViewModel(get()) }
}

val DiModule = listOf(databaseModule, remoteDataSourceModule, repositoryModule, viewModelModule)
