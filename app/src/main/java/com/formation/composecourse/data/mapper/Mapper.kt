package com.formation.composecourse.data.mapper

interface Mapper<OutputType : Any, InputType : Any> {

    fun convert(input: InputType): OutputType

    fun convert(inputs: List<InputType>): List<OutputType> {
        return inputs.map { convert(it) }
    }

    fun convertOrEmpty(input: List<InputType>?): List<OutputType> {
        return convert(input ?: emptyList())
    }
}
