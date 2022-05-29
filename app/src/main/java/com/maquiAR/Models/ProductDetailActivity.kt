package com.maquiAR.Models

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.maquiAR.R

class ProductDetailActivity : AppCompatActivity() {

    private val productPicked: Product? = null

    //Layout TextView fields
    private var prodName:TextView? = null
    private var prodDescription:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
    }
}