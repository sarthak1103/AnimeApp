import com.google.gson.annotations.SerializedName

data class GetAnimeResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination
)

data class Data(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("episodes")
    val episodes: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("images")
    val images: Images
)

data class Images(
    @SerializedName("jpg")
    val jpg: Jpg
)

data class Jpg(
    @SerializedName("image_url")
    val imageUrl: String
)

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int
)