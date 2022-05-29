package com.maquiAR.Models

import java.util.ArrayList

class EyeShadow(
    name: String,
    description: String,
    images: ArrayList<String>,
    ecommerceLink: String,
    color: FloatArray
) : Product(name, description, images, ecommerceLink, "models/eyeShadow.png", color)