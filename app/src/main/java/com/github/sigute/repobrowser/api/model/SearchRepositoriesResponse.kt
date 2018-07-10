package com.github.sigute.repobrowser.api.model

import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponse(@SerializedName("items") val items: List<Repository>
)
