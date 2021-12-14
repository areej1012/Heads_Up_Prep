package com.example.headsup

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.headsup.databinding.ItemRowBinding

class HeadsUpRecyle(var arrayList: ArrayList<Celebrities>) : RecyclerView.Adapter<HeadsUpRecyle.ItemHolder>() {
    class ItemHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val list = arrayList
        holder.binding.apply {
            tvName.text = arrayList[position].name
            tvTaboo1.text = arrayList[position].taboo1
            tvTaboo2.text = arrayList[position].taboo2
            tvTaboo3.text = arrayList[position].taboo3
        }
    }

    override fun getItemCount(): Int = arrayList.size
}