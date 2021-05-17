package dee.mvvm.koin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dee.mvvm.koin.MVVM.values
import dee.mvvm.koin.R
import kotlinx.android.synthetic.main.listitem.view.*
import java.util.*


class AssestsAdapter() :
    RecyclerView.Adapter<AssestsAdapter.ViewHolder>() {
    var modelArrayList: ArrayList<values> = ArrayList<values>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUI(position, modelArrayList)
    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    fun setModelArrayList(_modelArrayList: List<values>?) {
        modelArrayList.addAll(_modelArrayList!!)
        notifyDataSetChanged()
    }

    fun updateList(list: List<values?>) {
        modelArrayList = list as ArrayList<values>
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindUI(position: Int, newsfeeds: ArrayList<values>) {

            itemView.rank.text = newsfeeds[position].rank
            itemView.symbol.text = newsfeeds[position].symbol
            itemView.coinname.text = newsfeeds[position].name
            itemView.price.text =
                "$${String.format("%.2f", newsfeeds[position].priceUsd.toDouble())}"
            itemView.changeprice.text =
                "${String.format("%.2f", newsfeeds[position].changePercent24Hr.toDouble())}%"
        }
    }
}
