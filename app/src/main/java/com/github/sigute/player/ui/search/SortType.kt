package com.github.sigute.player.ui.search

import com.github.sigute.player.R

enum class SortType(val title: Int, val apiValue: String?) {
    BestMatch(R.string.sort_options_best_match, null),
    Stars(R.string.sort_options_stars, "stars"),
    Forks(R.string.sort_options_forks, "forks"),
    LastUpdated(R.string.sort_options_last_updated, "updated")
}
