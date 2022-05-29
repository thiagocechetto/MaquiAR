package com.maquiAR

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.maquiAR.Models.Contour
import com.maquiAR.Models.EyeShadow
import com.maquiAR.Models.Lipstick
import com.maquiAR.Models.Product
import com.maquiAR.arface.recyclers.ProductCardViewRecycler

class ProductListActivity : AppCompatActivity() {

    private val allProducts: MutableList<Product>? = ArrayList()
    private val filtredProducts: MutableList<Product>? = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        loadToolbar()
        loadBottomMenuNavigation()

        loadProducts()

        showProducts(filtredProducts!!.toList())

    }

    fun loadProducts() {
        for (i in 0..14) {
            if(i%3 == 0) {
                allProducts!!.add(Contour("Contorno", "tipo base ta ligado", ArrayList(), "https://developer.android.com/studio/write/image-asset-studio?hl=pt-br", floatArrayOf(0.647f, 0.357f, 0.33f, 1f)))

            } else if (i%3 == 1) {
                allProducts!!.add(EyeShadow("Sombra de olho", "sombrinha", ArrayList(), "https://developer.android.com/studio/write/image-asset-studio?hl=pt-br", floatArrayOf(0.647f, 0.357f, 0.33f, 1f)))

            } else {
                allProducts!!.add(Lipstick("Batom", "batones", ArrayList(), "https://developer.android.com/studio/write/image-asset-studio?hl=pt-br", floatArrayOf(0.647f, 0.357f, 0.33f, 1f)))

            }
        }
        filtredProducts?.addAll(allProducts!!)
    }

    fun showProducts(products: List<Product>) {
        val productExhibition = findViewById<View>(R.id.recycler_products_list) as RecyclerView
        val prodCardAdapter = ProductCardViewRecycler(this, products)
        productExhibition.layoutManager = GridLayoutManager(this, 2)
        productExhibition.adapter = prodCardAdapter
    }

    fun loadToolbar() {
        val toolbar = findViewById<View>(R.id.app_toolbar) as Toolbar
        toolbar.title = "Todas as Maquiagens"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    fun loadBottomMenuNavigation() {
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottomMenu.setOnNavigationItemSelectedListener(listener)
    }

    var listener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val intent: Intent
            when (item.itemId) {
                R.id.menuCamera -> {
                    // Create intent
                    intent = Intent(
                    //   this@ActivityProductList,
                    //   ActivityProductCategory::class.java
                    )

                    // Start ProductCategory activity.
                    startActivity(intent)
                }
                R.id.menuProducts -> {
                    // Create intent
                    intent = Intent(
                       // this@ActivityProductList,
                       // ActivityMyCart::class.java
                    )

                    // Start MyCart activity.
                    startActivity(intent)
                }
                R.id.menuFavorite -> {
                    // Create intent
                    intent = Intent(
                     //   this@ActivityProductList,
                       // ActivityMyFavorite::class.java
                    )

                    // Start MyFavorite activity.
                    startActivity(intent)
                }
            }
            true
        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}