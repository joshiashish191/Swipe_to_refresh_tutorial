package net.softglobe.swipetorefreshtutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.softglobe.swipetorefreshtutorial.databinding.ItemProductBinding

class ProductsAdapter : ListAdapter<Products, ProductsAdapter.ProductsViewHolder>(ProductsDiffUtils()) {

    lateinit var binding : ItemProductBinding

    inner class ProductsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind (products: Products) {
            binding.productName.text = products.name
            binding.productPrice.text = products.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_product,parent, false)

        return ProductsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductsDiffUtils : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }
}