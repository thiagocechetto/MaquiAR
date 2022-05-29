package com.maquiAR.arface.recyclers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.maquiAR.Models.Product
import com.maquiAR.R

class ProductCardViewRecycler(private val context: Context, productList: List<Product>) :
    RecyclerView.Adapter<ProductCardViewRecycler.ProductView>() {
    private val productListData: List<Product>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductView {
        val view: View
        val mInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.card_item_product_view, parent, false)
        return ProductView(view)
    }

    override fun onBindViewHolder(holder: ProductView, position: Int) {

        holder.productName.setText(productListData[position].name)
        holder.productDescription.append(productListData[position].description)
        holder.productCardView.setOnClickListener {
            val intent = Intent(
               // context, ActivityProductDetail::class.java
            )

            //              Passing data to the Product Detail Activity
           // intent.putExtra("ProductCheck", productListData[position])

            //              Start the activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productListData.size
    }

    class ProductView(productItem: View) : RecyclerView.ViewHolder(productItem) {
        val productCardView: CardView
        val productImage: ImageView
        val productName: TextView
        val productDescription: TextView

        init {
            productCardView = productItem.findViewById<View>(R.id.card_product_view) as CardView
            productImage = productItem.findViewById<View>(R.id.product_card_image) as ImageView
            productName = productItem.findViewById<View>(R.id.product_card_name) as TextView
            productDescription = productItem.findViewById<View>(R.id.product_card_description) as TextView
        }
    }

    init {
        productListData = productList
    }
}
