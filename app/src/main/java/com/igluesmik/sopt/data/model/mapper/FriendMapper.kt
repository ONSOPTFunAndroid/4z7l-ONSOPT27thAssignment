package com.igluesmik.sopt.data.model.mapper

import com.igluesmik.sopt.data.model.domain.Friend
import com.igluesmik.sopt.data.model.network.response.ResponseUsers

object FriendMapper : Mapper<ResponseUsers, List<Friend>> {
    override fun map(input: ResponseUsers): List<Friend> {
        val list = mutableListOf<Friend>()
        for (it in input.data) {
            list.add(Friend(it.firstName, it.lastName, it.avatar))
        }
        return list.toList()
    }
}