package com.example.model

data class PropertyOption(val name: String, val multiplier: Double, val iconRes: String)

data class PropertySizeOption(val name: String, val multiplier: Double, val detail: String)

data class PestConcernOption(val name: String, val basePrice: Int, val description: String)

data class TreatmentFrequencyOption(val name: String, val discountPercent: Int, val badgeText: String?)

data class ServicePackage(
    val title: String,
    val subtitle: String,
    val startingPrice: String,
    val warrantyText: String,
    val keyFeatures: List<String>,
    val tag: String,
    val iconType: String
)

data class ReviewItem(
    val name: String,
    val locality: String,
    val rating: Float,
    val comment: String,
    val serviceUsed: String,
    val dateText: String
)

data class WorkGalleryItem(
    val title: String,
    val category: String,
    val description: String
)

data class DiagnosticSymptom(
    val id: String,
    val title: String,
    val symptomDetail: String,
    val identifiedPest: String,
    val severityLevel: String,
    val recommendedService: String,
    val solutionSummary: String
)

data class AMCFatureRow(
    val featureName: String,
    val oneTimeVal: String,
    val quarterlyVal: String,
    val annualVal: String
)

data class BeforeAfterCase(
    val pestTitle: String,
    val symptomDescription: String,
    val solutionProvided: String,
    val warranty: String
)

object PestDataDefaults {
    val propertyTypes = listOf(
        PropertyOption("Apartment", 1.0, "apartment"),
        PropertyOption("Independent House", 1.25, "home"),
        PropertyOption("Shop", 1.15, "store"),
        PropertyOption("Office", 1.35, "business"),
        PropertyOption("Warehouse", 1.60, "warehouse")
    )

    val propertySizes = listOf(
        PropertySizeOption("Under 500 sq ft", 1.0, "Studio / 1 BHK / Small Shop"),
        PropertySizeOption("500–1000 sq ft", 1.4, "2 BHK / Standard Office"),
        PropertySizeOption("1000–2000 sq ft", 1.9, "3-4 BHK / Large Duplex"),
        PropertySizeOption("2000+ sq ft", 2.5, "Villa / Large Commercial")
    )

    val pestConcerns = listOf(
        PestConcernOption("General Pest Control", 899, "Ants, Spiders, Silverfish, Flies & Crawling Insects"),
        PestConcernOption("Cockroach Control", 999, "Advanced Herbal Gel + Odorless Chemical Spray"),
        PestConcernOption("Bed Bug Treatment", 1499, "2-Session Intense Steam & Anti-bedbug Chemical Treatment"),
        PestConcernOption("Termite Control", 2499, "Pre & Post Construction Drill-Fill-Seal Chemical Barrier"),
        PestConcernOption("Rodent Control", 1199, "Baiting, Traps, Entry Sealing & Ultrasonic Management"),
        PestConcernOption("Mosquito Control", 1299, "Thermal Fogging & Anti-Larval Water Treatment"),
        PestConcernOption("Wood Borer Treatment", 1799, "Precision Syringe Chemical Injection & Timber Coating"),
        PestConcernOption("Annual AMC Package", 3499, "Full-Year Protection (3 Scheduled Services + Free On-Call visits)")
    )

    val treatmentFrequencies = listOf(
        TreatmentFrequencyOption("One-Time Treatment", 0, null),
        TreatmentFrequencyOption("Quarterly AMC (3 Visits/Yr)", 15, "SAVE 15%"),
        TreatmentFrequencyOption("Annual AMC (4 Visits + On-Call)", 25, "BEST VALUE - SAVE 25%")
    )

