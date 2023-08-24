package chepsi.weather.remotedatasource.utils

val mockForecastResponse = "{\n" +
    "  \"cod\": \"200\",\n" +
    "  \"message\": 0,\n" +
    "  \"cnt\": 40,\n" +
    "  \"list\": [\n" +
    "    {\n" +
    "      \"dt\": 1661871600,\n" +
    "      \"main\": {\n" +
    "        \"temp\": 296.76,\n" +
    "        \"feels_like\": 296.98,\n" +
    "        \"temp_min\": 296.76,\n" +
    "        \"temp_max\": 297.87,\n" +
    "        \"pressure\": 1015,\n" +
    "        \"sea_level\": 1015,\n" +
    "        \"grnd_level\": 933,\n" +
    "        \"humidity\": 69,\n" +
    "        \"temp_kf\": -1.11\n" +
    "      },\n" +
    "      \"weather\": [\n" +
    "        {\n" +
    "          \"id\": 500,\n" +
    "          \"main\": \"Rain\",\n" +
    "          \"description\": \"light rain\",\n" +
    "          \"icon\": \"10d\"\n" +
    "        }\n" +
    "      ],\n" +
    "      \"clouds\": {\n" +
    "        \"all\": 100\n" +
    "      },\n" +
    "      \"wind\": {\n" +
    "        \"speed\": 0.62,\n" +
    "        \"deg\": 349,\n" +
    "        \"gust\": 1.18\n" +
    "      },\n" +
    "      \"visibility\": 10000,\n" +
    "      \"pop\": 0.32,\n" +
    "      \"rain\": {\n" +
    "        \"3h\": 0.26\n" +
    "      },\n" +
    "      \"sys\": {\n" +
    "        \"pod\": \"d\"\n" +
    "      },\n" +
    "      \"dt_txt\": \"2022-08-30 15:00:00\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"dt\": 1661882400,\n" +
    "      \"main\": {\n" +
    "        \"temp\": 295.45,\n" +
    "        \"feels_like\": 295.59,\n" +
    "        \"temp_min\": 292.84,\n" +
    "        \"temp_max\": 295.45,\n" +
    "        \"pressure\": 1015,\n" +
    "        \"sea_level\": 1015,\n" +
    "        \"grnd_level\": 931,\n" +
    "        \"humidity\": 71,\n" +
    "        \"temp_kf\": 2.61\n" +
    "      },\n" +
    "      \"weather\": [\n" +
    "        {\n" +
    "          \"id\": 500,\n" +
    "          \"main\": \"Rain\",\n" +
    "          \"description\": \"light rain\",\n" +
    "          \"icon\": \"10n\"\n" +
    "        }\n" +
    "      ],\n" +
    "      \"clouds\": {\n" +
    "        \"all\": 96\n" +
    "      },\n" +
    "      \"wind\": {\n" +
    "        \"speed\": 1.97,\n" +
    "        \"deg\": 157,\n" +
    "        \"gust\": 3.39\n" +
    "      },\n" +
    "      \"visibility\": 10000,\n" +
    "      \"pop\": 0.33,\n" +
    "      \"rain\": {\n" +
    "        \"3h\": 0.57\n" +
    "      },\n" +
    "      \"sys\": {\n" +
    "        \"pod\": \"n\"\n" +
    "      },\n" +
    "      \"dt_txt\": \"2022-08-30 18:00:00\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"dt\": 1661893200,\n" +
    "      \"main\": {\n" +
    "        \"temp\": 292.46,\n" +
    "        \"feels_like\": 292.54,\n" +
    "        \"temp_min\": 290.31,\n" +
    "        \"temp_max\": 292.46,\n" +
    "        \"pressure\": 1015,\n" +
    "        \"sea_level\": 1015,\n" +
    "        \"grnd_level\": 931,\n" +
    "        \"humidity\": 80,\n" +
    "        \"temp_kf\": 2.15\n" +
    "      },\n" +
    "      \"weather\": [\n" +
    "        {\n" +
    "          \"id\": 500,\n" +
    "          \"main\": \"Rain\",\n" +
    "          \"description\": \"light rain\",\n" +
    "          \"icon\": \"10n\"\n" +
    "        }\n" +
    "      ],\n" +
    "      \"clouds\": {\n" +
    "        \"all\": 68\n" +
    "      },\n" +
    "      \"wind\": {\n" +
    "        \"speed\": 2.66,\n" +
    "        \"deg\": 210,\n" +
    "        \"gust\": 3.58\n" +
    "      },\n" +
    "      \"visibility\": 10000,\n" +
    "      \"pop\": 0.7,\n" +
    "      \"rain\": {\n" +
    "        \"3h\": 0.49\n" +
    "      },\n" +
    "      \"sys\": {\n" +
    "        \"pod\": \"n\"\n" +
    "      },\n" +
    "      \"dt_txt\": \"2022-08-30 21:00:00\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"dt\": 1662292800,\n" +
    "      \"main\": {\n" +
    "        \"temp\": 294.93,\n" +
    "        \"feels_like\": 294.83,\n" +
    "        \"temp_min\": 294.93,\n" +
    "        \"temp_max\": 294.93,\n" +
    "        \"pressure\": 1018,\n" +
    "        \"sea_level\": 1018,\n" +
    "        \"grnd_level\": 935,\n" +
    "        \"humidity\": 64,\n" +
    "        \"temp_kf\": 0\n" +
    "      },\n" +
    "      \"weather\": [\n" +
    "        {\n" +
    "          \"id\": 804,\n" +
    "          \"main\": \"Clouds\",\n" +
    "          \"description\": \"overcast clouds\",\n" +
    "          \"icon\": \"04d\"\n" +
    "        }\n" +
    "      ],\n" +
    "      \"clouds\": {\n" +
    "        \"all\": 88\n" +
    "      },\n" +
    "      \"wind\": {\n" +
    "        \"speed\": 1.14,\n" +
    "        \"deg\": 17,\n" +
    "        \"gust\": 1.57\n" +
    "      },\n" +
    "      \"visibility\": 10000,\n" +
    "      \"pop\": 0,\n" +
    "      \"sys\": {\n" +
    "        \"pod\": \"d\"\n" +
    "      },\n" +
    "      \"dt_txt\": \"2022-09-04 12:00:00\"\n" +
    "    }\n" +
    "  ],\n" +
    "  \"city\": {\n" +
    "    \"id\": 3163858,\n" +
    "    \"name\": \"Zocca\",\n" +
    "    \"coord\": {\n" +
    "      \"lat\": 44.34,\n" +
    "      \"lon\": 10.99\n" +
    "    },\n" +
    "    \"country\": \"IT\",\n" +
    "    \"population\": 4593,\n" +
    "    \"timezone\": 7200,\n" +
    "    \"sunrise\": 1661834187,\n" +
    "    \"sunset\": 1661882248\n" +
    "  }\n" +
    "}"
