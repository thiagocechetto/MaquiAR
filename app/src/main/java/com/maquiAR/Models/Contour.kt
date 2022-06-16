package com.maquiAR.Models

import com.maquiAR.R

class Contour(
    id: Int,
    name: String,
    description: String,
    images: ArrayList<String>,
    ecommerceLink: String,
    color: FloatArray,
    colorName: String
) : Product(
    id, name, description, images, ecommerceLink, "models/contour.png",
    R.drawable.icon_blush, color, colorName
)