package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)



    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel


    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest


    fun save(guestMode: GuestModel){
        if(guestMode.id == 0) {
            if(repository.insert(guestMode)) {
                _saveGuest.value = "INSERÇÃO COM SUCESSO"
            }else{
                _saveGuest.value = "FALHA"
            }
        }else{
            if(repository.update(guestMode)) {
                _saveGuest.value = "ATUALIZAÇÃO COM SUCESSO"
            }else{
                _saveGuest.value = "FALHA"
            }
        }
    }


    fun get(id: Int){
       guestModel.value = repository.get(id)
    }

}