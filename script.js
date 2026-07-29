/* ==========================================================================
   PV PEST CONTROL SERVICES - JAVASCRIPT LOGIC (Amravati Edition)
   ========================================================================== */

// --- Data Defaults ---
const DIAGNOSTIC_DATA = [
  {
    id: "wood_borer",
    title: "Yellow Powder on Furniture / Hollow Wooden Doors",
    symptomDetail: "Fine yellowish wooden dust piles under beds, cabinets, or hollow sound when tapping door frames.",
    identifiedPest: "Wood Borers / Subterranean Termites",
    severityLevel: "CRITICAL RISK",
    recommendedService: "Termite Control",
    solutionSummary: "High-pressure liquid injection & drill-fill-seal subterranean barrier to protect structural wood.",
    img: "images/diagnostic-woodborer.webp"
  },
  {
    id: "cockroach",
    title: "Small Brown Insects Crawling in Kitchen at Night",
    symptomDetail: "Fast-moving reddish-brown insects seen near stove, microwave, sink drains, or inside pantry cabinets.",
    identifiedPest: "German Cockroaches",
    severityLevel: "HIGH RISK",
    recommendedService: "Cockroach Control",
    solutionSummary: "Targeted herbal gel dots & odorless synthetic pyrethroid spray destroying central nests.",
    img: "images/diagnostic-cockroach.webp"
  },
  {
    id: "bed_bug",
    title: "Red Itchy Skin Spots After Waking Up + Mattress Stains",
    symptomDetail: "Clusters of small itchy bumps on arms/legs, dark blood specks on white bedsheets or mattress seams.",
    identifiedPest: "Bed Bugs (Cimex lectularius)",
    severityLevel: "HIGH RISK",
    recommendedService: "Bed Bug Treatment",
    solutionSummary: "2-Session high temperature thermal steaming paired with targeted anti-nymph chemical spray.",
    img: "images/diagnostic-bedbug.webp"
  },
  {
    id: "rodents",
    title: "Chewed Electric Wires, Droppings or Ceiling Noises",
    symptomDetail: "Scratches in false ceiling, gnaw marks on PVC pipes, appliance wiring damage, or dark rice-grain droppings.",
    identifiedPest: "Roof Rats / House Mice",
    severityLevel: "CRITICAL RISK",
    recommendedService: "Rodent Control",
    solutionSummary: "Tamper-proof bait boxes, sticky traps, pipe entry audits, and ultrasonic repellent placement.",
    img: "images/diagnostic-rodent.webp"
  }
];

const PROPERTY_TYPES = [
  { name: "Apartment", multiplier: 1.0, key: "apartment" },
  { name: "Row House / Duplex", multiplier: 1.3, key: "rowhouse" },
  { name: "Bungalow / Villa", multiplier: 1.6, key: "villa" },
  { name: "Commercial / Shop", multiplier: 1.5, key: "commercial" }
];

const TREATMENTS = [
  { name: "Cockroach Herbal Gel", basePrice: 800, key: "cockroach", default: true },
  { name: "Anti-Termite Drill-Fill", basePrice: 2200, key: "termite", default: true },
  { name: "Bed Bug Thermal Spray", basePrice: 1200, key: "bedbug", default: false },
  { name: "Rodent Exclusion & Bait", basePrice: 900, key: "rodent", default: false },
  { name: "Bird & Pigeon Netting", basePrice: 1599, key: "birdnetting", default: false },
  { name: "Annual AMC Package", basePrice: 3500, key: "amc", default: false }
];

