# E-Commerce Android App

A full-featured native Android e-commerce application built with **Kotlin**, consuming my **E-Commerce Java + Spring Boot** REST API backend. The app delivers a polished shopping experience with authentication, product discovery, cart management, checkout, order tracking, and user activity features.

---

## Screenshots & Features

Screenshots soon...

### Authentication
- Login with **email or CPF**
- Registration with full user profile (name, CPF, phone, birth date, gender)
- **Forgot password** flow with email verification code
- JWT-based session management with expiration detection

###  Home
- Personalized greeting with the user's first name
- Auto-scrolling banner carousel with dot indicators
- Category tiles with emoji mapping
- Products grouped by category in horizontal scroll lists
- Cart badge with live item count

### Search
- Real-time product search with debounce
- Category filter chips with animated slide-in/out
- Category grid for browsing
- Empty, results, and no-results states

### Product Detail
- Image carousel with dot indicators
- Stock status (in stock / last units / out of stock)
- Average rating with star bar
- Add to cart with email verification gate
- Favorite / unfavorite toggle
- Reviews section with collapsible list
- Submit a review with optional comment

### Cart & Checkout
- Add / remove / clear cart items
- Address validation before checkout
- Freight calculation by postal code
- Spinner-based address, freight, and payment method selection
- Order summary with subtotal + freight + total
- Redirect to Stripe-hosted checkout page
- Deep link handling for payment success/failure

### Orders
- Paginated order list with status badges (Pending, Paid, Shipped, Delivered, Canceled)
- Order detail with itemized list and total

### Profile
- Profile picture upload / delete
- Edit personal info (name, phone)
- Change email and password via verification code flow
- Delete account with confirmation

### Activity
- Notification feed (reviews, comments, favorites) with unread dot
- My Reviews tab — edit rating, update/delete comment
- My Favorites tab — quick access to favorited products
- Clear all notifications

### Address Management
- CEP (postal code) auto-lookup to fill city, state, street, and neighborhood
- Add, edit, and delete delivery addresses

---

##  Architecture & Tech Stack

### Android
| Layer | Technology |
|---|---|
| Language | Kotlin |
| Architecture | MVVM (ViewModel + LiveData) |
| DI | Hilt (Dagger) |
| Navigation | Jetpack Navigation Component + Safe Args |
| Networking | Retrofit 2 + OkHttp 3 + Gson |
| Image Loading | Glide |
| Storage | DataStore Preferences (JWT token) |
| Concurrency | Kotlin Coroutines |
| UI | View Binding, Material 3, ConstraintLayout, RecyclerView |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |

### E-Commerce Backend — Java + Spring Boot
The Android app is the client for a custom-built **Java Spring Boot** REST API. The backend exposes endpoints for:

- **Auth** — `/auth/login`, `/auth/register`
- **Products** — `/products`, `/products/home`, `/products/{id}`
- **Categories** — `/categories`
- **Cart** — `/cart`, `/cart/{productId}/increment`, `/cart/{productId}/decrement`
- **Orders** — `/orders/checkout`, `/orders`, `/orders/{id}`
- **Addresses** — `/addresses`, `/cities/lookup`
- **Reviews & Comments** — `/products/{id}/reviews`, `/products/reviews/comments/{id}`
- **Favorites** — `/users/me/activity/favorites`
- **User Activity** — `/users/me/activity`
- **Email & Password** — `/verify-email/**`, `/email/**`, `/password/**`
- **Shipping** — `/cart/freight`, `/orders/{id}/shipping`

Authentication is handled via **JWT Bearer tokens**, with an `AuthInterceptor` that automatically attaches the token to protected requests and skips it for public endpoints.

---

## Project Structure

