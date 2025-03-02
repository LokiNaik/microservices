package com.microservices.user_service.entities.constants;

public class AppConstants {

    public static class RATING_SERVICE {
        public static final String GET_RATINGS = "http://RATING-SERVICE/ratings/user/";
        public static final String RATING_SERVICE = "ratingService";
        public static final String FALLBACK_RATING_SERVICE = "fallBackForRatingService";
    }

    public static class HOTEL_SERVICE {
        public static final String GET_HOTEL_SERVICE_URL = "http://HOTEL-SERVICE/hotel/";
        public static final String HOTEL_SERVICE_FALLBACK = "fallBackForHotelService";
        public static final String HOTEL_SERVICE = "hotelService";
    }
}
