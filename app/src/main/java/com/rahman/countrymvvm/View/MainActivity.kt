package com.rahman.countrymvvm.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahman.countrymvvm.R
import com.rahman.countrymvvm.View.adapters.CountryListAdapter
import com.rahman.countrymvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: ListViewModel;
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        mViewModel.refresh()

        countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeCountriesViewModel()
    }

    private fun observeCountriesViewModel() {
        mViewModel.countriesMutable.observe(this, Observer { countries ->
            countries?.let {
                countriesAdapter.updateCountries(it)
            }
        })

        mViewModel.countryLoadError.observe(this, Observer { errorIs ->
            errorIs?.let {
                listErrorTv.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        mViewModel.loadingProgressBar.observe(this, Observer { loader ->
            loader?.let { loaderProgress.visibility = if (it) View.VISIBLE else View.GONE
            if (it){
                listErrorTv.visibility = View.GONE
                countriesRecyclerView.visibility = View.GONE
            }/*else{
                listErrorTv.visibility = View.VISIBLE
                countriesRecyclerView.visibility = View.VISIBLE
            }*/
            }
        })
    }
}