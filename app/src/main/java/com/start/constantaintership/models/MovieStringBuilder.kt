package com.start.constantaintership.models

class MovieStringBuilder {

    companion object{

        fun getMovieTitleAndYear(title: String?, year: Int?) = "$title (${year})"

        fun getMovieActorNames(actorsList: List<Actors?>?): String {
            val newList = actorsList?.distinct()
            val strForActors: String = newList?.joinToString { it -> it?.actor.toString() } ?: ""
            return "Актёры: $strForActors"
        }

        fun getMovieDirectorName(directorName: String?): String {
            val words = directorName?.split(" ")
            return "Режиссёр: ${words?.get(2)} ${words?.get(0)?.get(0)}.${words?.get(1)?.get(0)}."
        }
    }
}