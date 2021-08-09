package com.example.android.astronomypictureoftheday.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.astronomypictureoftheday.R
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
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun getShareIntent() : Intent {
        val args = CardFragmentArgs.fromBundle(requireArguments())
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setType("image/*")
            .intent
    }

    private fun shareCard() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareCard()
        }
        return super.onOptionsItemSelected(item)
    }
}
