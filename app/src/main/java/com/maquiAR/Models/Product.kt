package com.maquiAR.Models

import java.io.Serializable
import java.util.ArrayList

abstract class Product(
    val id: Int,
    val name: String,
    val description: String,
    val images: ArrayList<String>,
    val ecommerceLink: String,
    val texturePath: String,
    val icon: Int,
    val color: FloatArray,
    val colorName: String
) : Serializable