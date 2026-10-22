Repair with Replacement 

A full-stack web application for submitting product repair/replacement requests, comparing estimated costs, tracking request status, and managing requests through an admin dashboard.

Team details
- Project title: Repair with Replacement
- Name : Jeffy sansuiya S
- Register number : 711524BEE026   
- Department : Electrical and Electronics Engineering
- College : KIT-Kalaignarkarunanidhi Institute of Technology

Features
- Responsive frontend built with HTML, CSS and JavaScript
- Java Spring Boot REST API
- MySQL persistence using Spring Data JPA
- Submit and view repair requests
- Take a product photo with the device camera or upload an existing image, with preview and server storage
- Capture product age, overall condition, and previous repair history
- Provide a reasoned rule-based Repair/Replace recommendation using cost, age, and condition
- Show the saved photo link and recommendation reason in tracking/admin views
- Rule-based recommendation using estimated costs
- Track a request using its ID
- Admin dashboard to update request status
- Server-side validation for required fields, cost values and status choices

Technologies
Java 17, Spring Boot 3, Spring Web, Spring Data JPA, MySQL, HTML5, CSS3, JavaScript, Maven, Git and GitHub.

Requirements
- JDK 17 or newer
- Maven 3.8+ (or use the Maven wrapper if you add one)
- MySQL 8 or compatible
- Git

Run locally

1. Install and start MySQL.
2. Create the database by running `database/schema.sql`, or allow the configured MySQL URL to create it.
3. Open `src/main/resources/application.properties` and set your database username/password, or provide environment variables:
   - `DB_URL` (default `jdbc:mysql://localhost:3306/repair_replace?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC`)
   - `DB_USERNAME` (default `root`)
   - `DB_PASSWORD` (default empty)
4. From the project root run:
   ```bash
   mvn spring-boot:run
   ```
5. Open **http://localhost:8080** in your browser. The frontend is served by Spring Boot and calls `/api/requests`.

## API endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/requests` | Create a request |
| GET | `/api/requests` | List all requests (admin dashboard) |
| GET | `/api/requests/{id}` | Track one request |
| PATCH | `/api/requests/{id}/status` | Update request status |

Example request body:
```json
{
  "customerName": "Demo Customer",
  "email": "demo@example.com",
  "phone": "9876543210",
  "productType": "Computer / laptop",
  "productName": "Laptop A",
  "problemDescription": "Laptop does not charge",
  "repairCost": 1500,
  "replacementCost": 12000
}
```

Recommendation logic
For demonstration, the backend recommends repair only when repair cost is below 60% of replacement cost, reported condition is Excellent/Good, and product age is 5 years or less. Otherwise it recommends a replacement review. This is a rule-based decision aid, not AI image recognition or a professional diagnosis. A photo is stored for human/technician review. This is a configurable sample rule, not a professional diagnosis. Real costs and final decisions should be verified by a technician.

Important limitations
- The demo admin dashboard has no login/authentication. Do not deploy it publicly with real customer information until authentication and authorization are added.
- The app accepts cost estimates from the form; it does not automatically diagnose damage from photos.
- Use only test data for public demonstrations.

GitHub upload
1. Create a new public or private GitHub repository named `Repair-With-Replacement`.
2. In this folder, run:
   ```bash
   git init
   git add .
   git commit -m "Initial full-stack Repair with Replacement project"
   git branch -M main
   git remote add origin https://github.com/YOUR-USERNAME/Repair-With-Replacement.git
   git push -u origin main
   ```
3. Replace `YOUR-USERNAME` with your GitHub username and use the repository URL in your review submission.
4. Add screenshots of the running website to a `screenshots/` folder if your review requires them.

## Suggested test checklist
- [ ] Homepage loads at `http://localhost:8080`
- [ ] Submitting a valid request returns an ID
- [ ] Request is saved in MySQL
- [ ] Tracking works with a valid ID
- [ ] Invalid/nonexistent IDs show an error
- [ ] Admin list displays submitted requests
- [ ] Status update persists after refresh


Product photo upload
Customers can optionally attach a damaged-product image (JPG, PNG, WebP, or GIF; maximum 5 MB) when submitting a request. Spring Boot validates the upload and saves it under the local `uploads/` directory; the request record stores the image URL, and the admin dashboard shows a `View photo` link. Images are stored locally, so for deployment use persistent file storage and add authentication/authorization before handling real customer data. The image is for human review only; it does not automatically classify damage.
