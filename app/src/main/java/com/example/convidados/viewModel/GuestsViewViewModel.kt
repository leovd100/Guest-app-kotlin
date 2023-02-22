package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestsViewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository =
        GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guest: LiveData<List<GuestModel>> = listAllGuests


    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    fun getAbsent(){
        listAllGuests.value = repository.getAbsent()
    }

    fun getPresent(){
        listAllGuests.value = repository.getPresent()
    }

    fun delete(id: Int){
        repository.delete(id)

    }
}