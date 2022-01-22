package com.emrecan.harfnotuhesaplama

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val button=findViewById<Button>(R.id.hesapla)


        button.setOnClickListener {
            val v = findViewById<EditText>(R.id.vize)
            val f = findViewById<EditText>(R.id.finalnot)
            val notOrt = findViewById<TextView>(R.id.notortalama)
            val sinif = findViewById<EditText>(R.id.sinifort)
            val sayi = findViewById<EditText>(R.id.ogrencisayisi)



            val vize = v.text.toString().toDouble()
            val final =f.text.toString().toDouble()
            val ortSinif = sinif?.text.toString().toDouble()




            /**Çarpım Katsayısını baz alarak tabloya göre hesaplama fonksiyonu**/
            carpimKatsayisiHarfNotu(vize, final, ortSinif)


            /**Vize %40, Final %60 olacak şekilde ham ortalama hesaplama fonksiyonu**/
            notOrt.text= "Not Ortalamanız :"+hesaplama(vize,final).toString()


            /**T skor baz alınarak tabloya göre hesaplama fonksiyonu**/
            tScoreHarfNotu(vize, final, ortSinif)



        }

    }

    fun tScoreHarfNotu(vize: Double,final: Double,ortSinif: Double) {

        /**Ders başarı notunun belirlenmesinde bağıl değerlendirmeye katma sınırının üzerinde kalan öğrenci
        sayısı 20 ve üzerinde, sınıfın ham başarı notu ortalaması 70’in altında ise aşağıdaki eşitlik kullanılarak
        Tskor'ları belirlenir. T-skor’larından yola çıkılarak Çizelge 1 'deki dağılıma göre harfli notlar belirlenir.**/

        val ogrenciOrtalama = hesaplama(vize, final)
        val sinifOrtalama = ortSinif
        val ogrenciSayisi = findViewById<EditText>(R.id.ogrencisayisi)

        val sayi = ogrenciSayisi.text.toString().toDouble()

        /**T-Skor hesabı**/

        var a = ogrenciOrtalama - sinifOrtalama
        var b = Math.pow(a,a)
        var c = 1/sayi
        var d = c*b
        var e = Math.sqrt(d)
        var f = a/e

        val tScore = (f*10)+50

        /**T-Skor ekrana yazdırma**/
        tSkorPuan.text= "T Skorunuz :"+tScore


        if (ortSinif in 0.00..42.5) {

            if (tScore in 0.0..39.0) tScoreHarfNotu.text = "Bağıl Harf Notunuz : FF"
            else if (tScore in 39.0..43.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FD"
            else if (tScore in 44.0..48.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DD"
            else if (tScore in 49.0..53.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DC"
            else if (tScore in 54.0..58.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CC"
            else if (tScore in 59.0..63.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CB"
            else if (tScore in 64.0..68.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BB"
            else if (tScore in 69.0..73.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BA"
            else if (tScore in 74.0..2000.00) tScoreHarfNotu.text ="Bağıl Harf Notunuz : AA"


        } else if (ortSinif in 42.5..47.5) {

            if (tScore in 0.0..37.0) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FF"
            else if (tScore in 37.0..41.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FD"
            else if (tScore in 42.0..46.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DD"
            else if (tScore in 47.0..51.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DC"
            else if (tScore in 52.0..56.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CC"
            else if (tScore in 57.0..61.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CB"
            else if (tScore in 62.0..66.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BB"
            else if (tScore in 67.0..71.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BA"
            else if (tScore in 72.0..2000.00) tScoreHarfNotu.text ="Bağıl Harf Notunuz : AA"


        } else if (ortSinif in 47.5..52.5) {

            if (tScore in 0.0..35.0) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FF"
            else if (tScore in 35.0..39.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FD"
            else if (tScore in 40.0..44.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DD"
            else if (tScore in 45.0..49.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DC"
            else if (tScore in 50.0..54.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CC"
            else if (tScore in 55.0..59.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CB"
            else if (tScore in 60.0..64.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BB"
            else if (tScore in 65.0..69.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BA"
            else if (tScore in 70.0..2000.00) tScoreHarfNotu.text ="Bağıl Harf Notunuz : AA"


        } else if (ortSinif in 52.5..57.5) {

            if (tScore in 0.0..33.0) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FF"
            else if (tScore in 33.0..37.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FD"
            else if (tScore in 38.0..42.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DD"
            else if (tScore in 43.0..47.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DC"
            else if (tScore in 48.0..52.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CC"
            else if (tScore in 53.0..57.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CB"
            else if (tScore in 58.0..62.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BB"
            else if (tScore in 63.0..67.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BA"
            else if (tScore in 68.0..2000.00) tScoreHarfNotu.text ="Bağıl Harf Notunuz : AA"


        } else if (ortSinif in 57.5..62.5) {

            if (tScore in 0.0..31.0) {
            }
            else if (tScore in 31.0..35.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FD"
            else if (tScore in 36.0..40.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DD"
            else if (tScore in 41.0..45.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DC"
            else if (tScore in 46.0..50.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CC"
            else if (tScore in 51.0..55.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CB"
            else if (tScore in 56.0..60.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BB"
            else if (tScore in 61.0..65.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BA"
            else if (tScore in 66.0..2000.00) tScoreHarfNotu.text ="Bağıl Harf Notunuz : AA"


        } else if (ortSinif in 62.5..70.0) {

            if (tScore in 0.0..29.0) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FF"
            else if (tScore in 29.0..33.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : FD"
            else if (tScore in 34.0..38.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DD"
            else if (tScore in 39.0..43.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : DC"
            else if (tScore in 44.0..48.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CC"
            else if (tScore in 49.0..53.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : CB"
            else if (tScore in 54.0..58.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BB"
            else if (tScore in 59.0..63.99) tScoreHarfNotu.text ="Bağıl Harf Notunuz : BA"
            else if (tScore in 64.0..2000.00) tScoreHarfNotu.text ="Bağıl Harf Notunuz : AA"


        }


    }

    fun hesaplama(vize:Double, final:Double): Double {

        val ortalama = (vize*0.4)+(final*0.6)

        return ortalama

    }

    /**öğrenci sayısı 19'dan az ve sınıf ham ortalaması 70'ten yüksek ise yapılan hesaplama**/
    fun carpimKatsayisiHarfNotu(vize: Double, final: Double, ortSinif: Double) {

        val harfnotu = findViewById<TextView>(R.id.harfnotu)

        /**Çarpım Katsayısı= 1+(100-sınıf ortalaması)*0,004**/
        val carpimkatsayisi = (1 + (100 - ortSinif) * 0.004)

        /**Öğrenci sayısı 19 ve daha az ise, öğrencinin ham başarı notu çarpım katsayısı ile çarpılarak elde
        edilen puanın Çizelge 2’teki harf karşılığı öğrencinin başarı notu olarak belirler.**/

        /**Sınıf ortalaması 70 ve üzeri ise sınıf mevcuduna bakılmaksızın öğrencinin ham başarı notu çarpım
        katsayı ile çarpılır, elde edilen puanın Çizelge 2’teki harf karşılığı öğrencinin başarı notu olarak
        belirler.**/
        val hamBasariCarpimi = carpimkatsayisi * hesaplama(vize, final)


        if (hamBasariCarpimi in 0.0..39.0) harfnotu.text ="Mutlak Harf Notunuz : FF"
        else if (hamBasariCarpimi in 40.0..49.0) harfnotu.text ="Mutlak Harf Notunuz : FD"
        else if (hamBasariCarpimi in 50.0..59.0) harfnotu.text ="Mutlak Harf Notunuz : DD"
        else if (hamBasariCarpimi in 60.0..69.0) harfnotu.text ="Mutlak Harf Notunuz : DC"
        else if (hamBasariCarpimi in 70.0..74.0) harfnotu.text ="Mutlak Harf Notunuz : CC"
        else if (hamBasariCarpimi in 75.0..79.0) harfnotu.text ="Mutlak Harf Notunuz : CB"
        else if (hamBasariCarpimi in 80.0..84.0) harfnotu.text ="Mutlak Harf Notunuz : BB"
        else if (hamBasariCarpimi in 85.0..89.0) harfnotu.text ="Mutlak Harf Notunuz : BA"
        else if (hamBasariCarpimi in 90.0..100.00) harfnotu.text ="Mutlak Harf Notunuz : AA"


    }

    /**klavye gizleme fonksiyonu**/
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}