    val servicePackages = listOf(
        ServicePackage(
            title = "General Pest Control",
            subtitle = "Complete protection from ants, spiders, silverfish & crawling pests",
            startingPrice = "₹899",
            warrantyText = "3 Months Warranty",
            keyFeatures = listOf("100% Odorless Chemical Spray", "Child & Pet Safe Formula", "Covers Kitchen, Bathrooms & Exterior", "Free Follow-up if pests recur"),
            tag = "Most Popular",
            iconType = "bug"
        ),
        ServicePackage(
            title = "Termite Control (De-Termite)",
            subtitle = "Deep drill-fill-seal subterranean barrier technology",
            startingPrice = "₹2,499",
            warrantyText = "Up to 5 Years Warranty",
            keyFeatures = listOf("Advanced Drill-Fill-Seal Barrier", "Protects Furniture, Doors & Foundations", "Govt. Approved Bayer Chemicals", "Free Inspection & Soil Check"),
            tag = "High Damage Risk",
            iconType = "shield"
        ),
        ServicePackage(
            title = "Herbal Cockroach Control",
            subtitle = "No need to empty kitchen cabinets! Advanced herbal baiting",
            startingPrice = "₹999",
            warrantyText = "6 Months Warranty",
            keyFeatures = listOf("No Cooking Interruption Needed", "Herbal Gel Dots in Cabinets & Corners", "Destroys Cockroach Nests", "Safe for Babies & Elders"),
            tag = "Kitchen Special",
            iconType = "kitchen"
        ),
        ServicePackage(
            title = "Bed Bug Eradication",
            subtitle = "2-Session intense thermal steam & chemical treatment",
            startingPrice = "₹1,499",
            warrantyText = "100% Eradication Guarantee",
            keyFeatures = listOf("Includes Mattress & Furniture Steaming", "Dual Visit Treatment (7 Days Apart)", "Eliminates Eggs & Nymphs", "Instant Relief Night 1"),
            tag = "Sleep Safe",
            iconType = "bed"
        ),
        ServicePackage(
            title = "Rodent & Rat Control",
            subtitle = "Bait station setup, glue traps & entry hole sealing",
            startingPrice = "₹1,199",
            warrantyText = "Includes Entry Audit",
            keyFeatures = listOf("Tamper-Resistant Bait Stations", "Entry Hole & Pipe Inspection", "Non-Toxic Glue Board Placement", "Prevents Wire & Cable Damage"),
            tag = "Commercial & Home",
            iconType = "rodent"
        ),
        ServicePackage(
            title = "Mosquito Fogging & Larval Control",
            subtitle = "Thermal fogging & water tank anti-larval treatment",
            startingPrice = "₹1,299",
            warrantyText = "Dengue/Malaria Guard",
            keyFeatures = listOf("Outdoor Thermal Smoke Fogging", "Anti-Larval Drains & Tank Spray", "Reduces Mosquito Swarms Instantly", "Ideal for Lawns & Complexes"),
            tag = "Health Guard",
            iconType = "mosquito"
        ),
        ServicePackage(
            title = "Wood Borer Treatment",
            subtitle = "Precision syringe chemical injection into wooden furniture holes",
            startingPrice = "₹1,799",
            warrantyText = "2 Years Warranty",
            keyFeatures = listOf("Deep Syringe Injection in Wood Holes", "Oil-Based Timber Penetrating Coating", "Prevents Yellow Powder Accumulation", "Saves Antique & Costly Furniture"),
            tag = "Wood Care",
            iconType = "wood"
        ),
        ServicePackage(
            title = "Annual Maintenance Contract (AMC)",
            subtitle = "Year-round pest free guarantee with 3-4 scheduled services + free emergency calls",
            startingPrice = "₹3,499",
            warrantyText = "365-Day Guarantee",
            keyFeatures = listOf("Covers Cockroaches, Ants, Spiders & Rodents", "4 Scheduled Inspections Per Year", "Unlimited Free Complaint Calls", "Priority Technician Assignment"),
            tag = "Best Savings",
            iconType = "amc"
        )
    )

    val diagnosticSymptoms = listOf(
        DiagnosticSymptom(
            id = "powder_wood",
            title = "Yellow Powder on Furniture / Hollow Wooden Doors",
            symptomDetail = "Fine yellowish wooden dust piles under beds, cabinets, or hollow sound when tapping door frames.",
            identifiedPest = "Wood Borers / Subterranean Termites",
            severityLevel = "CRITICAL RISK",
            recommendedService = "Termite Control",
            solutionSummary = "High-pressure liquid injection & drill-fill-seal subterranean barrier to protect structural wood."
        ),
        DiagnosticSymptom(
            id = "kitchen_night",
            title = "Small Brown Insects Crawling in Kitchen at Night",
            symptomDetail = "Fast-moving reddish-brown insects seen near stove, microwave, sink drains, or inside pantry cabinets.",
            identifiedPest = "German Cockroaches",
            severityLevel = "HIGH RISK",
            recommendedService = "Cockroach Control",
            solutionSummary = "Targeted herbal gel dots & odorless synthetic pyrethroid spray destroying central nests."
        ),
        DiagnosticSymptom(
            id = "bed_bites",
            title = "Red Itchy Skin Spots After Waking Up + Mattress Stains",
            symptomDetail = "Clusters of small itchy bumps on arms/legs, dark blood specks on white bedsheets or mattress seams.",
            identifiedPest = "Bed Bugs (Cimex lectularius)",
            severityLevel = "HIGH RISK",
            recommendedService = "Bed Bug Treatment",
            solutionSummary = "2-Session high temperature thermal steaming paired with targeted anti-nymph chemical spray."
        ),
        DiagnosticSymptom(
            id = "wire_gnaw",
            title = "Chewed Electric Wires, Droppings or Ceiling Noises",
            symptomDetail = "Scratches in false ceiling, gnaw marks on PVC pipes, appliance wiring damage, or dark rice-grain droppings.",
            identifiedPest = "Roof Rats / House Mice",
            severityLevel = "CRITICAL RISK",
            recommendedService = "Rodent Control",
            solutionSummary = "Tamper-proof bait boxes, sticky traps, pipe entry audits, and ultrasonic repellent placement."
        )
    )

