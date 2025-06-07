package com.info.kisileruygulamasiyeni.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.info.kisileruygulamasiyeni.R
import com.info.kisileruygulamasiyeni.data.entity.Kisiler
import com.info.kisileruygulamasiyeni.databinding.FragmentKisiKayitBinding
import com.info.kisileruygulamasiyeni.databinding.SatirTasarimBinding
import com.info.kisileruygulamasiyeni.ui.fragment.AnasayfaFragmentDirections
import com.info.kisileruygulamasiyeni.ui.viewmodel.AnasayfaViewModel

class KisilerAdapter(var mContext:Context,var kisilerListesi:List<Kisiler>,var viewModel: AnasayfaViewModel): RecyclerView.Adapter<KisilerAdapter.SatirTasarimTutucu>(){

    inner class SatirTasarimTutucu(var binding: SatirTasarimBinding) :  RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        //databinding tanımladığımız yer
        //
        //val binding:SatirTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.satir_tasarim,false)
        val binding: SatirTasarimBinding= DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.satir_tasarim, parent,false)
        return SatirTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val kisi = kisilerListesi.get(position)
        val t = holder.binding
        t.kisiNesnesi = kisi

        t.imageViewSil.setOnClickListener{
            Snackbar.make(it,"${kisi.kisi_ad} - ${kisi.kisi_tel}",Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    sil(kisi.kisi_id)
                }.show()
        }

        t.satirCard.setOnClickListener{
            val gecis = AnasayfaFragmentDirections.kisiDetayGecis(kisi = kisi)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return kisilerListesi.size
    }

    fun sil(kisi_id:Int){
        viewModel.sil(kisi_id)
    }

}