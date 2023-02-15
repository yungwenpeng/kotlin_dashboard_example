package com.example.kotlin_dashboard_example.api

object ServerConfig {
    private const val SCHEME = "http"
    private const val HOST = "172.17.100.20"
    private const val PORT = "9090"
    const val API_URL = "$SCHEME://$HOST:$PORT"
}