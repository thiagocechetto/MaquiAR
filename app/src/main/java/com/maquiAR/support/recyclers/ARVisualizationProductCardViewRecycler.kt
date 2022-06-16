package com.maquiAR.support.recyclers

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.maquiAR.Models.Product
import com.maquiAR.R
import kotlin.math.roundToInt

class ARVisualizationProductCardViewRecycler(
    private val context: Context,
    productList: List<Product>,
    onProductSelected: OnProductSelectedListener
) :
    RecyclerView.Adapter<ARVisualizationProductCardViewRecycler.ProductView>() {
    private val productListData: List<Product>
    private val onProductSelectedListener: OnProductSelectedListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductView {
        val view: View
        val mInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.ar_visualization_card_item_product_view, parent, false)
        return ProductView(view)
    }

    override fun onBindViewHolder(holder: ProductView, position: Int) {

        holder.productName.setText(productListData[position].name)
        holder.productColor.setBackgroundColor(getColorFromFloatArray(productListData[position].color))
        holder.productImage.setImageResource(productListData[position].icon)
        holder.productCardView.setOnClickListener {
            onProductSelectedListener!!.onProductSelected(productListData[position])
        }
    }

    fun getColorFromFloatArray(array: FloatArray): Int {
        var r = (array[0] * 255).roundToInt()
        var g = (array[1] * 255).roundToInt()
        var b = (array[2] * 255).roundToInt()
        var hex = String.format("#%02x%02x%02x", r, g, b)
        return Color.parseColor(hex)
    }

    override fun getItemCount(): Int {
        return productListData.size
    }

    class ProductView(productItem: View) : RecyclerView.ViewHolder(productItem) {
        val productCardView: CardView
        val productImage: ImageView
        val productName: TextView
        val productColor: TextView

        init {
            productCardView = productItem.findViewById<View>(R.id.card_product_view) as CardView
            productImage = productItem.findViewById<View>(R.id.product_card_image) as ImageView
            productName = productItem.findViewById<View>(R.id.product_card_name) as TextView
            productColor = productItem.findViewById<View>(R.id.product_card_color) as TextView
        }
    }

    init {
        productListData = productList
        onProductSelectedListener = onProductSelected
    }

    interface OnProductSelectedListener {
        fun onProductSelected(product: Product)
    }
}