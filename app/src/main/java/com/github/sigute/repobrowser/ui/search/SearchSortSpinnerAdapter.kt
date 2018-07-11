package com.github.sigute.repobrowser.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.github.sigute.repobrowser.R
import kotlinx.android.synthetic.main.spinner_sort_item.view.*

class SearchSortSpinnerAdapter(context: Context) : ArrayAdapter<SortType>(context, R.layout.spinner_sort_item, SortType.values()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createView((parent as Spinner).selectedItemPosition, true)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createView(position, false)
    }

    private fun createView(position: Int, showArrow: Boolean): View {
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_sort_item, null)
        view.title.text = context.getText(SortType.values()[position].title)
        view.arrow.visibility = if (showArrow) View.VISIBLE else View.GONE
        return view
    }
}
