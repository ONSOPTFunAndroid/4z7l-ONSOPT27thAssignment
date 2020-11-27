package com.igluesmik.sopt.di

import androidx.room.Room
import com.igluesmik.sopt.data.local.datasource.ProfileLocalDataSource
import com.igluesmik.sopt.data.local.database.ProfileDatabase
import com.igluesmik.sopt.data.local.datasource.ProfileLocalDataSourceImpl
import com.igluesmik.sopt.data.remote.api.FriendService
import com.igluesmik.sopt.data.remote.api.UserService
import com.igluesmik.sopt.data.remote.datasource.FriendRemoteDataSource
import com.igluesmik.sopt.data.remote.datasource.FriendRemoteDataSourceImpl
import com.igluesmik.sopt.data.remote.datasource.UserRemoteDataSource
import com.igluesmik.sopt.data.remote.datasource.UserRemoteDataSourceImpl
import com.igluesmik.sopt.data.repository.ProfileRepo
import com.igluesmik.sopt.data.repository.ProfileRepoImpl
import com.igluesmik.sopt.data.repository.UserRepo
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

val networkModule = module {
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://15.164.83.210:3000")
            .build()
    }
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()
    }
    single<UserService> { get<Retrofit>().create(UserService::class.java) }
    single<FriendService> { get<Retrofit>().create(FriendService::class.java) }
}

val localDataSourceModule = module {
    single<ProfileLocalDataSource> { ProfileLocalDataSourceImpl(get()) }
}

val remoteDataSourceModule = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get())}
    single<FriendRemoteDataSource> { FriendRemoteDataSourceImpl(get())}
}

val repositoryModule = module {
    single<ProfileRepo> { ProfileRepoImpl(get()) }
    single<UserRepo> { UserRepoImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { ProfileViewModel(get()) }
    viewModel { UserViewModel(get()) }
}

val DiModule = listOf(
    databaseModule,
    networkModule,
    localDataSourceModule,
    remoteDataSourceModule,
    repositoryModule,
    viewModelModule
)
