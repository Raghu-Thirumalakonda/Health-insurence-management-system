# CareCover Health Insurance Management

Spring Boot 4.1 and Thymeleaf app for managing insurance plans, claims, patients, doctors, and partner providers. The frontend and backend run together at `http://localhost:8080`.

## Requirements

- Java 25 or newer
- MySQL Server 8.0 or newer
- MySQL Workbench (optional GUI for creating and viewing the database)

Workbench is a database client; MySQL Server must also be installed and running.

## Configure MySQL

In MySQL Workbench, connect to your local MySQL Server and run `database/create-carecover.sql`. The app also asks MySQL to create the `carecover` database if it does not exist.

In PowerShell, set the credentials you use for that local MySQL connection. Keep your password private and out of source control:

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-local-MySQL-password"
$env:ADMIN_USERNAME = "admin"
$env:ADMIN_PASSWORD = "choose-a-private-secret-at-least-12-characters"
```

The app uses `jdbc:mysql://localhost:3306/carecover` by default. You can override the connection with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables.

## Run

From the project folder:

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080`. Hibernate creates or updates the tables inside the `carecover` schema.
You will be sent to the CareCover sign-in page. Sign in to the admin workspace with the `ADMIN_USERNAME` and `ADMIN_PASSWORD` values set above. Keep those values private and set them again in every new PowerShell window before starting the app.

## Features

- Dashboard overview of plans, claims, patients, doctors, and providers.
- Insurance plan create, edit, search, and delete.
- Claim submission and review status updates.
- Patient, doctor, and provider directory management.
- Admin and patient sign-in with separate roles; BCrypt password hashes, session logout, and patient password change.
- Patient portal for coverage status, policy number, coverage dates, member contact details, and renewal notices.
- Patient profile email, phone, registration/visit dates, optional photo URL, insurance tracking, and renewal message previews.
- Responsive Thymeleaf pages with no separate frontend server.

Tests use an isolated in-memory H2 database; the running app uses MySQL. Existing records in the old H2 file are not automatically copied to MySQL.

Patient portal access is enabled by an admin in the patient record. Set a unique email and an initial password of at least 10 characters, then share the sign-in details with the patient privately. Patients can change their password after signing in. Admin access uses environment variables, and passwords are not committed to the project. Renewal notices are previews only; the app does not send email or SMS.

This is a portfolio/demo application. Use fictional patient details and keep it on a trusted local environment until you add deployment-grade privacy, monitoring, backups, and email/SMS delivery.

## Cloud deployment (Render)

The repository includes a `Dockerfile` and `render.yaml` Blueprint. Push the project to GitHub, then open Render and choose **New > Blueprint** and connect the repository. Render will create the web app and PostgreSQL database from the Blueprint. Enter a private `ADMIN_PASSWORD` when prompted; use at least 12 characters and do not commit it to GitHub. The app listens on Render's assigned port and uses PostgreSQL in the cloud. The local setup continues to use MySQL.

The Blueprint uses Render's free web and database plans for a portfolio demo. Free PostgreSQL databases are temporary and expire after 30 days; upgrade the database in Render before that date if you want the hosted data to remain available. Free web services may sleep when idle. Do not use real patient data in this demo.