const SERVICES_LIST = [
  {
    icon: "🐜",
    name: "General Pest Control",
    desc: "Complete protection from ants, spiders, silverfish, flies & crawling pests. 100% odorless spray covering kitchen & living areas.",
    price: "Starts at ₹899",
    tag: "Most Popular",
    imgPlaceholder: "General Pest Spray & Inspection",
    img: "images/service-general.webp"
  },
  {
    icon: "🪵",
    name: "Subterranean Termite Barrier",
    desc: "Deep 12mm drill-fill-seal subterranean chemical barrier along wall joints + 5 years written warranty certificate.",
    price: "Starts at ₹2,499",
    tag: "High Damage Risk",
    imgPlaceholder: "Subterranean Drill-Fill Treatment",
    img: "images/service-termite.webp"
  },
  {
    icon: "🪳",
    name: "Herbal Cockroach Control",
    desc: "100% Odorless herbal gel dots applied inside kitchen hinges & cabinets without emptying drawers or interrupting cooking.",
    price: "Starts at ₹999",
    tag: "Kitchen Special",
    imgPlaceholder: "Herbal Gel Dot Application",
    img: "images/service-cockroach.webp"
  },
  {
    icon: "🛏️",
    name: "Bed Bug Thermal Care",
    desc: "180°C thermal steam eradication combined with dual chemical treatment destroying eggs & nymphs for instant night relief.",
    price: "Starts at ₹1,499",
    tag: "Sleep Safe",
    imgPlaceholder: "180°C Mattress Thermal Steaming",
    img: "images/service-bedbug.webp"
  },
  {
    icon: "🐀",
    name: "Rodent Control & Exclusion",
    desc: "Tamper-proof bait boxes, sticky glue boards, pipe entry audits, and ultrasonic repellent placement to protect electrical wires.",
    price: "Starts at ₹1,199",
    tag: "Commercial & Home",
    imgPlaceholder: "Bait Station & Pipe Audit",
    img: "images/service-rodent.webp"
  },
  {
    icon: "🦟",
    name: "Mosquito Fogging & Larval Control",
    desc: "Outdoor thermal smoke fogging combined with drain anti-larval treatment to reduce swarms & prevent Dengue/Malaria.",
    price: "Starts at ₹1,299",
    tag: "Health Guard",
    imgPlaceholder: "Outdoor Thermal Fogging Machine",
    img: "images/service-mosquito.webp"
  },
  {
    icon: "🪵",
    name: "Wood Borer Precision Treatment",
    desc: "Precision syringe chemical injection into wooden furniture holes and oil-based timber penetrating coating to save antique items.",
    price: "Starts at ₹1,799",
    tag: "Wood Care",
    imgPlaceholder: "Wood Timber Syringe Coating",
    img: "images/service-woodborer.webp"
  },
  {
    icon: "🕊️",
    name: "Bird & Pigeon Netting Control",
    desc: "High-density Garware UV-resistant nylon netting & anti-perching stainless steel spikes for balcony, window, and solar panel protection.",
    price: "Starts at ₹1,599",
    tag: "Balcony Special",
    imgPlaceholder: "UV Garware Netting & Spike Installation",
    img: "images/service-birdnetting.webp"
  },
  {
    icon: "🛡️",
    name: "Annual AMC Package",
    desc: "Year-round pest free guarantee with 3-4 scheduled services, priority dispatch, and unlimited free emergency complaint calls.",
    price: "Starts at ₹3,499",
    tag: "Best Savings",
    imgPlaceholder: "365-Day AMC Protection Certificate",
    img: "images/service-amc.webp"
  }
];

const AMC_ROWS = [
  { feature: "Scheduled Inspections", oneTime: "1 Visit", quarterly: "3 Visits / Year", annual: "4 Visits / Year" },
  { feature: "Emergency Complaint Calls", oneTime: "Chargeable", quarterly: "1 Free Visit", annual: "UNLIMITED FREE" },
  { feature: "Re-Treatment Warranty", oneTime: "30 Days", quarterly: "90 Days / Visit", annual: "365 Days Full Year" },
  { feature: "Kitchen Cabinet Emptying", oneTime: "Not Required", quarterly: "Not Required", annual: "Not Required" },
  { feature: "Price Discount", oneTime: "Standard Price", quarterly: "Save 15%", annual: "BEST VALUE (Save 25%)" }
];

