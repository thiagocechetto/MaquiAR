package com.maquiAR

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maquiAR.Models.Product
import com.maquiAR.arface.AugmentedFaceFragment
import com.maquiAR.arface.AugmentedFaceListener
import com.maquiAR.arface.AugmentedFaceNode
import com.maquiAR.support.FavoriteUtils
import com.maquiAR.support.ProductLoader
import com.maquiAR.support.recyclers.ARVisualizationProductCardViewRecycler
import kotlinx.android.synthetic.main.activity_ar_visualization.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ARVisualizationActivity : AppCompatActivity(), AugmentedFaceListener,
    ARVisualizationProductCardViewRecycler.OnProductSelectedListener {

    private var productChanged = false
    private var productPicked: Product? = null

    private var openDescriptionButton: ImageButton? = null
    private var addToFavoriteButton: ImageButton? = null
    private var takePhotoButton: ImageButton? = null
    private var listProductsButton: ImageButton? = null
    private var listProductsRecycler: RecyclerView? = null
    private var toolbar: Toolbar? = null

    private var augmentedFaceFragment: AugmentedFaceFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_visualization)

        val intent = intent
        productPicked = intent.extras!!.getSerializable("Product") as Product
        loadToolbar()
        loadLayoutElements()
        augmentedFaceFragment = face_view as AugmentedFaceFragment
        augmentedFaceFragment!!.setAugmentedFaceListener(this)
    }

    override fun onFaceAdded(face: AugmentedFaceNode) {
        face.setFaceMeshTexture(productPicked!!.texturePath)
        face.setTextureColor(productPicked!!.color)
    }

    override fun onFaceUpdate(face: AugmentedFaceNode) {
        if (productChanged) {
            productChanged = false
            face.setFaceMeshTexture(productPicked!!.texturePath)
            face.setTextureColor(productPicked!!.color)
        }
    }

    fun loadToolbar() {
        toolbar = findViewById<View>(R.id.app_toolbar) as Toolbar
        toolbar!!.title = productPicked!!.name + " - " + productPicked!!.colorName
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
    }

    fun loadLayoutElements() {
        openDescriptionButton = findViewById<View>(R.id.openProductDescriptionButton) as ImageButton
        openDescriptionButton!!.setImageResource(R.drawable.icon_cart)
        addToFavoriteButton = findViewById<View>(R.id.toggleFavorite) as ImageButton
        setFavoriteIcon()
        takePhotoButton = findViewById<View>(R.id.takePhotoButton) as ImageButton
        takePhotoButton!!.setImageResource(R.drawable.icon_camera)

        listProductsButton = findViewById<View>(R.id.listProducts) as ImageButton
        listProductsButton!!.setImageResource(R.drawable.icon_list)

        loadProducts()
    }

    fun loadProducts() {
        val products = ProductLoader.loadProducts()
        loadProductsRecycler(products!!)
    }

    fun loadProductsRecycler(products: List<Product>) {
        listProductsRecycler =
            findViewById<View>(R.id.recycler_simple_products_list) as RecyclerView
        listProductsRecycler!!.visibility = View.INVISIBLE
        val prodCardAdapter = ARVisualizationProductCardViewRecycler(this, products, this)
        listProductsRecycler!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        listProductsRecycler!!.adapter = prodCardAdapter

    }

    override fun onProductSelected(product: Product) {
        this.productPicked = product
        toolbar!!.title = productPicked!!.name + " - " + productPicked!!.colorName
        setFavoriteIcon()
        productChanged = true

    }

    fun openProductDescription(view: View?) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("ProductCheck", productPicked!!)
        startActivity(intent)
    }

    fun toggleFavorite(view: View?) {
        if (checkProductInFavorite()) {
            removeProductFromFavorite()
        } else {
            addProductToFavorite()
        }
    }

    fun checkProductInFavorite(): Boolean {
        return FavoriteUtils.isFavorite(this.productPicked!!.id)
    }

    fun setFavoriteIcon() {
        if (checkProductInFavorite()) {
            addToFavoriteButton!!.setImageResource(R.drawable.icon_favorite_64dp)
        } else {
            addToFavoriteButton!!.setImageResource(R.drawable.icon_favorite_bordered_64dp)
        }
    }

    fun addProductToFavorite() {
        FavoriteUtils.addFavorite(productPicked!!.id, this)
        Toast.makeText(
            applicationContext, "Produto adicionado aos favoritos!",
            Toast.LENGTH_SHORT
        ).show()
        addToFavoriteButton!!.setImageResource(R.drawable.icon_favorite_64dp)
    }

    fun removeProductFromFavorite() {
        FavoriteUtils.removeFavorite(productPicked!!.id, this)
        Toast.makeText(
            applicationContext, "Produto removido dos favoritos!",
            Toast.LENGTH_SHORT
        ).show()
        addToFavoriteButton!!.setImageResource(R.drawable.icon_favorite_bordered_64dp)
    }

    fun takePhoto(view: View) {
        augmentedFaceFragment!!.takeScreenshot = true
    }

    fun toggleProductList(view: View) {
        if (listProductsRecycler!!.visibility == View.INVISIBLE) {
            listProductsRecycler!!.visibility = View.VISIBLE
        } else {
            listProductsRecycler!!.visibility = View.INVISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}