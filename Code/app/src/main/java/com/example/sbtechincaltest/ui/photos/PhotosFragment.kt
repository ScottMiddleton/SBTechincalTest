package com.example.sbtechincaltest.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.databinding.FragmentPhotosBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class PhotosFragment : Fragment() {
    private val viewModel: PhotosViewModel by inject()
    private lateinit var binding: FragmentPhotosBinding
    private lateinit var adapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate view and obtain an instance of the binding class
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_photos,
                container,
                false
            )

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PhotosAdapter()

        binding.photosRv.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            viewModel.getPhotos()
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.photoResponseSuccessLD.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.emptyListTv.visibility = View.GONE
            } else {
                binding.emptyListTv.visibility = View.VISIBLE
            }

            binding.refreshLayout.isRefreshing = false
            adapter.submitList(it)
        }

        viewModel.photosResponseErrorLD.observe(viewLifecycleOwner) {
            if (adapter.itemCount == 0) {
                binding.emptyListTv.visibility = View.VISIBLE
            }
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                it, Snackbar.LENGTH_LONG
            ).show()
            binding.refreshLayout.isRefreshing = false
        }

        viewModel.photosResponseLoadingLD.observe(viewLifecycleOwner) {
            binding.refreshLayout.isRefreshing = true
        }
    }
}