const CASES = [
  {
    title: "Severe German Cockroach Infestation in Rajendra Colony",
    before: "Over 200+ cockroaches infesting kitchen drawers, sink drains, and microwave electronics.",
    after: "Odorless herbal gel baiting dots applied in hinge corners & appliances without interrupting cooking.",
    warranty: "6 Months Zero-Cockroach Guarantee",
    beforeImg: "images/case-cockroach-before.webp",
    afterImg: "images/case-cockroach-after.webp"
  },
  {
    title: "Subterranean Termite Colony in Shyam Nagar Duplex",
    before: "Mud tubes reaching up to ceiling with hollow teakwood doors and wardrobe destruction.",
    after: "Deep 12mm drill-fill-seal chemical barrier along wall joints + furniture syringe coating.",
    warranty: "5 Years Written Warranty Certificate",
    beforeImg: "images/case-termite-before.webp",
    afterImg: "images/case-termite-after.webp"
  },
  {
    title: "Heavy Bed Bug Infestation in Camp Road Hostel",
    before: "Persistent night bites affecting residents across 4 bedrooms in Camp Road hostel.",
    after: "High-pressure 180°C thermal steam eradication followed by dual chemical treatment 7 days apart.",
    warranty: "100% Elimination Guarantee",
    beforeImg: "images/case-bedbug-before.webp",
    afterImg: "images/case-bedbug-after.webp"
  }
];

const REVIEWS = [
  {
    name: "Ramesh Kulkarni",
    location: "Rajapeth, Amravati",
    stars: "★★★★★",
    text: "PV Pest Control did anti-termite treatment at my bungalow 2 years ago. Zero termites since then. Very professional team!",
    photo: "images/review-ramesh.webp"
  },
  {
    name: "Priya Deshmukh",
    location: "Rukmini Nagar, Amravati",
    stars: "★★★★★",
    text: "The cockroach herbal gel treatment is magic. Didn't have to empty my kitchen cabinets at all and odorless!",
    photo: "images/review-priya.webp"
  },
  {
    name: "Sunil Wankhede",
    location: "Badnera Road, Amravati",
    stars: "★★★★★",
    text: "Prompt service within 45 minutes of my call. Their technician was uniformed and polite. Highly recommend their AMC plan.",
    photo: "images/review-sunil.webp"
  }
];

// --- State Variables ---
let selectedPropertyIndex = 0;
let selectedTreatments = new Set(["cockroach", "termite"]);

// --- DOM Loaded Initialization ---
document.addEventListener("DOMContentLoaded", () => {
  renderDiagnostic();
  renderCalculatorInputs();
  calculateEstimate();
  renderServices();
  renderAMC();
  renderCaseShowcase(0);
  renderReviews();

  // Slider Event
  const slider = document.getElementById("areaSlider");
  slider.addEventListener("input", (e) => {
    document.getElementById("areaValDisplay").textContent = `${parseInt(e.target.value).toLocaleString()} sq ft`;
    calculateEstimate();
  });
});

// --- Pest Diagnostic Render ---
function renderDiagnostic() {
  const container = document.getElementById("symptomSelector");
  container.innerHTML = DIAGNOSTIC_DATA.map((item, idx) => `
    <div class="symptom-item ${idx === 0 ? 'active' : ''}" onclick="selectSymptom(${idx})">
      <span>🔍</span> ${item.title}
    </div>
  `).join('');

  showSymptomResult(0);
}

function selectSymptom(index) {
  const items = document.querySelectorAll('.symptom-item');
  items.forEach((item, idx) => {
    if (idx === index) item.classList.add('active');
    else item.classList.remove('active');
  });

  showSymptomResult(index);
}