```
app/src/main/java/com/ecommerce/app/
├── data/
│   ├── api/              # Retrofit service interfaces + AuthInterceptor
│   ├── model/            # Request/Response data classes
│   └── repository/       # Repository layer (BaseRepository with safeApiCall)
├── di/
│   └── NetworkModule.kt  # Hilt DI — OkHttp, Retrofit, all API services
├── ui/
│   ├── auth/             # Login, Register, Forgot Password fragments
│   ├── customer/
│   │   ├── home/         # HomeFragment + HomeViewModel
│   │   ├── search/       # SearchFragment + SearchViewModel
│   │   ├── products/     # ProductDetail, adapters (Product, Review, Comment)
│   │   ├── cart/         # Cart, Checkout fragments + ViewModels
│   │   ├── orders/       # Orders list + Order detail
│   │   ├── payment/      # PaymentWaiting / Success / Failure fragments
│   │   ├── address/      # Address list, add, edit
│   │   └── profile/
│   │       ├── activity/ # Notifications, Reviews, Favorites
│   │       └── security/ # Change email/password, delete account
│   └── shared/           # EnterCodeFragment (shared across auth flows)
├── util/
│   ├── TokenManager.kt   # DataStore JWT persistence
│   ├── JwtDecoder.kt     # JWT expiration check
│   ├── NetworkResult.kt  # Sealed class: Loading / Success / Error
│   ├── Extensions.kt     # View helpers, formatters, toCurrency()
│   ├── MaskWatcher.kt    # Input mask (CPF, phone, CEP)
│   └── DialogUtils.kt    # Bottom sheet options dialog
└── ECommerceApp.kt       # @HiltAndroidApp Application class
```

---

## Setup & Configuration

### 1. Clone the repository

```bash
git clone https://github.com/your-username/ecommerce-android.git
cd ecommerce-android
```

### 2. Configure the API base URL

In `app/build.gradle`, update the `BASE_URL` to point to your Spring Boot backend:

```groovy
defaultConfig {
    buildConfigField "String", "BASE_URL", "\"http://YOUR_LOCAL_IP:8080\""
}

buildTypes {
    release {
        buildConfigField "String", "BASE_URL", "\"https://your-production-api.com/\""
    }
}
```

> **Tip:** Use your machine's local network IP (e.g., `192.168.1.X`) when testing on a physical device connected to the same Wi-Fi. The app already has `android:usesCleartextTraffic="true"` set for local development.

### 3. Build & Run

Open in **Android Studio Hedgehog** or later and run on a device or emulator with API 24+.

---

## Key Flows

### Payment Flow
1. User selects address, freight option, and payment method in Checkout
2. App calls `POST /orders/checkout` → receives a Stripe `checkoutUrl`
3. App opens the URL in the system browser
4. On return, the backend redirects to a deep link: `ecommerce://payment/success` or `ecommerce://payment/failure`
5. Android handles the deep link and navigates to the respective result screen

### CEP Lookup Flow
1. User types an 8-digit postal code in the address form
2. App calls `GET /cities/lookup?cep={cep}`
3. City, state, street, and neighborhood fields are auto-populated

### Email Verification Flow
1. User attempts to add a product to cart without a verified email
2. App shows a dialog and sends a verification code to the registered email
3. User enters the 6-digit code in `EnterCodeFragment`
4. On success, the user is returned to the product page with cart access unlocked

---

## Security

- JWT tokens are stored in **DataStore** (not SharedPreferences) for encrypted, async access
- Token expiration is checked locally via `JwtDecoder` on app startup
- The `AuthInterceptor` skips the `Authorization` header for public endpoints (products, categories, auth routes)
- Email change and password change both require a **verification code** sent to the user's email

---

## Design System

The app uses a **Material 3** design system with a custom green primary color (`#1DB954`), full **dark mode** support, and a consistent style library defined in `values/styles.xml`:

- `App.Button.Primary` / `App.Button.Outlined` / `App.Button.Destructive`
- `App.TextInputLayout` / `App.TextInputLayout.Password`
- `App.Card` / `App.Card.Action`
- `App.Text.PageTitle` / `App.Text.SectionTitle` / `App.Text.Price`
- `App.BackButton` / `App.Divider`

---

## E-Commerce Java + Spring Boot Repository

This Android app is the mobile client for the **E-Commerce Java Spring Boot API**:

> 🔗 [ecommerce-spring-backend](https://github.com/arthuurdp/e-commerce)

The backend handles all business logic including authentication (JWT), payment processing (Stripe), CEP lookup, shipping calculation, email sending, and database persistence.

---

This project is for educational and portfolio purposes.