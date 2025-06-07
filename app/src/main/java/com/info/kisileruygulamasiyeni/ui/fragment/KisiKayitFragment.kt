package com.info.kisileruygulamasiyeni.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.info.kisileruygulamasiyeni.R
import com.info.kisileruygulamasiyeni.databinding.FragmentKisiKayitBinding
import com.info.kisileruygulamasiyeni.ui.viewmodel.KisiKayitVMF
import com.info.kisileruygulamasiyeni.ui.viewmodel.KisiKayitViewModel


class KisiKayitFragment : Fragment() {

    private lateinit var binding: FragmentKisiKayitBinding
    private lateinit var viewModel : KisiKayitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_kayit, container, false)
        binding.kisiKayitFragment = this
        binding.kisiKayitToolbarBaslik = "Kisi kayit"


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:KisiKayitViewModel by viewModels(){
            KisiKayitVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun kaydet(kisi_ad:String,kisi_tel:String){
        viewModel.kaydet(kisi_ad,kisi_tel)
    }
}