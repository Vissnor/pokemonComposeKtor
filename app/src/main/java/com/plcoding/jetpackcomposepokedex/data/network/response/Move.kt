package com.plcoding.jetpackcomposepokedex.data.network.response

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)