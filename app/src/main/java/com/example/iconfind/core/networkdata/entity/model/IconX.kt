package com.example.iconfind.core.networkdata.entity.model

data class IconX(
    val categories: List<CategoryXXX>,
    val containers: List<Any>,
    val icon_id: Int,
    val is_icon_glyph: Boolean,
    val is_premium: Boolean,
    val prices: List<PriceXX>,
    val published_at: String,
    val raster_sizes: List<RasterSizeX>,
    val styles: List<StyleXX>,
    val tags: List<String>,
    val type: String,
    val vector_sizes: List<VectorSizeX>
)