# 🎧 Genius - Java Music Platform

## 📌 Description
**Genius** is a Java-based console application inspired by [Genius.com](https://genius.com), developed as part of the **Advanced Programming - Spring 2025** course. This platform simulates a lyric-centric music community with user roles, social interaction, content contribution, and music discovery—all in a clean **Object-Oriented** architecture.

This project is implemented **from scratch** without any template, using Java 17 and following **OOP principles**, **design patterns**, and **clean code practices**.

---

## 🚀 Features

### 🔐 Authentication System
- Register/Login with password hashing (`PasswordUtils.java` uses `BCrypt`)
- Role-based access: **User**, **Artist**, and **Admin**
- Verification system for artists via admin approval
- Interactive animated CLI using `ConsoleUtils.java`

### 🧠 Search System
Implemented in `UserStage.java`:
- **Searchable entities**: Songs, Albums, and Artists
- Users type a keyword and the system filters matches by:
  - **Title** (for songs and albums)
  - **Name/Username** (for artists)
- If matches are mixed, the system asks user to specify what they meant to search.
- From the search result, users can:
  - View full song details
  - View and like/dislike comments
  - Like/dislike the song
  - View lyrics
  - Explore artist profile or album songs

### 🎵 Music Features
- Each song has:
  - Title, lyrics, artist, co-artists, view count, comments, likes/dislikes
- Songs can be:
  - Single or part of an album (`Song.java`)
- Co-artists supported for songs and albums
- View count increases on play

### 💬 Comment System
- Users can comment on songs
- Comments can be liked/disliked
- Displayed with like/dislike count and timestamp

### 📝 Lyric Edit Suggestion
- Users can suggest new lyrics
- Artists (or Admins if artist inactive) approve or reject requests
- Notifications are sent to all relevant parties

### 📊 Charts and Stats
- View top songs sorted by views
- View songs sorted by popularity (likes)

### 🔔 Notification System
- Notifications are delivered for:
  - ✅ Follows
  - 🎵 New song/album releases
  - 📝 Lyric edit suggestions
  - 🔄 Lyric edit approvals/rejections
- Notifications are:
  - Shown **immediately upon login**
  - Accessible any time via dashboards in `UserStage`, `ArtistStage`, or `AdminStage`
- **Seen/Unseen Status**:
  - New (unseen) notifications are shown with **🔔**
  - Once viewed via `checkNotifications()`, they are marked as **✓ Seen**
  - Managed internally using the `Notification` class, which includes:
    - Message text
    - Timestamp (`createdAt`)
    - `seen` flag with `markAsSeen()` method

> ✅ This ensures users never miss important updates, while also keeping track of what they’ve already read.

### 👥 Follow System
- Users can **follow artists**
- All accounts can follow each other except artists who **cannot follow others**
- Notifications triggered on follow
- Followed artists' new content is visible in feed

### 💿 Album Management (Artists only)
- Artists can create albums
- Albums include ordered tracklists
- Albums can include co-artists

---

## 💡 OOP Principles in Practice

| Concept          | Usage in Project                              | Example |
|------------------|-----------------------------------------------|---------|
| **Encapsulation** | Attributes of classes are private; access via getters/setters | `Song.java` uses private `viewCount` with `incrementView()` method |
| **Inheritance**  | Role-specific users (User, Artist, Admin) inherit from `Account` | `class Artist extends Account` |
| **Polymorphism** | Different implementations of `receiveFollowNotification()` based on user role | Overridden in `User`, `Artist`, and `Admin` |
| **Abstraction**  | `Account` is an abstract class, defines shared structure for all accounts | Abstract method `getRole()` implemented in subclasses |
| **Composition**  | `Album` contains a list of `Song` objects | `Album.getTrackList()` returns all songs inside an album |

---

## 🔧 Project Structure

```
com.hirad.genus
│
├── controller
│   ├── AuthController.java
│   ├── SongController.java
│   └── SearchController.java
│
├── model
│   ├── Account.java, User.java, Artist.java, Admin.java
│   ├── Song.java, Album.java, Comment.java
│   ├── Notification.java, LyricEditRequest.java
│
├── seed
│   └── SeedData.java
│
├── ui
│   ├── UserStage.java
│   ├── ArtistStage.java
│   └── AdminStage.java
│
├── utils
│   ├── ConsoleUtils.java
│   ├── PasswordUtils.java
│   └── TextFileManager.java
│
└── Main.java
```

---

## 🧪 Sample Flow

```
> Run app → shows loading and winking animation
> Choose to login/register
> Role-based menu:
  - User: explore/search, comment, follow, suggest lyrics
  - Artist: manage songs/albums, approve edits, see followers
  - Admin: approve artists, moderate edits
```

---

## 💾 Data Persistence
- Implemented via `TextFileManager.java`
- Stores users and artists in `.txt` files
- Optional: `DataManager.java` shows future JSON-based saving with Gson

---

## ✅ Seed Data
`SeedData.java` provides rich demo data:
- 5 verified artists: **David Bowie**, **Eminem**, **Tupac**, etc.
- Albums and songs for each artist
- 3 test users with varied follow/edit/comment activity

---

## 💻 How to Run

### Prerequisites
- Java 17+
- gradle

### Steps
```bash
git clone https://github.com/yourusername/genius-java-platform.git
cd genius-java-platform
mvn compile
mvn exec:java
```

---

## 📜 Changelog

### v1.0 - Core Features
- Full CLI implementation
- Three role-based interactive menus
- Song/album creation
- Voting, comment, and lyric edit system
- Smart search and follow system
- File-based data persistence

---

## 🙌 Credits
- Instructor: **Dr. Saeed Reza Kheradpisheh**
- Built for: *Advanced Programming - Spring 2025*
- Inspired by: [Genius.com](https://genius.com/HiradTorabi)
- And special thanks to my friend and mentor @MmdHosain.

---

## 📫 Contact
For feedback or questions:

📧 babak.torabi.1384@gmail.com  
🐙 GitHub: [yourusername](https://github.com/HiradTorabi)
