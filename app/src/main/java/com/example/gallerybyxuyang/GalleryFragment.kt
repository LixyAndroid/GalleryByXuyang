package com.example.gallerybyxuyang


import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.swipeIndicator -> {
                swipeLayoutGallery.isRefreshing = true
                Handler().postDelayed(Runnable {galleryViewModel.fetchData() },1000)

                //galleryViewModel.fetchData()
            }

        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeLayoutGallery.isRefreshing = true

        setHasOptionsMenu(true)

        val galleryAdapter = GalleryAdapter()
        recyclerView.apply {

            adapter = galleryAdapter
            layoutManager = GridLayoutManager(requireContext(),2) as RecyclerView.LayoutManager

        }


        galleryViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GalleryViewModel::class.java)

        galleryViewModel.photoListLive.observe(this, Observer {
            galleryAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })

        galleryViewModel.photoListLive.value?:galleryViewModel.fetchData()

        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.fetchData()
        }
    }

}