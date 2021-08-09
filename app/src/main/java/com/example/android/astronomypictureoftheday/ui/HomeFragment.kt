package com.example.android.astronomypictureoftheday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.astronomypictureoftheday.adapter.ApodAdapter
import com.example.android.astronomypictureoftheday.databinding.FragmentHomeBinding
import com.example.android.astronomypictureoftheday.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.recyclerView.adapter = ApodAdapter(ApodAdapter.OnClickListener{
            viewModel.displayCardDetails(it)
        })

        viewModel.navigateToSelectedCard.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCardFragment(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayCardDetailsComplete()
            }
        })

        return binding.root
    }

}