package com.info.kisileruygulamasiyeni.data.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.info.kisileruygulamasiyeni.data.entity.Kisiler
import com.info.kisileruygulamasiyeni.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KisilerDaoRepository (var application: Application){

    var kisilerListesi:MutableLiveData<List<Kisiler>>
    var vt:Veritabani

    init{
        kisilerListesi = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application)!!
    }

    fun kisileriGetir() : MutableLiveData<List<Kisiler>>{
        return kisilerListesi
    }

    fun kaydet(kisi_ad:String,kisi_tel:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniKisi = Kisiler(0,kisi_ad,kisi_tel)
            vt.kisilerDao().kisiEkle(yeniKisi)
        }
    }

    fun guncelle(kisi_id:Int, kisi_ad:String,kisi_tel:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(kisi_id,kisi_ad,kisi_tel)
            vt.kisilerDao().kisiGuncelle(guncellenenKisi)
        }
    }

    fun ara(aramaKelimesi:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = vt.kisilerDao().kisiAra(aramaKelimesi)
        }
    }

    fun sil(kisi_id:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Kisiler(kisi_id,"","")
            vt.kisilerDao().kisiSil(silinenKisi)
            kisileriYukle()
        }
    }

    fun kisileriYukle(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = vt.kisilerDao().tumKisiler()
        }
    }

}