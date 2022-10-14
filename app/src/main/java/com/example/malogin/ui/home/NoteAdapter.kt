package com.example.malogin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.malogin.database.entity.NoteEntity
import com.example.malogin.databinding.NoteItemBinding

class NoteAdapter(private val listener: NoteItemClickListener) : RecyclerView.Adapter<NoteAdapter.NoteItemViewHolder>() {

    private var items: MutableList<NoteEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class NoteItemViewHolder(var binding: NoteItemBinding, private val listener: NoteItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: NoteEntity) {
            with(item) {
                binding.tvNote.text = this.note
                binding.tvTitle.text = this.title
                binding.ivDelete.setOnClickListener{ listener.onDeleteMenuClicked(item) }
                binding.ivEdit.setOnClickListener{ listener. onEditMenuClicked(item) }
            }

        }
    }

    fun setItems(items: List<NoteEntity>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<NoteEntity>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun deleteItem(item: NoteEntity){
        this.items.remove(item)
        notifyDataSetChanged()
    }

}

interface NoteItemClickListener {
    fun onDeleteMenuClicked(item: NoteEntity)
    fun onEditMenuClicked(item: NoteEntity)
}