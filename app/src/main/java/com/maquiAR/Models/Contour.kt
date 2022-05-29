package com.maquiAR.Models

class Contour (name: String,
               description: String,
               images: java.util.ArrayList<String>,
               ecommerceLink: String,
               color: FloatArray
) : Product(name, description, images, ecommerceLink, "models/contour.png", color)