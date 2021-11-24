package com.example.bambainsidekotlin.services

import com.example.bambainsidekotlin.models.DefaultPaymentMethod
import com.example.bambainsidekotlin.models.LoggedUser
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vivebamba.client.apis.CustomerApi
import com.vivebamba.client.apis.StoreApi
import com.vivebamba.client.infrastructure.OffsetDateTimeAdapter
import com.vivebamba.client.models.*
import org.json.JSONArray

class BambaService {
    fun getProducts(): ArrayList<Product>{
        val storeApi = StoreApi()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<Product> = moshi.adapter(Product::class.java)
        val products: List<Product> = storeApi.storeProductsGet()
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

    fun placeOrder(productSku: String) {

        val products = listOf(OrderProducts(productSku))
        val loggedUser = LoggedUser
        val defaultPaymentMethod = DefaultPaymentMethod

        val name = loggedUser.name
        val lastName = loggedUser.lastName
        val secondLastName = loggedUser.secondLastName
        val cellphone = loggedUser.cellphone
        val email = loggedUser.email
        val birthdate = loggedUser.birthdate
        val gender = loggedUser.gender

        val mediaTypeKey = defaultPaymentMethod.mediaTypeKey
        val mediaTypeValue = defaultPaymentMethod.mediaTypeValue

        val paymentParams = PaymentParams(mediaTypeKey, mediaTypeValue)

        val storeApi = StoreApi()
        val customer = Customer(name, lastName, secondLastName,cellphone, email, birthdate, gender)
        val order = Order(customer, products, paymentParams)
        storeApi.storeOrdersPost(order)
    }

    fun getServices(): ArrayList<CustomerService> {
        val customerApi = CustomerApi()
        val loggedUser = LoggedUser

        val services = customerApi.customerCustomerIdServicesGet(loggedUser.id)
        val jsArray = JSONArray(services)
        val servicesList = ArrayList<CustomerService>()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).add(OffsetDateTimeAdapter()).build()
        val jsonAdapter: JsonAdapter<CustomerService> = moshi.adapter(CustomerService::class.java)

        for (i in 0 until jsArray.length()) {
            val jsonObject = jsArray.getJSONObject(i)
            val customerService: CustomerService? = jsonAdapter.fromJson(jsonObject.toString())
            if (customerService is CustomerService)
                servicesList.add(customerService)
        }

        return servicesList
    }
}