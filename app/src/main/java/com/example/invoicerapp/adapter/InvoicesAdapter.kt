package com.example.invoicerapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.invoicerapp.databinding.ItemInvoiceOverviewBinding
import com.example.invoicerapp.datamodel.Invoice
import com.example.invoicerapp.datamodel.InvoiceStatus

class InvoicesAdapter(private val context: Context?) :
    ListAdapter<Invoice, InvoicesAdapter.InvoicesOverviewViewHolder>(
        invoiceOvervewItemCallback
    ) {

    inner class InvoicesOverviewViewHolder(val viewBinding: ItemInvoiceOverviewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: Invoice) {
            with(viewBinding) {
                tvDescription.text = data.description
                tvClientName.text = "Name: ${data.clientName}"
                tvStatus.text = data.status

                when (data.status) {
                    InvoiceStatus.DRAFT.status -> cvItemContainer.setCardBackgroundColor(Color.WHITE)
                    InvoiceStatus.PENDING.status -> cvItemContainer.setCardBackgroundColor(
                        Color.parseColor(
                            "#FFBF9A"
                        )
                    )
                    InvoiceStatus.PAID.status -> cvItemContainer.setCardBackgroundColor(
                        Color.parseColor(
                            "#A3C7FF"
                        )
                    )
                }

            }
        }
    }

    companion object {
        val invoiceOvervewItemCallback = object : DiffUtil.ItemCallback<Invoice>() {

            override fun areItemsTheSame(
                oldItem: Invoice,
                newItem: Invoice
            ): Boolean {
                return newItem.hashCode() == oldItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: Invoice,
                newItem: Invoice
            ): Boolean {
                return newItem.equals(oldItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoicesOverviewViewHolder {
        return InvoicesOverviewViewHolder(
            // Important to keep the "parent, false" to make the Recycler expand
            ItemInvoiceOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: InvoicesOverviewViewHolder, position: Int) {
        val data = this.getItem(position)
        holder.bind(data)
    }

}