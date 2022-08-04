package com.start.constantaintership.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.constantaintership.MovieAdapter
import com.start.constantaintership.OnItemClickListener
import com.start.constantaintership.databinding.MainFragmentBinding
import com.start.constantaintership.models.MovieModel
import com.start.constantaintership.repositories.MovieRepository
import com.start.constantaintership.repositories.MyViewModelFactory

class MainFragment : Fragment(), OnItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private lateinit var viewModel: MainViewModel
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MovieRepository()))[MainViewModel::class.java]

        initRecyclerView()
        initViewModel()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        binding.rvMoviesList.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = MovieAdapter(requireContext(),this)
        binding.rvMoviesList.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setMovieList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }

    override fun onItemClicked(movie: MovieModel) {
        var aboutDialog = AlertDialog.Builder(
            requireContext()).setMessage("Фильм \"${movie.title.toString()}\" был нажат")
            .setPositiveButton("OK") { _, _ -> }.create()
        aboutDialog.show();
    }

}