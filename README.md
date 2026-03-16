# E-Commerce Android App

Kotlin Android client for the Spring Boot e-commerce REST API.

---

## 📋 Requirements

| Tool | Version |
|------|---------|
| Android Studio | Hedgehog 2023.1.1+ |
| JDK | 17 |
| Kotlin | 1.9.22 |
| compileSdk | 34 |
| minSdk | 24 (Android 7.0) |

---

## 🚀 Quick Start

### 1. Clone / open the project
Open **Android Studio** → *Open* → select the `ecommerce-android` folder.

### 2. Set your API base URL

Open `app/build.gradle` and update:

```groovy
defaultConfig {
    // For the Android emulator pointing at localhost:
    buildConfigField "String", "BASE_URL", "\"http://10.0.2.2:8080/\""

    // For a real device on the same network, use your machine's local IP:
    // buildConfigField "String", "BASE_URL", "\"http://192.168.1.100:8080/\""
}
```

> `10.0.2.2` is the emulator's alias for `localhost` on the host machine.

### 3. Start the Spring Boot backend
Make sure the API is running on port **8080** (see the backend README).

### 4. Sync Gradle and Run
Click **Sync Now** when prompted, then press **▶ Run**.

---

## 🏗 Architecture

```
app/
└── src/main/java/com/ecommerce/app/
    ├── data/
    │   ├── api/
    │   │   ├── ApiService.kt          ← All Retrofit endpoints
    │   │   └── AuthInterceptor.kt     ← Attaches JWT to every request
    │   ├── model/
    │   │   └── Models.kt              ← All request/response data classes
    │   └── repository/
    │       ├── BaseRepository.kt      ← safeApiCall() helper
    │       └── Repositories.kt        ← Auth, User, Product, Category,
    │                                     Cart, Order, Address repositories
    ├── di/
    │   └── NetworkModule.kt           ← Hilt: Retrofit, OkHttp, ApiService
    ├── ui/
    │   ├── MainActivity.kt            ← Single-activity host
    │   ├── MainViewModel.kt           ← Decides start destination
    │   ├── auth/                      ← Login, Register, ForgotPassword
    │   ├── customer/
    │   │   ├── home/                  ← Product grid + search
    │   │   ├── products/              ← Product detail
    │   │   ├── cart/                  ← Cart + Checkout
    │   │   ├── orders/                ← Order list + detail
    │   │   ├── address/               ← Address list + add
    │   │   └── profile/               ← Profile, edit, security
    │   └── admin/
    │       ├── dashboard/             ← Stats overview
    │       ├── products/              ← List + create/edit/delete
    │       ├── orders/                ← All orders
    │       ├── users/                 ← User list
    │       └── categories/            ← Category CRUD
    ├── util/
    │   ├── NetworkResult.kt           ← sealed class: Loading/Success/Error
    │   ├── TokenManager.kt            ← DataStore JWT persistence
    │   ├── JwtDecoder.kt              ← Lightweight JWT payload decoder
    │   └── Extensions.kt             ← View helpers, toCurrency(), etc.
    └── ECommerceApp.kt                ← @HiltAndroidApp
```

**Pattern:** MVVM · Repository · Hilt DI · Coroutines · LiveData · View Binding

---

## 🔐 Authentication Flow

1. User logs in → API returns JWT token
2. Token stored in **DataStore Preferences** via `TokenManager`
3. Every Retrofit request passes through `AuthInterceptor`, which reads the token and adds `Authorization: Bearer <token>`
4. On app launch, `MainViewModel` checks the token:
   - Missing / expired → `LoginFragment`
   - Valid + role `ROLE_ADMIN` → `AdminDashboardFragment`
   - Valid + role `ROLE_USER` → `HomeFragment`

---

## 📱 Screens

### Customer
| Screen | Description |
|--------|-------------|
| Login | Email/CPF + password |
| Register | Full registration form |
| Forgot Password | Email → 6-digit code → new password |
| Home | Product grid with search |
| Product Detail | Images, description, Add to Cart |
| Cart | Increment/decrement items, checkout |
| Checkout | Address selector + payment method |
| My Orders | Paginated order history |
| Order Detail | Items, status, total |
| Profile | Name, email display |
| Edit Profile | First name, last name, phone |
| Addresses | List + add new (auto-fill via CEP) |
| Security | Change password via email code |

### Admin
| Screen | Description |
|--------|-------------|
| Dashboard | Total products / orders / users |
| Products | Grid with FAB to create; tap to edit/delete |
| Edit Product | Name, description, price, stock, dimensions, image URL, categories |
| Orders | Full order list |
| Users | All registered users |
| Categories | List with create/delete via dialog |

---

## 🔑 Key Dependencies

| Library | Purpose |
|---------|---------|
| Hilt 2.50 | Dependency injection |
| Retrofit 2.9 + Gson | HTTP client + JSON |
| OkHttp Logging Interceptor | Request/response logging in debug |
| Glide 4.16 | Image loading |
| DataStore Preferences | Secure token storage |
| Navigation Component 2.7 | Fragment navigation + safe args |
| Material Components 1.11 | UI widgets |
| Coroutines 1.7.3 | Async operations |
| Lifecycle ViewModel + LiveData | MVVM state management |

---

## ⚙️ Configuration Reference

### `buildConfigField` values in `app/build.gradle`

| Key | Default (debug) | Notes |
|-----|----------------|-------|
| `BASE_URL` | `http://10.0.2.2:8080/` | Change for real device or production |

### Backend environment variables needed
See the Spring Boot `.env.example` — the Android app only needs `BASE_URL` pointing at the running API.

---

## 🧩 Extending the App

### Add a new screen
1. Create `Fragment` + `ViewModel` in the appropriate package
2. Add `<fragment>` entry and `<action>` entries to `res/navigation/nav_graph.xml`
3. Add layout XML in `res/layout/`
4. If it's a top-level destination, add it to `AppBarConfiguration` in `MainActivity`

### Add a new API endpoint
1. Add the `suspend fun` signature to `ApiService.kt`
2. Add a wrapper method to the relevant `Repository` using `safeApiCall { }`
3. Call it from a `ViewModel` and observe `NetworkResult` in the Fragment

---

## 🐛 Troubleshooting

| Problem | Fix |
|---------|-----|
| `CLEARTEXT communication not permitted` | Add `android:usesCleartextTraffic="true"` in `AndroidManifest.xml` (already done) |
| `Connection refused` on emulator | Use `10.0.2.2` not `localhost` |
| `Connection refused` on real device | Use your host machine's LAN IP (e.g. `192.168.1.x`) |
| Hilt build errors | Run *Build → Clean Project*, then rebuild |
| Navigation safe args not generated | Ensure `androidx.navigation.safeargs.kotlin` plugin is applied in `app/build.gradle` |
| Images not loading | Check `android:usesCleartextTraffic` and that image URLs are reachable |
