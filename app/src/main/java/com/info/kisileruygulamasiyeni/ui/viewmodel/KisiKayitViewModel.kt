package com.info.kisileruygulamasiyeni.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.info.kisileruygulamasiyeni.data.repo.KisilerDaoRepository

class KisiKayitViewModel(application: Application) : AndroidViewModel(application) {

    val krepo = KisilerDaoRepository(application)

    fun kaydet(kisi_ad:String,kisi_tel:String){
        krepo.kaydet(kisi_ad,kisi_tel)
    }

}