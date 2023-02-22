package com.example.convidados.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresente.isChecked = true

        observe()

        loadData()


    }




    override fun onClick(v: View) {
        if(v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresente.isChecked

            val model = GuestModel(guestId, name, presence)

            viewModel.save(model)
            finish()
        }
    }


    private fun observe(){
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if(it.presence ){
                binding.radioPresente.isChecked = true
            }else{
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this){
            if(it != ""){
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun loadData(){
        val bundle = intent.extras
        if( bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }

    }

}