function showSymptomResult(index) {
  const item = DIAGNOSTIC_DATA[index];
  const resultBox = document.getElementById("diagnosticResult");
  resultBox.innerHTML = `
    <span class="severity-badge">${item.severityLevel}</span>
    <h3 style="color: var(--color-lime-accent); font-size: 20px; margin-bottom: 8px;">${item.identifiedPest}</h3>

    <!-- Pest Identification Visual -->
    <div class="diagnostic-img-placeholder">
      <img src="${item.img}" alt="${item.identifiedPest} symptom pattern" loading="lazy">
    </div>

    <p style="font-size: 13px; color: #CBD5E1; margin-bottom: 16px;">${item.symptomDetail}</p>
    
    <div style="background-color: var(--color-dark-surface); padding: 14px; border-radius: 8px; margin-bottom: 16px;">
      <span style="font-size: 10px; font-weight: 800; color: var(--color-lime-accent); letter-spacing: 0.5px;">RECOMMENDED TREATMENT</span>
      <p style="font-size: 13px; color: #FFFFFF; margin-top: 4px;">${item.solutionSummary}</p>
    </div>

    <button class="btn btn-accent btn-full" onclick="openBookingModal('${item.recommendedService}')">
      Book ${item.recommendedService} Inspection →
    </button>
  `;
}

// --- Calculator Logic ---
function renderCalculatorInputs() {
  // Property Pills
  const propContainer = document.getElementById("propertyTypes");
  propContainer.innerHTML = PROPERTY_TYPES.map((prop, idx) => `
    <button class="pill-btn ${idx === selectedPropertyIndex ? 'active' : ''}" onclick="selectProperty(${idx})">
      ${prop.name}
    </button>
  `).join('');

  // Treatment Checkboxes
  const treatContainer = document.getElementById("treatmentList");
  treatContainer.innerHTML = TREATMENTS.map((treat) => `
    <label class="treatment-cb-item">
      <input type="checkbox" value="${treat.key}" ${selectedTreatments.has(treat.key) ? 'checked' : ''} onchange="toggleTreatment('${treat.key}')">
      <span>${treat.name}</span>
    </label>
  `).join('');
}

function selectProperty(index) {
  selectedPropertyIndex = index;
  renderCalculatorInputs();
  calculateEstimate();
}

function toggleTreatment(key) {
  if (selectedTreatments.has(key)) {
    if (selectedTreatments.size > 1) {
      selectedTreatments.delete(key);
    }
  } else {
    selectedTreatments.add(key);
  }
  calculateEstimate();
}

function calculateEstimate() {
  const sqft = parseInt(document.getElementById("areaSlider").value);
  const propMultiplier = PROPERTY_TYPES[selectedPropertyIndex].multiplier;
  
  const baseCost = Math.round(sqft * 0.9 * propMultiplier);
  
  let treatmentCostSum = 0;
  TREATMENTS.forEach(t => {
    if (selectedTreatments.has(t.key)) {
      treatmentCostSum += t.basePrice;
    }
  });

  const discount = selectedTreatments.size >= 2 ? Math.round((baseCost + treatmentCostSum) * 0.15) : 0;
  const total = (baseCost + treatmentCostSum) - discount;

  document.getElementById("baseCost").textContent = `₹${baseCost.toLocaleString()}`;
  document.getElementById("treatmentCost").textContent = `₹${treatmentCostSum.toLocaleString()}`;
  document.getElementById("discountVal").textContent = `-₹${discount.toLocaleString()}`;
  document.getElementById("totalCost").textContent = `₹${total.toLocaleString()}`;

  const discountRow = document.getElementById("discountRow");
  if (discount > 0) {
    discountRow.style.display = "flex";
  } else {
    discountRow.style.display = "none";
  }
}

function bookCalculatedQuote() {
  const total = document.getElementById("totalCost").textContent;
  const prop = PROPERTY_TYPES[selectedPropertyIndex].name;
  const sqft = document.getElementById("areaValDisplay").textContent;
  
  const msg = encodeURIComponent(`Hello PV Pest Control, I calculated an instant estimate on your website:\nProperty: ${prop} (${sqft})\nEstimated Price: ${total}\nPlease call me to schedule an inspection.`);
  window.open(`https://wa.me/918329931123?text=${msg}`, '_blank');
}

