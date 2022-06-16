package com.maquiAR.support

import android.app.Activity
import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maquiAR.Models.Product
import java.lang.reflect.Type

object FavoriteUtils {

    private var favoriteIds:  ArrayList<Int> = ArrayList()


    fun loadFavoriteIds(activity: Activity): java.util.ArrayList<Int> {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json = sharedPref.getString("Favoritos", "[]")
        if(json!!.length < 3) {
            this.favoriteIds = ArrayList()
            return this.favoriteIds
        }
        var stringList = json!!.substring(1,json!!.length -1).split(",")
        var intList = ArrayList<Int>()
        for(string in stringList) {
            intList.add(string!!.toInt())
        }
        this.favoriteIds = intList
        return this.favoriteIds
    }

    fun saveFavorites(activity: Activity) {
        val key = "Favoritos"
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String = gson.toJson(favoriteIds)
        with(sharedPref.edit()) {
            putString(key, json)
            apply()
        }

    }

    fun getFavorites(activity: Activity): MutableList<Int>? {
        if(favoriteIds!!.size == 0) {
            loadFavoriteIds(activity)
        }
        return this.favoriteIds
    }

    fun addFavorite(id: Int, activity: Activity) {
        this.favoriteIds!!.add(id)
        saveFavorites(activity)
    }

    fun removeFavorite(id: Int, activity: Activity) {
        this.favoriteIds!!.remove(id)
        saveFavorites(activity)
    }

    fun isFavorite(id: Int) : Boolean {
        return favoriteIds!!.contains(id)
    }

    fun getProductsByIdsList(allProducts: List<Product>, favoriteIds: List<Int>): MutableList<Product>? {
        var list = ArrayList<Product>()
        for(id in favoriteIds) {
            val productWithId = allProducts.find{ it.id == id }
            productWithId?.let {
                list.add(it)
            }
        }
        return list
    }

}