    val amcFeatures = listOf(
        AMCFatureRow("Scheduled Inspections", "1 Visit", "3 Visits / Year", "4 Visits / Year"),
        AMCFatureRow("Emergency Complaint Calls", "Chargeable", "1 Free Visit", "UNLIMITED FREE"),
        AMCFatureRow("Re-Treatment Warranty", "30 Days", "90 Days / Visit", "365 Days Full Year"),
        AMCFatureRow("Kitchen Cabinet Emptying", "Not Required", "Not Required", "Not Required"),
        AMCFatureRow("Price Discount", "Standard Price", "Save 15%", "BEST VALUE (Save 25%)")
    )

    val beforeAfterCases = listOf(
        BeforeAfterCase(
            pestTitle = "Severe German Cockroach Infestation",
            symptomDescription = "Over 200+ cockroaches infesting kitchen drawers and electronic appliances in a Rajendra Colony home.",
            solutionProvided = "PV Odorless Herbal Gel baiting dots applied in hinge corners & appliances without interrupting cooking.",
            warranty = "6 Months Zero-Cockroach Guarantee"
        ),
        BeforeAfterCase(
            pestTitle = "Subterranean Termite Colony in Wooden Wardrobe",
            symptomDescription = "Mud tubes reaching up to ceiling with hollow teakwood doors in a Shyam Nagar duplex.",
            solutionProvided = "Deep 12mm drill-fill-seal chemical barrier along wall joints + furniture syringe coating.",
            warranty = "5 Years Written Warranty Certificate"
        ),
        BeforeAfterCase(
            pestTitle = "Heavy Bed Bug Infestation in Hostel Rooms",
            symptomDescription = "Persistent night bites affecting residents across 4 bedrooms in Camp Road hostel.",
            solutionProvided = "High-pressure 180°C thermal steam eradication followed by dual chemical treatment 7 days apart.",
            warranty = "100% Elimination Guarantee"
        )
    )

    val reviews = listOf(
        ReviewItem(
            name = "Ramesh Kulkarni",
            locality = "Rajendra Colony, Amravati",
            rating = 5.0f,
            comment = "PV Pest Control solved our severe cockroach issue in just one herbal gel session. Clean work, punctuality, and zero odor! Highly recommended in Amravati.",
            serviceUsed = "Herbal Cockroach Control",
            dateText = "July 2026"
        ),
        ReviewItem(
            name = "Priya Deshmukh",
            locality = "Shyam Nagar, Amravati",
            rating = 5.0f,
            comment = "We booked termite treatment for our wooden wardrobes. Mr. PV and his team drilled neatly, filled chemical barrier, and sealed every hole perfectly.",
            serviceUsed = "Termite Control",
            dateText = "June 2026"
        ),
        ReviewItem(
            name = "Sanjay Joshi",
            locality = "Camp Road, Amravati",
            rating = 5.0f,
            comment = "We opted for their Annual AMC package for our sweet shop. Prompt quarterly visits and excellent customer support. 10/10 service!",
            serviceUsed = "Annual AMC Contract",
            dateText = "May 2026"
        ),
        ReviewItem(
            name = "Anil Gawande",
            locality = "Badnera Road, Amravati",
            rating = 4.9f,
            comment = "Super fast response on WhatsApp. They reached our home in 2 hours for bed bug treatment. Sleeping peacefully now!",
            serviceUsed = "Bed Bug Eradication",
            dateText = "April 2026"
        )
    )

    val galleryItems = listOf(
        WorkGalleryItem("Anti-Termite Drilling & Chemical Injection", "Termite Barrier", "Precision 12mm drill holes along internal perimeter with high-pressure chemical barrier."),
        WorkGalleryItem("Herbal Gel Application in Modular Kitchen", "Cockroach Control", "Non-messy gel dots placed behind hinges and under sink without disturbing food items."),
        WorkGalleryItem("2-Step Thermal Steam Bed Bug Treatment", "Bed Bug Eradication", "High-temperature steam treatment targeting mattress seams, headboards, and baseboards."),
        WorkGalleryItem("Rodent Bait Station Installation", "Rodent Control", "Tamper-proof bait boxes placed along property borders to stop rats before entering."),
        WorkGalleryItem("Thermal Fogging in Residential Society Lawn", "Mosquito Guard", "Eco-friendly dense fogging eliminating adult mosquitoes and dengue breeding grounds."),
        WorkGalleryItem("Syringe Chemical Treatment for Wood Borers", "Wood Borer Care", "Specialized liquid chemical syringe treatment into wood exit holes to destroy larvae.")
    )
}