// --- Render Services ---
function renderServices() {
  const container = document.getElementById("servicesGrid");
  container.innerHTML = SERVICES_LIST.map(s => `
    <div class="service-card">
      <div class="service-card-header">
        <span class="service-tag-badge">${s.tag || 'Certified Service'}</span>
        <div class="service-icon">${s.icon}</div>
      </div>

      <!-- Service Image -->
      <div class="service-img-placeholder">
        <img src="${s.img}" alt="${s.name} - PV Pest Control Amravati" loading="lazy">
      </div>

      <h3>${s.name}</h3>
      <p>${s.desc}</p>
      <div class="price">${s.price}</div>
      <button class="btn btn-primary btn-full" onclick="openBookingModal('${s.name}')">Book Inspection</button>
    </div>
  `).join('');
}

// --- Render AMC Table ---
function renderAMC() {
  const tbody = document.querySelector("#amcTable tbody");
  tbody.innerHTML = AMC_ROWS.map(r => `
    <tr>
      <td><strong>${r.feature}</strong></td>
      <td>${r.oneTime}</td>
      <td>${r.quarterly}</td>
      <td style="color: var(--color-lime-dark); font-weight: 800;">${r.annual}</td>
    </tr>
  `).join('');
}

// --- Showcase Tabs ---
function renderCaseShowcase(index) {
  const c = CASES[index];
  const container = document.getElementById("showcaseContainer");

  container.innerHTML = `
    <div class="case-tabs">
      <button class="case-tab-btn ${index === 0 ? 'active' : ''}" onclick="renderCaseShowcase(0)">Cockroaches</button>
      <button class="case-tab-btn ${index === 1 ? 'active' : ''}" onclick="renderCaseShowcase(1)">Termites</button>
      <button class="case-tab-btn ${index === 2 ? 'active' : ''}" onclick="renderCaseShowcase(2)">Bed Bugs</button>
    </div>

    <h3 style="font-size: 16px; margin-bottom: 12px;">${c.title}</h3>

    <div class="before-after-img-grid">
      <div class="case-img-placeholder before">
        <span class="img-badge">BEFORE TREATMENT</span>
        <img src="${c.beforeImg}" alt="Before treatment - ${c.title}" loading="lazy">
      </div>

      <div class="case-img-placeholder after">
        <span class="img-badge green">AFTER PV TREATMENT</span>
        <img src="${c.afterImg}" alt="After PV Pest Control treatment - ${c.title}" loading="lazy">
      </div>
    </div>

    <div class="before-box">
      <strong>⚠️ BEFORE:</strong> ${c.before}
    </div>

    <div class="after-box">
      <strong>🛡️ AFTER:</strong> ${c.after}
      <div style="margin-top: 6px; font-weight: 800; color: var(--color-lime-dark);">
        ✨ Warranty: ${c.warranty}
      </div>
    </div>
  `;
}

// --- Render Reviews ---
function renderReviews() {
  const container = document.getElementById("reviewsGrid");
  container.innerHTML = REVIEWS.map(r => `
    <div class="review-card">
      <div class="review-img-placeholder">
        <img src="${r.photo}" alt="${r.name}" loading="lazy">
      </div>
      <div class="review-stars">${r.stars}</div>
      <div class="review-quote">"${r.text}"</div>
      <div class="review-author">${r.name}</div>
      <div class="review-location">📍 ${r.location}</div>
    </div>
  `).join('');
}

// --- Modal Controls ---
function openBookingModal(presetService = '') {
  const modal = document.getElementById("bookingModal");
  modal.classList.add("open");
  if (presetService) {
    document.getElementById("custService").value = presetService;
  }
}

function closeBookingModal() {
  const modal = document.getElementById("bookingModal");
  modal.classList.remove("open");
}

function handleFormSubmit(e) {
  e.preventDefault();
  const name = document.getElementById("custName").value;
  const phone = document.getElementById("custPhone").value;
  const area = document.getElementById("custArea").value;
  const service = document.getElementById("custService").value;

  const text = encodeURIComponent(`New Booking Request from Website:\nName: ${name}\nPhone: ${phone}\nArea: ${area}\nService Needed: ${service}`);
  window.open(`https://wa.me/918329931123?text=${text}`, '_blank');
  closeBookingModal();
}
