package dee.mvvm.koin.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dee.doodleblue.Utils.customalert
import com.dee.doodleblue.Utils.customalertcancel
import dee.mvvm.koin.Adapters.AssestsAdapter
import dee.mvvm.koin.MVVM.Resource
import dee.mvvm.koin.MVVM.Viewmodels
import dee.mvvm.koin.MVVM.values
import dee.mvvm.koin.R
import kotlinx.android.synthetic.main.fragment_pricelist.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PricelistFragment : Fragment() {

    val viewModel: Viewmodels by viewModel()
    val adapter = AssestsAdapter()
    val list = arrayListOf<values>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pricelist, container, false)


        //initializing recyclerview
        view?.currencyrecyclerview?.layoutManager = LinearLayoutManager(context)
        view?.currencyrecyclerview?.adapter = adapter
        view?.currencyrecyclerview?.setHasFixedSize(true)

        //for getting initial values
        getItems()

        view.swiperefresh.setOnRefreshListener {
            getItems()
        }

        //for search filter
        view.searchbox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var filtertext = view.searchbox.text.toString().trim()
                filter(filtertext.toLowerCase());
            }
        })

        return view
    }


    private fun getItems() {

        viewModel.AssestsViewModel()

        viewModel.AssestsResponseviewmodel.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    customalert(context)
                }
                is Resource.Success -> {
                    customalertcancel()

                    view?.swiperefresh
                        ?.isRefreshing = false
                    list.addAll(it.value.data)
                    adapter.setModelArrayList(list)
                    Log.d("TAG", "getItems: ${list}")

                    viewModel.AssestsResponseviewmodel.removeObservers(viewLifecycleOwner)
                }
                is Resource.Failure -> {
                    view?.swiperefresh
                        ?.isRefreshing = false
                    customalertcancel()
                }
            }
        })

    }

    fun filter(text: String?) {
        val temp: MutableList<values> = ArrayList<values>()
        for (d in list) {
            if (d.name.toLowerCase().contains(text!!)) {
                temp.add(d)
                Log.d("TAG", "filter: $temp")
            }
        }
        adapter.updateList(temp)
    }

}