package com.example.iconfind.core.networkdata.entity.model

data class Iconset(
    val are_all_icons_glyph: Boolean,
    val author: Author,
    val categories: List<CategoryXX>,
    val icons_count: Int,
    val iconset_id: Int,
    val identifier: String,
    val is_premium: Boolean,
    val name: String,
    val prices: List<PriceX>,
    val published_at: String,
    val styles: List<StyleX>,
    val type: String
)