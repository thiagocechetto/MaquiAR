package com.maquiAR.support

import com.maquiAR.Models.Blush
import com.maquiAR.Models.EyeShadow
import com.maquiAR.Models.Lipstick
import com.maquiAR.Models.Product

object ProductLoader {

    val allProducts: MutableList<Product>? = ArrayList()

    fun loadProducts(): MutableList<Product>? {
        if(allProducts!!.size > 0) {
            return allProducts
        }

        allProducts!!.add(
            Lipstick(
                62,
                "Batom e Brilho labial Luisance",
                "Marca: Luisance \n",
                ArrayList(listOf("https://duvidasdebeleza.com.br/wp-content/uploads/2016/04/resenha-batom-liquido-matte-luisance-roxo512-blog-duvidasdebeleza-1024x925.jpg","https://www.oval.co.uk/wp-content/uploads/Pantone.5125C.jpg")),
                "https://www.formulacertaperfumaria.com.br/produto/batom-royal-luisance/?attribute_pa_cor=03&utm_source=Google%20Shopping&utm_campaign=Google%20Shopping&utm_medium=organic&utm_term=13346",
                floatArrayOf(0.467f,0.173f,0.251f, 1f),
                "Roxo 5125"
            ))


        val list = ArrayList<String>()
        list.add("https://www.maccosmetics.com.br/media/export/cms/products/640x600/mac_sku_M0N904_640x600_0.jpg")
        list.add("https://www.maccosmetics.com.br/media/export/cms/products/640x600/mac_sku_M0N904_640x600_3.jpg")
        list.add("https://www.maccosmetics.com.br/media/export/cms/products/smoosh_v2/mac_smoosh_M0N904.jpg")
        allProducts!!.add(
            Lipstick(
                45,
                "Batom Retro Matte",
                "Marca: MAC \n M·A·C Lipstick – o produto icônico que tornou a M·A·C famosa. Fórmula de longa duração que proporciona cor intensamente pigmentada e acabamento ultra matte aveludado.",
                list,
                "https://www.maccosmetics.com.br/product/13854/52593/produtos/maquiagem/labios/batom/batom-retro-matte?gclid=CjwKCAjwqauVBhBGEiwAXOepkXAQ43pAIgIbdPv-cJVzrYp7kZTDJDtlKB3g_G2R58d6OkEA4VPP3hoCMigQAvD_BwE&gclsrc=aw.ds#!/shade/ruby_woo",
                floatArrayOf(0.898f,0.216f,0.286f, 1f),
                "Ruby Woo"
            ))

        allProducts!!.add(
            Lipstick(
                77,
                "Batom",
                "Generico",
                ArrayList(listOf("https://duvidasdebeleza.com.br/wp-content/uploads/2016/04/resenha-batom-liquido-matte-luisance-roxo512-blog-duvidasdebeleza-1024x925.jpg","https://www.oval.co.uk/wp-content/uploads/Pantone.5125C.jpg")),
                "https://www.maccosmetics.com.br/product/13854/52593/produtos/maquiagem/labios/batom/batom-retro-matte?gclid=CjwKCAjwqauVBhBGEiwAXOepkXAQ43pAIgIbdPv-cJVzrYp7kZTDJDtlKB3g_G2R58d6OkEA4VPP3hoCMigQAvD_BwE&gclsrc=aw.ds#!/shade/ruby_woo",
                floatArrayOf(0.671f,0.286f,0.482f, 1f),
                "Roxo"
            ))

        allProducts!!.add(
            Lipstick(
                1023,
                "Batom Matte HLLR Chic",
                "Última moda em Paris, chega agora ao Brasil o Batom Matte HL Chic™. Feito para durar o dia todo e deixar seus lábios mais volumosos.",
                ArrayList(listOf("https://cdn.shopify.com/s/files/1/0629/9582/4879/products/Designsemnome_50_600x.jpg?v=1652683586","https://cdn.shopify.com/s/files/1/0629/9582/4879/products/Designsemnome_46_0430a2ab-251f-4faf-a59b-b87126240123_600x.jpg?v=1652683581")),
                "https://lojaferreirarodrigues.com.br/products/batom-matte-hllr-chic-paris?variant=42804346355951&currency=BRL&utm_medium=product_sync&utm_source=google&utm_content=sag_organic&utm_campaign=sag_organic",
                floatArrayOf(0.639f,0.208f,0.212f, 1f),
                "Vermelho Paixão"
            ))


        





        for (i in 0..14) {
            if (i % 3 == 0) {
                allProducts!!.add(
                    Blush(
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
                        floatArrayOf(0.647f, 0.357f, 0.33f, 0f),
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