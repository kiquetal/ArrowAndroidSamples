package com.github.jorgecastillo.kotlinandroid.data

import com.github.jorgecastillo.kotlinandroid.data.datasource.HeroesDataSource
import com.github.jorgecastillo.kotlinandroid.data.errors.Error
import com.github.jorgecastillo.kotlinandroid.domain.Result
import com.github.jorgecastillo.kotlinandroid.domain.model.SuperHero
import com.github.jorgecastillo.kotlinandroid.lang.NonEmptyList

class MarvelHeroesRepository(val dataSources: List<HeroesDataSource>) : HeroesRepository {

  override fun getHeroes(): Result<Error.HeroesNotFound, NonEmptyList<SuperHero>> =
      Result.firstSuccessIn(
          fa = dataSources,
          f = { it.getAll() },
          acc = Result.raiseError(Error.HeroesNotFound())
      )
}
