package com.info.kisileruygulamasiyeni.room

import androidx.room.*
import com.info.kisileruygulamasiyeni.data.entity.Kisiler

@Dao //room kütüphanesine ait olduğunu belirttik.
interface KisilerDao {
    @Query("SELECT * FROM kisiler")
    suspend fun tumKisiler() : List<Kisiler>//courutine oldugunu belirtir.

    @Insert
    suspend fun kisiEkle(kisi:Kisiler)

    @Update
    suspend fun kisiGuncelle(kisi:Kisiler)

    @Delete
    suspend fun kisiSil(kisi:Kisiler)

    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :aramaKelimesi || '%' ")
    suspend fun kisiAra(aramaKelimesi:String) : List<Kisiler>//courutine oldugunu belirtir.


}