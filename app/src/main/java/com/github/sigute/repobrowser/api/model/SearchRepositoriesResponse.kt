package com.github.sigute.repobrowser.api.model

import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponse(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("incomplete_results") val incompleteResults: Boolean,
        @SerializedName("items") val items: List<Repository>
)
