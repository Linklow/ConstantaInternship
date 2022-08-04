package com.start.constantaintership

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.start.constantaintership.models.Actors
import com.start.constantaintership.models.MovieModel
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val context: Context, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    private var movies: List<MovieModel>? = null

    fun setMovieList(movies: List<MovieModel>?) {
        this.movies = movies?.sortedByDescending { it.releaseYear }
    }

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindMovie(movie: MovieModel, clickListener: OnItemClickListener){
            itemView.movie_title.text = "${movie.title} (${movie.releaseYear})"
            itemView.director_name.text = getDirectorName(movie.directorName)
            itemView.actor_names.text = getActorNames(movie.actorsList)

            itemView.setOnClickListener {
                clickListener.onItemClicked(movie)
            }
        }

        private fun getActorNames(actorsList: List<Actors?>?): String? {
            val newList = actorsList?.distinct()
            val strForActors: String = newList?.joinToString { it -> it?.actor.toString() } ?: ""
            return "Актёры: $strForActors"
        }

        private fun getDirectorName(directorName: String?): String? {
            val words = directorName?.split(" ")
            return "Режиссёр: ${words?.get(2)} ${words?.get(0)?.get(0)}.${words?.get(1)?.get(0)}."
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        )
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