package com.example.hotelbooking.ui.model

data class Property(
    val name: String,
    val location: String,
    val description: String,

    val freeWifi: Boolean = false,
    val airConditioner: Boolean = false,
    val swimmingPool: Boolean = false,
    val freeParkingSlot: Boolean = false
)
val properties = listOf(
    Property(
        name = "Sunset Villa",
        location = "California",
        description = "A beautiful villa with a sunset view.",
        freeWifi = true,
        airConditioner = true,
        swimmingPool = true,
        freeParkingSlot = true
    ),
    Property(
        name = "Mountain Retreat",
        location = "Colorado",
        description = "A cozy retreat in the mountains.",
        freeWifi = true,
        airConditioner = false,
        swimmingPool = false,
        freeParkingSlot = true
    ),
    Property(
        name = "City Apartment",
        location = "New York",
        description = "A modern apartment in the heart of the city.",
        freeWifi = true,
        airConditioner = true,
        swimmingPool = false,
        freeParkingSlot = false
    ),
    Property(
        name = "Beach House",
        location = "Florida",
        description = "A charming house right on the beach.",
        freeWifi = false,
        airConditioner = true,
        swimmingPool = true,
        freeParkingSlot = true
    ),
    Property(
        name = "Country Cottage",
        location = "Vermont",
        description = "A peaceful cottage in the countryside.",
        freeWifi = false,
        airConditioner = false,
        swimmingPool = false,
        freeParkingSlot = true
    ),
    Property(
        name = "Luxury Condo",
        location = "Miami",
        description = "A luxurious condo with all amenities.",
        freeWifi = true,
        airConditioner = true,
        swimmingPool = true,
        freeParkingSlot = false
    ),
    Property(
        name = "Historic Mansion",
        location = "Virginia",
        description = "A historic mansion with beautiful gardens.",
        freeWifi = false,
        airConditioner = false,
        swimmingPool = false,
        freeParkingSlot = true
    ),
    Property(
        name = "Ski Chalet",
        location = "Switzerland",
        description = "A cozy chalet near the ski slopes.",
        freeWifi = true,
        airConditioner = true,
        swimmingPool = false,
        freeParkingSlot = true
    ),
    Property(
        name = "Urban Loft",
        location = "Chicago",
        description = "A trendy loft in the urban center.",
        freeWifi = true,
        airConditioner = true,
        swimmingPool = false,
        freeParkingSlot = false
    ),
    Property(
        name = "Island Bungalow",
        location = "Hawaii",
        description = "A secluded bungalow on a private island.",
        freeWifi = false,
        airConditioner = true,
        swimmingPool = true,
        freeParkingSlot = true
    )
)
