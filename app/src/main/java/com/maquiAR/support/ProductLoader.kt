package com.maquiAR.support

import com.maquiAR.Models.Contour
import com.maquiAR.Models.EyeShadow
import com.maquiAR.Models.Lipstick
import com.maquiAR.Models.Product

object ProductLoader {

    val allProducts: MutableList<Product>? = ArrayList()

    fun loadProducts(): MutableList<Product>? {
        if(allProducts!!.size > 0) {
            return allProducts
        }
        val list = ArrayList<String>()
        list.add("https://loja.catharinehill.com.br/image/cache/catalog/BATOM/BALA/batom-bala-chill-sexy-catharine-hill-1016-41-600x666.jpg")
        list.add("https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/81360/16f678fa-02fd-4bc3-a5da-82261687f998-81360-mac-tem-que-ter-batom-3g.png")
        list.add("https://www.sephora.com.br/dw/image/v2/BFJC_PRD/on/demandware.static/-/Sites-masterCatalog_Sephora/pt_BR/dwcfe17531/images/hi-res-BR/_9373088F-4D23-4D16-B6D8-43009849CBB8__Lipstick_500px_1.jpg?sw=556&sh=680&sm=fit")


        for (i in 0..14) {
            if (i % 3 == 0) {
                allProducts!!.add(
                    Contour(
                        i,
                        "Contorno",
                        "tipo base ta ligado",
                        list,
                        "https://en.wikipedia.org/wiki/Shader",
                        floatArrayOf(0.706f, 0.435f, 0.314f, 0.8f),
                        "marrom"
                    )
                )

            } else if (i % 3 == 1) {
                allProducts!!.add(
                    EyeShadow(
                        i,
                        "Sombra de olho",
                        "sombrinha",
                        list,
                        "https://en.wikipedia.org/wiki/Shader",
                        floatArrayOf(0.647f, 0.357f, 0.33f, 1f),

                        "blue"
                    )
                )

            } else {
                allProducts!!.add(
                    Lipstick(
                        i,
                        "Batom",
                        "batones",
                        list,
                        "https://en.wikipedia.org/wiki/Shader",
                        floatArrayOf(0.0f, 0.0f, 1f, 1f),
                        "blue"
                    )
                )

            }
        }
        return allProducts
    }

    fun getRandomProduct(): Product {
        return allProducts!!.random()
    }
}