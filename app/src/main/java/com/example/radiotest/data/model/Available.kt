package com.example.radiotest.data.model

import com.google.gson.annotations.SerializedName

data class Available(
    @SerializedName("stationuuid") val stationuuid: String? = null,
    @SerializedName("checkuuid") val checkuuid: String? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("codec") val codec: String? = null,
    @SerializedName("bitrate") val bitrate: Int? = null,
    @SerializedName("hls") val hls: Int? = null,
    @SerializedName("ok") val ok: Int? = null,
    @SerializedName("timestamp_iso8601") val timestampIso8601: String? = null,
    @SerializedName("timestamp") val timestamp: String? = null,
    @SerializedName("urlcache") val urlcache: String? = null,
    @SerializedName("metainfo_overrides_database") val metainfoOverridesDatabase: Int? = null,
    @SerializedName("public") val public: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("tags") val tags: String? = null,
    @SerializedName("countrycode") val countrycode: String? = null,
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("favicon") val favicon: String? = null,
    @SerializedName("loadbalancer") val loadbalancer: String? = null,
    @SerializedName("do_not_index") val doNotIndex: String? = null,
    @SerializedName("countrysubdivisioncode") val countrysubdivisioncode: String? = null,
    @SerializedName("server_software") val serverSoftware: String? = null,
    @SerializedName("sampling") val sampling: Int? = null,
    @SerializedName("timing_ms") val timingMs: Int? = null,
    @SerializedName("languagecodes") val languagecodes: String? = null,
    @SerializedName("ssl_error") val sslError: Int? = null,
    @SerializedName("geo_lat") val geoLat: String? = null,
    @SerializedName("geo_long") val geoLong: String? = null
)