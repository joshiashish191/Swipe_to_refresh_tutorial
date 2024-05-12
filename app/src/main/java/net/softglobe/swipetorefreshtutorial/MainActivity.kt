package net.softglobe.swipetorefreshtutorial

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val productsList = mutableListOf<Products>()
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productsList.add(Products(1,"Pen", 10))
        productsList.add(Products(2,"Tiffin box", 150))
        productsList.add(Products(3,"Water bottle", 120))
        productsList.add(Products(4,"Bag", 250))
        productsList.add(Products(5,"Compass box", 80))
        productsList.add(Products(6,"Books", 300))

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(this)
        initializeRecyclerView()

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            productsList.add(Products(7,"Shoes", 400))
        }

    }

    private fun initializeRecyclerView() {
        findViewById<RecyclerView>(R.id.rv_products).apply {
            adapter = ProductsAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            (adapter as ProductsAdapter).submitList(productsList)
        }
    }

    override fun onRefresh() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                initializeRecyclerView()
                Toast.makeText(this, "Refresh complete", Toast.LENGTH_SHORT).show()
                if (swipeRefreshLayout.isRefreshing)
                    swipeRefreshLayout.isRefreshing = false
        }, 3000)
    }
}