package com.igluesmik.sopt.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igluesmik.sopt.data.model.entity.Profile
import com.igluesmik.sopt.data.repository.ProfileRepo
import com.igluesmik.sopt.data.repository.ProfileRepoImpl
import com.igluesmik.sopt.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(private val repo : ProfileRepo) : BaseViewModel() {

    private val _profileList : LiveData<List<Profile>> = repo.getAll()
    val profileList : LiveData<List<Profile>>
        get() =  _profileList

    private val _profileData = MutableLiveData<Profile>()
    val profileData : LiveData<Profile>
        get() = _profileData

    fun getProfileById(id : Int){
        addDisposable(repo.getProfileById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _profileData.postValue(it)
            }, {
                Log.e(TAG, "Get Profile", it)
            })
        )
    }

    fun insert(profile : Profile) {
        addDisposable(repo.insert(profile)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
            }, {
                Log.e(TAG, "Insert", it)
            })
        )
    }

    fun delete(id : Int) {
        addDisposable(repo.delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
            }, {
                Log.e(TAG, "Delete", it)
            })
        )
    }

    fun deleteAll() {
        addDisposable(repo.deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
            }, {
                Log.e(TAG, "Delete", it)
            })
        )
    }

    companion object{
        private val TAG = ProfileViewModel::class.java.simpleName
    }
}