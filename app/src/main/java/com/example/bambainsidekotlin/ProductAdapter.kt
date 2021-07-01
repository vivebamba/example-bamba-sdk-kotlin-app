package com.example.bambainsidekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vivebamba.client.models.Product

class ProductAdapter(private val context: Context, private val dataSource: ArrayList<Product>): BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_product, parent, false)
        val titleTextView = rowView.findViewById(R.id.product_list_title) as TextView
        val subtitleTextView = rowView.findViewById(R.id.product_list_subtitle) as TextView
        val thumbnailImageView = rowView.findViewById(R.id.product_list_thumbnail) as ImageView
        val priceTextView = rowView.findViewById(R.id.product_list_price) as TextView

        val product = getItem(position) as Product

        titleTextView.text = product.name
        subtitleTextView.text = product.sku
        priceTextView.text = product.price.toString()

        Picasso.with(context).load(product.image).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return rowView
    }
}