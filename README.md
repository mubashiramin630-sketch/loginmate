# LoginMate

LoginMate is a beginner-friendly Android application built in Java with XML layouts for an Android Application Development assignment. It provides a complete, modern authentication flow and dashboard interface with a clean blue-and-white theme and full night-mode support.

---

## Implemented Features & Screens

LoginMate consists of seven distinct Activities, each with its own dedicated Java class and XML layout:

1. **SplashActivity**: Launcher screen displaying the app logo, name, and tagline. Automatically transitions to the Login screen after a 2-second delay.
2. **LoginActivity**: Allows users to sign in using their email/username and password. Includes buttons for Login, a link to Register, and a link for Forgot Password.
3. **RegisterActivity**: Collects user details (Full Name, Email, Password, Confirm Password) with robust input validation (checking empty fields, email format via `Patterns.EMAIL_ADDRESS`, and password matching) and saves credentials locally using `SharedPreferences`.
4. **ForgotPasswordActivity**: Accepts an email address, validates input format, and simulates password recovery instructions with an informative dialog before navigating to password reset.
5. **ResetPasswordActivity**: Collects and validates new password inputs and matching confirmation, updating the user's password locally in `SharedPreferences`.
6. **DashboardActivity**: Displays a personalized welcome message and a 2x2 `GridLayout` containing four interactive cards with icons and labels (**Home**, **Services**, **Settings**, and **Profile**). Includes a Logout button.
7. **ProfileActivity**: Displays the current user's name and email retrieved from local storage, along with options to return to the dashboard or log out (clearing the navigation task stack).

---

## Layout Architecture & UI Design

- **ConstraintLayout**: Used for [activity_splash.xml](app/src/main/res/layout/activity_splash.xml) and [activity_login.xml](app/src/main/res/layout/activity_login.xml).
- **LinearLayout**: Used for form-based screens ([activity_register.xml](app/src/main/res/layout/activity_register.xml), [activity_forgot_password.xml](app/src/main/res/layout/activity_forgot_password.xml), [activity_reset_password.xml](app/src/main/res/layout/activity_reset_password.xml), and [activity_profile.xml](app/src/main/res/layout/activity_profile.xml)).
- **GridLayout**: Used for dashboard module cards ([activity_dashboard.xml](app/src/main/res/layout/activity_dashboard.xml)).
- **Theme & Resources**:
  - Consistent blue-and-white theme using Material 3 DayNight styling.
  - Default colors defined in [colors.xml](app/src/main/res/values/colors.xml).
  - Alternative dark mode colors defined in [res/values-night/colors.xml](app/src/main/res/values-night/colors.xml).
  - All visible strings extracted to [strings.xml](app/src/main/res/values/strings.xml).

---

## Navigation Flow

```
SplashActivity ──> LoginActivity ──┬──> RegisterActivity
                                   ├──> ForgotPasswordActivity ──> ResetPasswordActivity
                                   └──> DashboardActivity ──> ProfileActivity
```

---

## Test Credentials

You can test login using either:
- **Default Test Credentials**:
  - Email: `user@loginmate.com`
  - Password: `password123`
- **Or**: Register a new account via the **Register** screen, which will store your credentials locally and allow immediate login.
