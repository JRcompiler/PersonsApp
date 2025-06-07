package com.info.kisileruygulamasiyeni.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.info.kisileruygulamasiyeni.data.entity.Kisiler

@Database(entities = [Kisiler::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun kisilerDao(): KisilerDao

    companion object {//static
        var INSTANCE : Veritabani? = null

        fun  veritabaniErisim(context:Context) : Veritabani?{
            if(INSTANCE == null){
                //veritabanıyla erişim sağlanmadıysa
                synchronized(Veritabani::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        Veritabani::class.java,
                        "Rehber.sqlite").createFromAsset("Rehber.sqlite").build()
                }//işlemlerin sıralı yapılmasını sağlar.{


            }
            return INSTANCE
        }
    }
}