package com.example.bambainsidekotlin

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vivebamba.client.apis.StoreApi
import com.vivebamba.client.models.Product
import org.json.JSONArray

class BambaService{
    fun getProducts(): ArrayList<Product>{
        val storeApi: StoreApi = StoreApi();
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<Product> = moshi.adapter(Product::class.java)
        val products: List<Product> = storeApi.storeProductsGet();
        val jsArray = JSONArray(products)
        val productList = ArrayList<Product>()
        for (i in 0 until jsArray.length()) {
            val jsonObject = jsArray.getJSONObject(i)
            val  product: Product? =jsonAdapter.fromJson(jsonObject.toString())
            if (product is Product) {
                productList.add(product)
            }
        }
        return productList
    }
}