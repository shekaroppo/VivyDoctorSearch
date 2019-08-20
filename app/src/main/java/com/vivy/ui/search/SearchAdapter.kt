package com.vivy.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vivy.data.model.Doctor
import com.vivy.databinding.ItemSearchBinding

class SearchAdapter(private var doctors: ArrayList<Doctor>,
                    private val eventHandler: SearchEventHandler) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    fun updateData(doctors: List<Doctor>) {
        this.doctors.addAll(doctors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindings: ItemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.bind(doctor, eventHandler)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    fun clear() {
        doctors.clear()
    }

    inner class SearchViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: Doctor, eventHandler: SearchEventHandler) {
            binding.doctorModel = doctor
            binding.eventHandler = eventHandler
            binding.executePendingBindings()
        }
    }
}
