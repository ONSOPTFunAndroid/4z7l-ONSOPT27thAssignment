package com.igluesmik.sopt.data.model.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}