package com.info.kisileruygulamasiyeni.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.info.kisileruygulamasiyeni.data.entity.Kisiler
import com.info.kisileruygulamasiyeni.data.repo.KisilerDaoRepository

class AnasayfaViewModel(application: Application): AndroidViewModel(application) {

    val krepo = KisilerDaoRepository(application)
    var kisilerListesi: MutableLiveData<List<Kisiler>>

    init{
        kisileriYukle() //ilk çalışmada veri getirmez
        kisilerListesi = krepo.kisileriGetir()
    }

    fun ara(aramaKelimesi:String){
        krepo.ara(aramaKelimesi)
    }

    fun sil(kisi_id:Int){
        Log.e("Kişi sil",kisi_id.toString())
    }

    fun kisileriYukle(){
        krepo.kisileriYukle()
    }

}