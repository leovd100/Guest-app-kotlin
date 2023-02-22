package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewModel.GuestsViewViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null


    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         viewModel =
            ViewModelProvider(this).get(GuestsViewViewModel::class.java)

        _binding = FragmentPresentBinding.inflate(inflater, container, false)


        binding.recycleGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recycleGuests.adapter = adapter


        val listeter = object: OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresent()
            }

        }


        adapter.attachListener(listeter)


        observe()



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observe() {
        viewModel.guest.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)

        }
    }


}