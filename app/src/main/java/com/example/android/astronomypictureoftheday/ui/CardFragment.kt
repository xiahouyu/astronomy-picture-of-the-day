package com.example.android.astronomypictureoftheday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.astronomypictureoftheday.databinding.FragmentCardBinding
import com.example.android.astronomypictureoftheday.viewModel.CardViewModel


class CardFragment : Fragment() {
    private val args: CardFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentCardBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val apodImage = args.selectedCard
        binding.viewModel = ViewModelProvider(
            this, CardViewModel.Factory(apodImage, application)).get(CardViewModel::class.java)
        return binding.root
    }
}
