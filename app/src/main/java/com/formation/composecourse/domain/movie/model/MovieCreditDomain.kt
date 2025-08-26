package com.formation.composecourse.domain.movie.model

enum class CreditDepartment {
    Acting,
    Directing,
    Production,
    Writing,
    Art,
    Crew,
    Editing,
    Camera,
    Unknown;

    companion object {
        fun fromString(value: String?): CreditDepartment {
            return entries.find { it.name == value } ?: Unknown
        }
    }
}

data class MovieCreditsDomain(
    val id: Int,
    val cast: List<CreditDomain>
) {
    val actors: List<CreditDomain>
        get() = cast.filter { it.department == CreditDepartment.Acting }

    val directing: List<CreditDomain>
        get() = cast.filter { it.department == CreditDepartment.Directing }

    val production: List<CreditDomain>
        get() = cast.filter { it.department == CreditDepartment.Production }

    val writing: List<CreditDomain>
        get() = cast.filter { it.department == CreditDepartment.Writing }

    val art: List<CreditDomain>
        get() = cast.filter { it.department == CreditDepartment.Art }
}

data class CreditDomain(
    val id: Int,
    val department: CreditDepartment,
    val name: String?,
    val profilePath: String?,
    val character: String
)
