package com.info.kisileruygulamasiyeni.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.info.kisileruygulamasiyeni.R
import com.info.kisileruygulamasiyeni.data.entity.Kisiler
import com.info.kisileruygulamasiyeni.databinding.FragmentAnasayfaBinding
import com.info.kisileruygulamasiyeni.ui.adapter.KisilerAdapter
import com.info.kisileruygulamasiyeni.ui.viewmodel.AnasayfaVMF
import com.info.kisileruygulamasiyeni.ui.viewmodel.AnasayfaViewModel

class AnasayfaFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel : AnasayfaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment = this
        binding.anasayfaToolbarBaslik = "Kişiler"

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        viewModel.kisilerListesi.observe(viewLifecycleOwner){
            val adapter = KisilerAdapter(requireContext(),it,viewModel)
            binding.kisilerAdapter = adapter
        }

        requireActivity().addMenuProvider(object : MenuProvider{

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu)

                val item = menu.findItem(R.id.action_ara)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnasayfaViewModel by viewModels(){
            AnasayfaVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun fabTikla(it:View){
        Navigation.findNavController(it).navigate(R.id.kisiKayitGecis)
    }

    override fun onQueryTextSubmit(query: String): Boolean { //Arama butonuna basıldığında çalışır.
        ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe çalışır
        ara(newText)
        return true
    }

    fun ara(aramaKelimesi:String){
        viewModel.ara(aramaKelimesi)
    }

    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }

}