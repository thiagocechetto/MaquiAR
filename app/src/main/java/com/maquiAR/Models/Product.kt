package com.maquiAR.Models

import java.util.ArrayList

abstract class Product(
     val name: String,
     val description: String,
     val images: ArrayList<String>,
     val ecommerceLink: String,
     val shadePath: String,
     val color: FloatArray
)