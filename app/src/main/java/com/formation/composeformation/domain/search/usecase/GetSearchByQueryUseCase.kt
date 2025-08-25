package com.formation.composeformation.domain.search.usecase

import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.search.model.MediaType
import com.formation.composeformation.domain.search.model.SearchDataDomain
import com.formation.composeformation.domain.search.model.SearchDomain
import com.formation.composeformation.domain.search.repository.SearchRepository
import javax.inject.Inject

data class SearchByQueryData(
    val cast: List<SearchDomain>,
    val movies: List<SearchDomain>,
    val tvShows: List<SearchDomain>
)

class GetSearchByQueryUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        language: LanguageCode = LanguageCode.EN_US,
        page: Int = 1
    ): Result<SearchByQueryData> {
        return searchRepository.getSearchByQuery(
            query = query,
            language = language,
            page = page
        ).map { searchData ->
            val cast = searchData.results.filter {
                it.mediaType == MediaType.Person &&
                    it.profilePath.isNotEmpty() &&
                    it.name.isNotEmpty()
            }

            val movies = searchData.results.filter {
                it.mediaType == MediaType.Movie &&
                    it.posterPath.isNotEmpty() &&
                    it.title.isNotEmpty()
            }

            val tvShows = searchData.results.filter {
                it.mediaType == MediaType.Tv &&
                    it.posterPath.isNotEmpty() &&
                    it.title.isNotEmpty()
            }

            SearchByQueryData(
                cast = cast,
                movies = movies,
                tvShows = tvShows
            )
        }
    }
}
