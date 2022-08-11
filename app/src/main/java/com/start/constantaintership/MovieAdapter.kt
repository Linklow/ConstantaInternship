package com.start.constantaintership

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.start.constantaintership.databinding.MovieItemBinding
import com.start.constantaintership.models.MovieStringBuilder
import com.start.constantaintership.models.MovieModel

class MovieAdapter(private val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    private var movies: List<MovieModel>? = null

    fun setMovieList(movies: List<MovieModel>?) {
        this.movies = movies?.sortedBy { it.releaseYear }
    }

    class MovieViewHolder(private val itemBinding: MovieItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bindMovie(movie: MovieModel, clickListener: OnItemClickListener){

            itemBinding.movieTitle.text = MovieStringBuilder.getMovieTitleAndYear(movie.title,movie.releaseYear)
            itemBinding.directorName.text = MovieStringBuilder.getMovieDirectorName(movie.directorName)
            itemBinding.actorNames.text = MovieStringBuilder.getMovieActorNames(movie.actorsList)

            itemView.setOnClickListener {
                clickListener.onItemClicked(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies!![position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return if (movies != null)
            movies!!.size
        else 0
    }
}

interface OnItemClickListener{
    fun onItemClicked(movie: MovieModel)
}