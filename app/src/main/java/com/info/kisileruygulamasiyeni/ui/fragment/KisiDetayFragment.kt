package com.info.kisileruygulamasiyeni.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.info.kisileruygulamasiyeni.R
import com.info.kisileruygulamasiyeni.databinding.FragmentKisiDetayBinding
import com.info.kisileruygulamasiyeni.ui.viewmodel.KisiDetayVMF
import com.info.kisileruygulamasiyeni.ui.viewmodel.KisiDetayViewModel


class KisiDetayFragment : Fragment() {

    private lateinit var binding: FragmentKisiDetayBinding
    private lateinit var viewModel: KisiDetayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_detay, container, false)
        binding.kisiDetayFragment = this
        binding.kisiDetayToolbarBaslik = "Kişi Detay"

        val bundle:KisiDetayFragmentArgs by navArgs()
        val gelenKisi = bundle.kisi
        binding.kisiNesnesi = gelenKisi

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:KisiDetayViewModel by viewModels(){
            KisiDetayVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun guncelle(kisi_id:Int, kisi_ad:String,kisi_tel:String){
        viewModel.guncelle(kisi_id,kisi_ad,kisi_tel)
    }
}