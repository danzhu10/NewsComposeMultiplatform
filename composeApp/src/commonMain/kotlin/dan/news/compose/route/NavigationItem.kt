package dan.news.compose.route


sealed class NavigationItem(
    val route: String,
    val title: String
) {

    data object DetailNews : NavigationItem("/detail", "News Detail")
    data object ListNews : NavigationItem("/list", "News")

}
